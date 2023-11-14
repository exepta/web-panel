package net.exsorce.webpanel.rest;

import lombok.RequiredArgsConstructor;
import net.exsorce.webpanel.rest.response.LeagueTeamResponse;
import net.exsorce.webpanel.service.LeagueTeamService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/team/{UID}")
	public ResponseEntity< LeagueTeamResponse > catchTeam ( @PathVariable String UID )
	{
		if(UID == null || UID.isBlank() || UID.isEmpty())
		{
			return ResponseEntity.ok( null );
		}

		return ResponseEntity.ok( leagueTeamService.getByLocalizedName( UID ) );
	}

}
