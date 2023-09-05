package net.exsorce.webpanel.service;

import lombok.RequiredArgsConstructor;

import net.exsorce.webpanel.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService
{

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException
	{
		return userRepository.findByEmail( username ).orElseThrow(() -> new UsernameNotFoundException( "User not found!" ));
	}

	public boolean exist( String email )
	{
		if(email == null || email.isBlank() || email.isEmpty())
			return false;

		return loadUserByUsername( email ) != null;
	}
}
