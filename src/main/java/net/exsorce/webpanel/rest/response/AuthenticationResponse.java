package net.exsorce.webpanel.rest.response;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse extends AbstractResponse
{

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("refresh_token")
	private String refreshToken;

}
