package net.exsorce.webpanel.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import net.exsorce.webpanel.repositories.TokenRepository;
import net.exsorce.webpanel.repositories.token.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler
{

	private final TokenRepository tokenRepository;

	@Override
	public void logout ( HttpServletRequest request, HttpServletResponse response, Authentication authentication )
	{
		final String authenticationHeader = request.getHeader( "Authorization" );
		final String jwt;
		if(authenticationHeader == null || !authenticationHeader.startsWith( "Bearer " )) {
			return;
		}
		jwt = authenticationHeader.substring( 7 );
		Token storedToken = tokenRepository.findByContent( jwt ).orElse( null );
		if(storedToken != null) {
			storedToken.setExpired( true );
			storedToken.setRevoked( true );
			tokenRepository.save( storedToken );
			SecurityContextHolder.clearContext();
		}
	}
}
