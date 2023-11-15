package net.exsorce.webpanel.service;

import lombok.RequiredArgsConstructor;
import net.exsorce.webpanel.model.User;
import net.exsorce.webpanel.model.league.LeagueTeam;
import net.exsorce.webpanel.repositories.LeagueTeamRepository;
import net.exsorce.webpanel.repositories.UserRepository;
import net.exsorce.webpanel.rest.request.LeagueTeamCreateRequest;
import net.exsorce.webpanel.rest.response.AbstractResponse;
import net.exsorce.webpanel.rest.response.LeagueTeamResponse;
import net.exsorce.webpanel.rest.response.UserInfoResponse;
import net.exsorce.webpanel.utils.Commons;

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
	private final UserRepository userRepository;

	private final AuthenticationService authenticationService;

	public LeagueTeamResponse createTeam ( LeagueTeamCreateRequest request, String token )
	{
		UserInfoResponse userInfoResponse = authenticationService.getUser( token );
		User user = userRepository.findByEmail( userInfoResponse.getEmail() ).orElseThrow();
		LeagueTeam team = LeagueTeam.builder()
				.localizedName( "" )
				.name( request.getName() )
				.description( request.getDescription() )
				.dateOfCreation( Commons.currentDate() )
				.prefix( request.getPrefix() )
				.teamColor( request.getTeamColor() )
				.build();

		LeagueTeam saved = leagueTeamRepository.save( team );
		return LeagueTeamResponse.builder()
				.name( saved.getName() )
				.prefix( saved.getPrefix() )
				.dateOfCreation( saved.getDateOfCreation() )
				.teamColor( saved.getTeamColor() )
				.description( saved.getDescription() )
				.build();
	}

	public LeagueTeamResponse getByLocalizedName ( String localizedName )
	{
		LeagueTeam team = leagueTeamRepository.findByLocalizedName( localizedName ).orElse( null );
		if(team == null) {
			team = leagueTeamRepository.findByName( localizedName ).orElse( null );
			if(team == null)
			{
				return LeagueTeamResponse.builder().build();
			}
		}

		return LeagueTeamResponse.builder()
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
					.name( team.getName() )
					.prefix( team.getPrefix() )
					.description( team.getDescription() )
					.dateOfCreation( team.getDateOfCreation() )
					.teamColor( team.getTeamColor() )
					.build());
		}

		return responses;
	}

	public boolean existTeam ( String localizedName )
	{
		if(localizedName == null || localizedName.isBlank() || localizedName.isEmpty())
			return false;

		return getByLocalizedName( localizedName ) != null;
	}
}
