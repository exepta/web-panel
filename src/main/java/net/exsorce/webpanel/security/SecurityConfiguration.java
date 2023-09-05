package net.exsorce.webpanel.security;

import lombok.RequiredArgsConstructor;

import net.exsorce.webpanel.service.LogoutService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration
{

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

	private final LogoutService logoutService;

	@Bean
	public SecurityFilterChain securityFilterChain ( HttpSecurity http ) throws Exception
	{
		http.csrf( AbstractHttpConfigurer::disable )
				.authorizeHttpRequests(builder -> builder
						.requestMatchers(
								antMatcher("/api/v0/auth/**"))
						.permitAll()
						.anyRequest()
						.authenticated())
				.sessionManagement(session -> session
						.sessionCreationPolicy( SessionCreationPolicy.STATELESS ))
				.authenticationProvider( authenticationProvider )
				.addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class )
				.logout(builder -> builder
						.logoutUrl( "/api/v0/auth/logout" )
						.addLogoutHandler(logoutService)
						.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

		return http.build();
	}

	@Bean
	public WebMvcConfigurer configurer ()
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings ( CorsRegistry registry )
			{
				registry.addMapping( "/**" ).allowedOrigins( "*" ).allowedHeaders( "*" );
			}
		};
	}

}
