package com.sportradar.statsapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportradar.statsapi.dto.MatchDto;
import com.sportradar.statsapi.entity.Match;
import com.sportradar.statsapi.repo.MatchRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MatchController {
	
	@Autowired
	private MatchRepository matchRepo;
	
	@GetMapping("/runningmatches")
	public ResponseEntity<?> getAllRunningMatches() {
		List<Match> matches = matchRepo.findRunningMatches();
		MatchDto matchDTO = new MatchDto(); 
		matchDTO.setMatches(matches);
		return new ResponseEntity<MatchDto>(matchDTO, HttpStatus.OK);
	}
	
	@GetMapping("/matchsummary")
	public ResponseEntity<?> getMatchesSummary() {
		List<Match> matches = matchRepo.findMatchSummary();
		MatchDto matchDTO = new MatchDto(); 
		matchDTO.setMatches(matches);
		return new ResponseEntity<MatchDto>(matchDTO, HttpStatus.OK);
	}
	
	@PostMapping("/addmatch")
	public Match addMatch(@RequestBody Match match) {
		return matchRepo.save(match);
	}
	
	@PutMapping("/match/{id}")
	public ResponseEntity < Match > updateMatch(@PathVariable(value = "id") Long matchId, @RequestBody Match matchDetails) {
		Optional<Match> matchObject = matchRepo.findById(matchId);
		Match match = matchObject.get();
		match.setHomeTeamScore(matchDetails.getHomeTeamScore());
		match.setAwayTeamScore(matchDetails.getAwayTeamScore());
	    final Match updatedMatch = matchRepo.save(match);
        return ResponseEntity.ok(updatedMatch);
	}
	
	@PutMapping("/finishMatch/{id}")
	public ResponseEntity < Match > updateMatch(@PathVariable(value = "id") Long matchId) {
		Optional<Match> matchObject = matchRepo.findById(matchId);
		Match match = matchObject.get();
		match.setStatus("Finished");
	    final Match updatedMatch = matchRepo.save(match);
        return ResponseEntity.ok(updatedMatch);
	}
}
