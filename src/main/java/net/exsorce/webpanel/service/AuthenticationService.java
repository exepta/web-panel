package net.exsorce.webpanel.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

import net.exsorce.webpanel.repositories.TokenRepository;
import net.exsorce.webpanel.repositories.UserRepository;
import net.exsorce.webpanel.repositories.token.Token;
import net.exsorce.webpanel.repositories.token.TokenType;
import net.exsorce.webpanel.rest.request.AuthenticationRequest;
import net.exsorce.webpanel.rest.request.RegisterRequest;
import net.exsorce.webpanel.rest.response.AuthenticationResponse;
import net.exsorce.webpanel.model.User;
import net.exsorce.webpanel.model.UserRole;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService
{

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	private final JWTService jwtService;

	public AuthenticationResponse register ( RegisterRequest request )
	{
		User user = User.builder()
				.firstname( request.getFirstname() )
				.lastname( request.getLastname() )
				.email( request.getEmail() )
				.password( passwordEncoder.encode( request.getPassword() ) )
				.role( UserRole.USER )
				.build();
		User savedUser = userRepository.save( user );
		String jwt = jwtService.generateToken( user );
		String refreshToken = jwtService.generateRefreshToken( user );
		saveUserToken( savedUser, jwt );
		return AuthenticationResponse.builder()
				.accessToken( jwt )
				.refreshToken( refreshToken )
				.build();
	}

	public AuthenticationResponse authenticate ( AuthenticationRequest request )
	{
		authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() ) );
		User user = userRepository.findByEmail( request.getEmail() ).orElseThrow();

		String jwt = jwtService.generateToken( user );
		String refreshToken = jwtService.generateRefreshToken( user );
		revokeAllUserTokens( user );
		saveUserToken( user, jwt );
		return AuthenticationResponse.builder()
				.accessToken( jwt )
				.refreshToken( refreshToken )
				.build();
	}

	public void refreshToken ( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		final String authHeader = request.getHeader( HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userData;
		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			return;
		}

		refreshToken = authHeader.substring(7);
		userData = jwtService.extractUserData(refreshToken);

		if (userData != null) {
			User user = this.userRepository.findByEmail(userData)
					.orElseThrow();
			if (jwtService.isValid(refreshToken, user)) {
				String accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				AuthenticationResponse authResponse = AuthenticationResponse.builder()
						.accessToken(accessToken)
						.refreshToken(refreshToken)
						.build();
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}

	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder()
				.user(user)
				.content(jwtToken)
				.tokenType( TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(User user) {
		List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;

		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});

		tokenRepository.saveAll(validUserTokens);
	}
}
