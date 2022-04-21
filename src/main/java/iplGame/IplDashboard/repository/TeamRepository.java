package iplGame.IplDashboard.repository;

import org.springframework.data.repository.CrudRepository;

import iplGame.IplDashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
	Team findByTeamName(String teamName);
}
