package net.exsorce.webpanel.service;

import lombok.RequiredArgsConstructor;
import net.exsorce.webpanel.model.league.LeagueTeam;
import net.exsorce.webpanel.repositories.LeagueTeamRepository;
import net.exsorce.webpanel.rest.response.LeagueTeamResponse;

import org.springframework.stereotype.Service;

/**
 * @author Daniel Ramke
 * @since 1.0.0-SNAPSHOT
 */

@Service
@RequiredArgsConstructor
public class LeagueTeamService
{

	private final LeagueTeamRepository leagueTeamRepository;

	public LeagueTeamResponse getByLocalizedName ( String localizedName )
	{
		System.out.println(localizedName);
		LeagueTeam team = leagueTeamRepository.findByLocalizedName( localizedName ).orElse( null );
		System.out.println(team);
		if(team == null) {
			return LeagueTeamResponse.builder().build();
		}

		return LeagueTeamResponse.builder()
				.localizedName( localizedName )
				.name( team.getName() )
				.prefix( team.getPrefix() )
				.description( team.getDescription() )
				.dateOfCreation( team.getDateOfCreation() )
				.teamColor( team.getTeamColor() )
				.build();
	}

}
