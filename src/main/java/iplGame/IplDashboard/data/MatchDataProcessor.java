package iplGame.IplDashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import iplGame.IplDashboard.data.MatchDataProcessor;
import iplGame.IplDashboard.model.Match;

//This is for processing the data taking data in MatchInput from csv and process then Match take the need data and store in databse 

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	@Override
	public Match process(final MatchInput matchInput) throws Exception {

		Match match = new Match();

		//System.out.println("MatchInpute data without long "+matchInput.getId());
		//System.out.println("MatchInpute data without long "+Long.parseLong(matchInput.getId()));

		match.setId(Long.parseLong(matchInput.getId()));
		match.setCity(matchInput.getCity());
		match.setDate(LocalDate.parse(matchInput.getDate()));
		match.setPlayerOfMatch(matchInput.getPlayer_of_match());
		match.setVenue(matchInput.getVenue());

		//set Team 1 and Team 2 Depening on the innings order
		String firstInningTeam, secondInningsTeam;

		if("bat".equals(matchInput.getToss_decision())) {
			firstInningTeam = matchInput.getToss_winner();
			secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
					? matchInput.getTeam2() : matchInput.getTeam1();
		}else {
			secondInningsTeam = matchInput.getToss_winner();
			firstInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) 
					? matchInput.getTeam2() : matchInput.getTeam1();
		}

		match.setTeam1(firstInningTeam);
		match.setTeam2(secondInningsTeam);

		match.setTossWinner(matchInput.getToss_winner());
		match.setTossDecision(matchInput.getToss_winner());
		match.setMatchwinner(matchInput.getWinner());
		match.setResult(matchInput.getResult());
		match.setResultMargin(matchInput.getResult_margin());
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());


		return match;
	}



}
