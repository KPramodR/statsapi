package com.sportradar.statsapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sportradar.statsapi.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
	@Query("SELECT t FROM Match t WHERE t.status='Running'")
	List<Match> findRunningMatches();

	@Query("SELECT t FROM Match t WHERE t.status='Finished'")
	List<Match> findMatchSummary();
}
