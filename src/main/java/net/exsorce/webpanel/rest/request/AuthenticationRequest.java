package net.exsorce.webpanel.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest
{

	private String email;
	private String password;

}
