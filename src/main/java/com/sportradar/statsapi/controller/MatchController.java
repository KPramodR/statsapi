package com.sportradar.statsapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportradar.statsapi.entity.Match;
import com.sportradar.statsapi.repo.MatchRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MatchController {
	
	@Autowired
	private MatchRepository matchRepo;
	
	@PostMapping("/addmatch")
	public Match addMatch(@RequestBody Match match) {
		Match tempMatch = new Match();
		tempMatch.setHomeTeam(match.getHomeTeam());
		tempMatch.setAwayTeam(match.getAwayTeam());
		tempMatch.setHomeTeamScore(0);
		tempMatch.setAwayTeamScore(0);
		tempMatch.setStatus("Running");
		return matchRepo.save(tempMatch);
	}
	
	@PutMapping("/updatematch/{id}")
	public ResponseEntity < Match > updateMatchScore(@PathVariable("id") Long matchId, @RequestBody Match matchDetails) {
		Optional<Match> matchObject = matchRepo.findById(matchId);
		Match match = matchObject.get();
		match.setHomeTeamScore(matchDetails.getHomeTeamScore());
		match.setAwayTeamScore(matchDetails.getAwayTeamScore());
	    final Match updatedMatch = matchRepo.save(match);
        return ResponseEntity.ok(updatedMatch);
	}
	
	@PutMapping("/finishmatch/{id}")
	public ResponseEntity < Match > updateMatchStatus(@PathVariable("id") Long matchId) {
		System.out.println(matchId);
		Optional<Match> matchObject = matchRepo.findById(matchId);
		Match match = matchObject.get();
		match.setStatus("Finished");
	    final Match updatedMatch = matchRepo.save(match);
        return ResponseEntity.ok(updatedMatch);
	}
	
	@DeleteMapping("/deletematch/{id}")
	public ResponseEntity<HttpStatus> deleteMath(@PathVariable("id") Long matchId) {
		System.out.println("to be deleted" + matchId);
		try {
			matchRepo.deleteById(matchId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/runningmatches")
	public ResponseEntity<?> getAllRunningMatches() {
		List<Match> matches = matchRepo.findRunningMatches();
		return new ResponseEntity<List<Match>>(matches, HttpStatus.OK);
	}
	
	@GetMapping("/matchsummary")
	public ResponseEntity<?> getMatchesSummary() {
		List<Match> matches = matchRepo.findMatchSummary();
		return new ResponseEntity<List<Match>>(matches, HttpStatus.OK);
	}
}
