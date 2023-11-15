package net.exsorce.webpanel.rest;

import lombok.RequiredArgsConstructor;
import net.exsorce.webpanel.rest.request.LeagueTeamCreateRequest;
import net.exsorce.webpanel.rest.response.AbstractResponse;
import net.exsorce.webpanel.rest.response.ErrorResponse;
import net.exsorce.webpanel.rest.response.LeagueTeamResponse;
import net.exsorce.webpanel.service.LeagueTeamService;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel Ramke
 * @since 1.0.0-SNAPSHOT
 */

@RestController
@RequestMapping("/api/v0/league")
@RequiredArgsConstructor
public class LeagueTeamRestController
{

	private final LeagueTeamService leagueTeamService;

	@PostMapping("/create")
	public ResponseEntity< AbstractResponse > createTeam ( @RequestHeader( HttpHeaders.AUTHORIZATION) String token,
			@RequestBody LeagueTeamCreateRequest request )
	{
		if(request == null) {
			return ResponseEntity.status( HttpStatus.BAD_REQUEST).body( ErrorResponse.builder()
					.message("Request was null!").code(HttpStatus.BAD_REQUEST.value()).build());
		}

		if(leagueTeamService.existTeam( request.getName() ))
		{
			return ResponseEntity.status( HttpStatus.FOUND ).body( ErrorResponse.builder()
					.message( "Team name already exists!" ).code( HttpStatus.FOUND.value() ).build() );
		}

		return ResponseEntity.ok( leagueTeamService.createTeam( request, token ) );
	}

	@GetMapping("/team/{UID}")
	public ResponseEntity< LeagueTeamResponse > catchTeam ( @PathVariable String UID )
	{
		if(UID == null || UID.isBlank() || UID.isEmpty())
		{
			return ResponseEntity.ok( null );
		}

		return ResponseEntity.ok( leagueTeamService.getByLocalizedName( UID ) );
	}

	@GetMapping("/teams")
	public ResponseEntity< List<LeagueTeamResponse> > catchTeams ()
	{
		return ResponseEntity.ok( leagueTeamService.getAllTeams() );
	}

}
