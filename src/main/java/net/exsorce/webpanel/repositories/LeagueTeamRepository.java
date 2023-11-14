package net.exsorce.webpanel.repositories;

import net.exsorce.webpanel.model.league.LeagueTeam;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueTeamRepository extends JpaRepository< LeagueTeam, Integer >
{

	Optional<LeagueTeam> findByLocalizedName ( String localizedName );

	Optional<LeagueTeam> findByName ( String name );

}
