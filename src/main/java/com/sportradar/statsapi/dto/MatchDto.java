package com.sportradar.statsapi.dto;

import java.util.List;

import com.sportradar.statsapi.entity.Match;

public class MatchDto {
	List<Match> matches;
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
}
