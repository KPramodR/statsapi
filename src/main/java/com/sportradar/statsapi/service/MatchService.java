package com.sportradar.statsapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportradar.statsapi.StatsapiApplication;
import com.sportradar.statsapi.entity.Match;
import com.sportradar.statsapi.repo.MatchRepository;

@Service
public class MatchService {
	@Autowired
	private MatchRepository matchRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(StatsapiApplication.class);
	
	/**
	 * @param match
	 * @return
	 */
	public Match addMatch(Match match) {
		logger.info("Adding new match.");
		Match tempMatch = new Match();
		tempMatch.setHomeTeam(match.getHomeTeam());
		tempMatch.setAwayTeam(match.getAwayTeam());
		tempMatch.setHomeTeamScore(0);
		tempMatch.setAwayTeamScore(0);
		tempMatch.setStatus("Running");
		logger.info("Adding new match.");
		Match newMatch = matchRepo.save(tempMatch);
		logger.info("New match added.");
		return newMatch;
	}
	
	/**
	 * @param matchId
	 * @return
	 */
	public Match getMatch(Long matchId) {
		logger.info("Retriving match.");
		Match matchObject = matchRepo.findById(matchId).isPresent() ? 
				matchRepo.findById(matchId).get(): null;
		logger.info("Match retrived.");
		return matchObject;
	}

	/**
	 * @param matchId
	 * @param matchDetails
	 * @return
	 */
	public Match updateMatchScore(Long matchId, Match matchDetails) {
		logger.info("Updating match score.");
		Optional<Match> matchObject = matchRepo.findById(matchId);
		Match match = matchObject.get();
		match.setHomeTeamScore(matchDetails.getHomeTeamScore());
		match.setAwayTeamScore(matchDetails.getAwayTeamScore());
	    return matchRepo.save(match);
 	}
	
	/**
	 * @param matchId
	 * @return
	 */
	public Match updateMatchStatus(Long matchId) {
		logger.info("Updating match status.");
		Optional<Match> matchObject = matchRepo.findById(matchId);
		Match match = matchObject.get();
		match.setStatus("Finished");
        return matchRepo.save(match);
	}
	
	/**
	 * @param matchId
	 * @return
	 */
	public boolean deleteMatch(Long matchId) {
		logger.info("Deleting match by id : " + matchId);
		try {
			matchRepo.deleteById(matchId);
			logger.info("Match deleted");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public boolean deleteAll() {
		logger.info("Deleting all matches");
		try {
			matchRepo.deleteAll();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public List<Match> getAllRunningMatches() {
		logger.info("Retriving all running matches");
		List<Match> matches = matchRepo.findRunningMatches();
		return matches;
	}
	
	/**
	 * @return
	 */
	public List<Match> getMatchesSummary() {
		logger.info("Retriving all finished matches");
		List<Match> matches = matchRepo.findMatchSummary();
		int[] match_sequence = new int[matches.size()]; 
		int[] match_score = new int[matches.size()]; 
		
		for(int i=0; i< matches.size();i++) {
			match_sequence[i]=i;
			match_score[i] = matches.get(i).getHomeTeamScore() + matches.get(i).getAwayTeamScore();
		}
		
		int temp1=0;
		int temp2=0;
		for(int i=0; i<matches.size();i++) {
			for(int j=i+1; j<matches.size();j++) {
				if(match_score[j]>match_score[i]) {
					temp1 = match_score[i]; 
					temp2 = match_sequence[i];
					
					match_score[i] = match_score[j];
					match_sequence[i] = match_sequence[j];
					
					match_score[j] = temp1;
					match_sequence[j] = temp2;			
				}
				
			}
		}
		
		for(int i=0; i<match_score.length-1;i++) {
			for(int j=0;j<match_score.length-1;j++) {
				if(match_score[j] == match_score[j+1]) {
					if(match_sequence[j] < match_sequence[j+1]) {
						temp1 = match_sequence[j+1];
						match_sequence[j+1] = match_sequence[j];
						match_sequence[j] = temp1;
					}					
				}
			}			
		}
		
		List<Match> formattedList = new ArrayList<Match>();
		for(int i=0; i<match_sequence.length;i++) {
		formattedList.add(matches.get(match_sequence[i]));
		}
		return formattedList;
	}
	
}
