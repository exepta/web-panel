package net.exsorce.webpanel.model.league;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "_league_user")
public class LeagueUser
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String leagueName;

	@Enumerated( EnumType.STRING )
	private LeagueElo elo;

	@Enumerated( EnumType.STRING )
	private LeagueRole role;

	private String description;

}
