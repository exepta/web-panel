package net.exsorce.webpanel.model.league;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Ramke
 * @since 1.0.0-SNAPSHOT
 */

@Entity
@Getter
@Setter
@Table(name = "_league")
public class LeagueTeam
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(length = 8)
	private String localizedName;

	@Column(length = 22)
	private String name;

	private String description;

	@Column(length = 5)
	private String prefix;

	private String dateOfCreation;

	private String teamColor;

	//Todo: members


	
}
