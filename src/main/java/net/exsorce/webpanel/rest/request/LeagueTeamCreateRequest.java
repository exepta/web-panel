package net.exsorce.webpanel.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daniel Ramke
 * @since 1.0.0-SNAPSHOT
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeagueTeamCreateRequest
{

	private String name;

	private String description;

	private String prefix;

	private String teamColor;

}
