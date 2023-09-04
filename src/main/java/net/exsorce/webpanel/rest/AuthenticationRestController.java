package net.exsorce.webpanel.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import net.exsorce.webpanel.rest.request.AuthenticationRequest;
import net.exsorce.webpanel.rest.request.RegisterRequest;
import net.exsorce.webpanel.rest.response.AuthenticationResponse;
import net.exsorce.webpanel.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@RestController
@RequestMapping("/api/v0/auth")
@RequiredArgsConstructor
public class AuthenticationRestController
{

	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity< AuthenticationResponse > register ( @RequestBody RegisterRequest request )
	{
		return ResponseEntity.ok( authenticationService.register( request ) );
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> register ( @RequestBody AuthenticationRequest request )
	{
		return ResponseEntity.ok( authenticationService.authenticate( request ) );
	}


	@PostMapping("/refresh-token")
	public void refreshToken ( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		authenticationService.refreshToken(request, response);
	}

}
