package net.exsorce.webpanel.model.league;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.exsorce.webpanel.model.User;

import java.util.ArrayList;
import java.util.List;

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

	@ManyToOne
	@JoinColumn( name = "leader_id" )
	private User leader;

	@ElementCollection
	private List<User> members = new ArrayList<>();


	
}
