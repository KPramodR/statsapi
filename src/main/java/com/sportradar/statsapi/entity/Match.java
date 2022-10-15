package com.sportradar.statsapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MATCH")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name="ID")
	private Long ID;
	
	@Column(name = "HOMETEAM")
	private String homeTeam;
	
	@Column(name = "AWAYTEAM")
	private String awayTeam;
	
	@Column(name = "HOMETEAMSCORE")
	private int homeTeamScore;
	
	@Column(name = "AWAYTEAMSCORE")
	private int awayTeamScore;
	
	@Column(name = "STATUS")
	private String status;

	public Match() {}
	public Match(String homeTeam, int homeTeamScore, String awayTeam, int awayTeamScore) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.status="Running";
	}
	
	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}

	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
