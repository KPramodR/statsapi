package com.sportradar.statsapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sportradar.statsapi.service.MatchService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MatchController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MatchController.class);
	
	/**
	 * 
	 */
	@Autowired
    private MatchService matchService;
	
	/**
	 * @param match
	 * @return
	 */
	@PostMapping("/addmatch")
	public ResponseEntity<Match> addMatch(HttpServletRequest httpReq, @RequestBody Match match) {
		logger.info("New Match add request received from :" + httpReq.getRemoteHost() + ", " + httpReq.getRemoteUser());
		return ResponseEntity.ok(this.matchService.addMatch(match));
	}
	
	/**
	 * @param matchId
	 * @param matchDetails
	 * @return
	 */
	@PutMapping("/updatematch/{id}")
	public ResponseEntity < Match > updateMatchScore(HttpServletRequest httpReq, @PathVariable("id") Long matchId, @RequestBody Match matchDetails) {
		logger.info("Update Match score request received from :" + httpReq.getRemoteHost() + ", " + httpReq.getRemoteUser());
        return ResponseEntity.ok(this.matchService.updateMatchScore(matchId, matchDetails));
	}
	
	/**
	 * @param matchId
	 * @return
	 */
	@PutMapping("/finishmatch/{id}")
	public ResponseEntity < Match > updateMatchStatus(HttpServletRequest httpReq, @PathVariable("id") Long matchId) {
		logger.info("Update Match status request received from :" + httpReq.getRemoteHost() + ", " + httpReq.getRemoteUser());
		return ResponseEntity.ok(this.matchService.updateMatchStatus(matchId));
	}
	
	/**
	 * @param matchId
	 * @return
	 */
	@DeleteMapping("/deletematch/{id}")
	public ResponseEntity<HttpStatus> deleteMatch(HttpServletRequest httpReq, @PathVariable("id") Long matchId) {
		logger.info("Date Match request received from :" + httpReq.getRemoteHost() + ", " + httpReq.getRemoteUser());
		if( this.matchService.deleteMatch(matchId)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @return
	 */
	@GetMapping("/runningmatches")
	public ResponseEntity<?> getAllRunningMatches(HttpServletRequest httpReq) {
		logger.info("All running matches list request received from :" + httpReq.getRemoteHost() + ", " + httpReq.getRemoteUser());
		return ResponseEntity.ok(this.matchService.getAllRunningMatches());
	}
	
	/**
	 * @return
	 */
	@GetMapping("/matchsummary")
	public ResponseEntity<?> getMatchesSummary(HttpServletRequest httpReq) {
		logger.info("All finished matches list request received from :" + httpReq.getRemoteHost() + ", " + httpReq.getRemoteUser());
		return ResponseEntity.ok(this.matchService.getMatchesSummary());
	}
}
