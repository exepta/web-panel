package net.exsorce.webpanel.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import net.exsorce.webpanel.repositories.TokenRepository;
import net.exsorce.webpanel.service.JWTService;
import net.exsorce.webpanel.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{

	private final JWTService jwtService;
	private final UserService userService;

	private final TokenRepository tokenRepository;

	@Override
	protected void doFilterInternal ( @NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain ) throws ServletException, IOException
	{
		final String authenticationHeader = request.getHeader( "Authorization" );
		final String jwt;
		final String userData;
		if (authenticationHeader == null || !authenticationHeader.startsWith( "Bearer " ))
		{
			filterChain.doFilter( request, response );
			return;
		}
		jwt = authenticationHeader.substring( 7 );
		userData = jwtService.extractUserData(jwt);
		if ( userData != null && SecurityContextHolder.getContext().getAuthentication() == null )
		{
			UserDetails details = this.userService.loadUserByUsername(userData);
			boolean validToken = tokenRepository.findByContent( jwt )
					.map( token -> !token.isExpired() && !token.isRevoked() ).orElse( false );
			if ( jwtService.isValid( jwt, details ) && validToken) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( details, null, details.getAuthorities() );
				authenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );
				SecurityContextHolder.getContext().setAuthentication( authenticationToken );
			}

		}
		filterChain.doFilter( request, response );
	}
}
