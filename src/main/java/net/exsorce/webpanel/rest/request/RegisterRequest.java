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
public class RegisterRequest
{

	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;

}
