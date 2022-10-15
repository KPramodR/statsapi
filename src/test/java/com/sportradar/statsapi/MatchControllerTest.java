package com.sportradar.statsapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sportradar.statsapi.entity.Match;
import com.sportradar.statsapi.repo.MatchRepository;

@SpringBootTest
public class MatchControllerTest {

	@Autowired
	private MatchRepository matchRepository;
	
	@Test
	void addMatch() {
		boolean actualResult = false;
		Match match = new Match("Norway",0,"Sweden",0);
		matchRepository.save(match);
		Optional<Match> returnedObject = matchRepository.findById((long) 1);
		if(returnedObject != null) {
			actualResult = true;
		}
		assertThat(actualResult).isTrue();
		
	}
	
}
