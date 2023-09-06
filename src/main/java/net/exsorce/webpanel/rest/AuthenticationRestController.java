package net.exsorce.webpanel.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import net.exsorce.webpanel.rest.request.AuthenticationRequest;
import net.exsorce.webpanel.rest.request.RegisterRequest;
import net.exsorce.webpanel.rest.response.AbstractResponse;
import net.exsorce.webpanel.rest.response.ErrorResponse;
import net.exsorce.webpanel.service.AuthenticationService;
import net.exsorce.webpanel.service.JWTService;
import net.exsorce.webpanel.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
	private final UserService userService;
	private final JWTService jwtService;

	@PostMapping("/register")
	public ResponseEntity<AbstractResponse> register (@RequestBody RegisterRequest request )
	{
		if(request == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
					.message("Request was null!").code(HttpStatus.BAD_REQUEST.value()).build());
		}

		if(userService.exist( request.getEmail() )) {
			return ResponseEntity.status(HttpStatus.FOUND).body(ErrorResponse.builder()
					.message("E-Mail already in use!").code(HttpStatus.FOUND.value()).build());
		}
		return ResponseEntity.ok( authenticationService.register( request ) );
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AbstractResponse> register ( @RequestBody AuthenticationRequest request )
	{
		if(request == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
					.message("Request was null!").code(HttpStatus.BAD_REQUEST.value()).build());
		}

		return ResponseEntity.ok( authenticationService.authenticate( request ) );
	}

	@GetMapping("/check-user")
	public ResponseEntity<Boolean> checkUser (@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		if(token == null) {
			return ResponseEntity.ok(false);
		}
		UserDetails details = userService.loadUserByUsername(jwtService.extractUserData(token.replace("Bearer ", "")));
		return ResponseEntity.ok(jwtService.isValid(token, details));
	}

	@PostMapping("/refresh-token")
	public void refreshToken ( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
		authenticationService.refreshToken(request, response);
	}

}
