package com.greenmile.challenger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenmile.challenger.bean.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {	
	
	public Team findByUsername(String username);
	
	@Query("SELECT t FROM Team t WHERE t.hackathon.id = :idHackathon")
	public Page<Team> getListAllTeamsByIdOfHackathon(@Param("idHackathon") Long idHackathon, Pageable pageable);
	
	@Query("SELECT t FROM Team t WHERE t.hackathon.id = :idHackathon ORDER BY t.name")
	public Page<Team> getListAllTeamsOrderByName(@Param("idHackathon") Long id, Pageable pageable);
	
	@Query("SELECT t FROM Team t WHERE t.hackathon.id = :idHackathon ORDER BY t.registrationDate")
	public Page<Team> getListAllTeamsOrderByRegistrationDate(@Param("idHackathon") Long id, Pageable pageable);

	
}
