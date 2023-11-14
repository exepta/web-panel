package net.exsorce.webpanel.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Daniel Ramke
 * @since 1.0.0-SNAPSHOT
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeagueTeamResponse extends AbstractResponse
{

	@JsonProperty("localizedName")
	private String localizedName;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("prefix")
	private String prefix;

	@JsonProperty("dateOfCreation")
	private String dateOfCreation;

	@JsonProperty("teamColor")
	private String teamColor;

}
