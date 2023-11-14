package net.exsorce.webpanel.service;

import lombok.RequiredArgsConstructor;
import net.exsorce.webpanel.model.league.LeagueTeam;
import net.exsorce.webpanel.repositories.LeagueTeamRepository;
import net.exsorce.webpanel.rest.response.LeagueTeamResponse;

import java.util.ArrayList;
import java.util.List;

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

	public List<LeagueTeamResponse> getAllTeams ()
	{
		List<LeagueTeam> teams = leagueTeamRepository.findAll();
		List<LeagueTeamResponse> responses = new ArrayList<>();
		for(LeagueTeam team : teams)
		{
			responses.add( LeagueTeamResponse.builder()
					.localizedName( team.getLocalizedName() )
					.name( team.getName() )
					.prefix( team.getPrefix() )
					.description( team.getDescription() )
					.dateOfCreation( team.getDateOfCreation() )
					.teamColor( team.getTeamColor() )
					.build());
		}

		return responses;
	}

}
