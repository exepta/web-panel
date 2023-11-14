package net.exsorce.webpanel.model.league;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Daniel Ramke
 * @since 1.0.0-SNAPSHOT
 */

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "_league")
public class LeagueTeam
{

	@Id
	private Integer id;

	@Column(length = 8)
	private String localizedName;

	@Column(length = 22)
	private String name;

	private String description;

	@Column(length = 5)
	private String prefix;

	private String dateOfCreation;

	@Column(length = 50)
	private String teamColor;

	//Todo: members


	
}
