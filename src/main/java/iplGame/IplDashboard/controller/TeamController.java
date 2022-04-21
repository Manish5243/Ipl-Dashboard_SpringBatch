package iplGame.IplDashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import iplGame.IplDashboard.model.Team;
import iplGame.IplDashboard.repository.MatchRepository;
import iplGame.IplDashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	
	/*//With this also we can autowired TeamRepository
	public TeamController(TeamRepository teamRepository) {
		super();
		this.teamRepository = teamRepository;
	}
	*/


	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = this.teamRepository.findByTeamName(teamName);
		Pageable pageable = PageRequest.of(0, 4);
		team.setMatches(matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable));
		
		return team;
	}

}
