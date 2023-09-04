package net.exsorce.webpanel;

import lombok.RequiredArgsConstructor;

import org.pheanixarea.webpanel.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig
{

	private final UserService userService;

	@Bean
	public AuthenticationProvider authenticationProvider ()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService( userService );
		provider.setPasswordEncoder( passwordEncoder() );
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager ( AuthenticationConfiguration configuration ) throws Exception
	{
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder ()
	{
		return new BCryptPasswordEncoder();
	}

}
