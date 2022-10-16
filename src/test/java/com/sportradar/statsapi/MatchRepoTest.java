package com.sportradar.statsapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.sportradar.statsapi.entity.Match;
import com.sportradar.statsapi.service.MatchService;


@SpringBootTest
public class MatchRepoTest {

	/**
	 * 
	 */
	@Autowired
	private MatchService matchService;
	
	private Match match;
	@BeforeEach
	public void setup() {
		match = new Match("Mexico",0,"Canada",0);
	}
	
	/**
	 * 
	 */
	@Test
	void addMatchTest() {
		boolean actualResult = false;
		Optional<Match> returnedObject = Optional.ofNullable(matchService.addMatch(match));
		Match returnedMatch = returnedObject.get();
		if(returnedMatch.getHomeTeam().equals("Mexico")) {
			actualResult = true;
		}
		matchService.deleteAll();
		assertThat(actualResult).isTrue();		
	}
	
	/**
	 * 
	 */
	@Test
	void getMatchTest() {
		boolean actualResult = false;
		Optional<Match> returnedObject = Optional.ofNullable(matchService.addMatch(match));
		Match returnedMatch = matchService.getMatch(returnedObject.get().getID());
		if(returnedMatch.getHomeTeam().equals("Mexico")) {
			actualResult = true;
		}
		matchService.deleteAll();
		assertThat(actualResult).isTrue();		
	}
	
	/**
	 * 
	 */
	@Test
	void updateMatchScoreTest() {
		boolean actualResult = false;
		Match returnedMatch = matchService.addMatch(match);
		returnedMatch = matchService.getMatch(returnedMatch.getID());
		returnedMatch.setHomeTeamScore(5);
		returnedMatch.setAwayTeamScore(5);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		
		Match updatedMatch = matchService.getMatch(returnedMatch.getID());
		if(updatedMatch.getAwayTeamScore()==5 && updatedMatch.getHomeTeamScore()==5) {
			actualResult = true;
		}
		matchService.deleteAll();
		assertThat(actualResult).isTrue();
		
	}
	
	/**
	 * 
	 */
	@Test
	void updateMatchStatusTest() {
		boolean actualResult = false;
		Match newMatch = matchService.addMatch(match);
		Match returnedMatch = matchService.updateMatchStatus(newMatch.getID());
		
		Match udpatedMatch = matchService.getMatch(returnedMatch.getID());
		
		if(udpatedMatch.getStatus().equals("Finished")) {
			actualResult = true;
		}
		matchService.deleteAll();
		assertThat(actualResult).isTrue();
	}
	
	/**
	 * 
	 */
	@Test
	void deleteMatchTest() {
		boolean actualResult = false;
		Match newMatch = matchService.addMatch(match);
		Match returnedMatch = matchService.getMatch(newMatch.getID());
		Long id = returnedMatch.getID();
		matchService.deleteMatch(returnedMatch.getID());
		Match deletedMatch = matchService.getMatch(id);
		if(deletedMatch == null) {
			actualResult = true;
		}
		matchService.deleteAll();
		assertThat(actualResult).isTrue();
	}
	
	/**
	 * 
	 */
	@Test
	void getAllRunningMatchesTest() {
		boolean actualResult = false;
		
		matchService.addMatch(match);
		
		match = new Match("Spain",0,"Brazil",0);
		matchService.addMatch(match);
		
		match = new Match("Germany",0,"France",0);
		matchService.addMatch(match);
		
		match = new Match("Uruguay",0,"Italy",0);
		matchService.addMatch(match);
		
		match = new Match("Argentina",0,"Australia",0);
		matchService.addMatch(match);
		
		List<Match> matches = matchService.getAllRunningMatches();
		
		if(matches.size() == 5) {
			actualResult = true;
		} else {
			actualResult = false;
		}
		matchService.deleteAll();
		assertThat(actualResult).isTrue();
		
	}
	
	/*Verify descending order by match score with two equal scores*/
	/**
	 * 
	 */
	@Test
	void getMatcheSummaryTest() {
		boolean actualResult = false;
		Match returnedMatch = null;
		
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(0);
		returnedMatch.setAwayTeamScore(5);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("Spain",0,"Brazil",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(10);
		returnedMatch.setAwayTeamScore(2);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("Germany",0,"France",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(2);
		returnedMatch.setAwayTeamScore(2);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("Uruguay",0,"Italy",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(6);
		returnedMatch.setAwayTeamScore(6);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("Argentina",0,"Australia",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(3);
		returnedMatch.setAwayTeamScore(1);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		List<Match> matches = matchService.getMatchesSummary();
		
		if(matches.get(0).getHomeTeam().equals("Uruguay")) {
			if(matches.get(1).getHomeTeam().equals("Spain")) {
				if(matches.get(2).getHomeTeam().equals("Mexico")) {
					if(matches.get(3).getHomeTeam().equals("Argentina")) {
						if(matches.get(4).getHomeTeam().equals("Germany")) {
							actualResult = true;
						}
					}
				}
			}
			
		} else {
			actualResult = false;
		}
		matchService.deleteAll();
		assertThat(actualResult).isTrue();
		
	}
	
	/*Verify descending order by match start time with three equal scores*/
	/**
	 * 
	 */
	@Test
	void getMatcheSummaryTestByStartTime() {
		boolean actualResult = false;
		Match returnedMatch = null;
		
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(0);
		returnedMatch.setAwayTeamScore(5);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("Spain",0,"Brazil",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(10);
		returnedMatch.setAwayTeamScore(2);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("Germany",0,"France",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(2);
		returnedMatch.setAwayTeamScore(2);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("Uruguay",0,"Italy",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(6);
		returnedMatch.setAwayTeamScore(6);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("Argentina",0,"Australia",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(3);
		returnedMatch.setAwayTeamScore(1);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		match = new Match("India",0,"Pakistan",0);
		returnedMatch = matchService.addMatch(match);
		returnedMatch.setHomeTeamScore(8);
		returnedMatch.setAwayTeamScore(4);
		matchService.updateMatchScore(returnedMatch.getID(), returnedMatch);
		matchService.updateMatchStatus(returnedMatch.getID());
		
		List<Match> matches = matchService.getMatchesSummary();
		
		if(matches.get(0).getHomeTeam().equals("India")) {
		if(matches.get(1).getHomeTeam().equals("Uruguay")) {
			if(matches.get(2).getHomeTeam().equals("Spain")) {
				if(matches.get(3).getHomeTeam().equals("Mexico")) {
					if(matches.get(4).getHomeTeam().equals("Argentina")) {
						if(matches.get(5).getHomeTeam().equals("Germany")) {
							actualResult = true;
						}
					}
				}
			}
		}
		} else {
			actualResult = false;
		}
		matchService.deleteAll();
		assertThat(actualResult).isTrue();
		
	}
}
