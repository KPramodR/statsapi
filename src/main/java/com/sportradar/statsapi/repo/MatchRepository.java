package com.sportradar.statsapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sportradar.statsapi.entity.Match;

/**
 * @author pkini
 *
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
	/**
	 * @return
	 */
	@Query("SELECT t FROM Match t WHERE t.status='Running'")
	List<Match> findRunningMatches();

	/**
	 * @return
	 */
	@Query("SELECT t FROM Match t WHERE t.status='Finished' order by ID asc" )
	List<Match> findMatchSummary();
}
