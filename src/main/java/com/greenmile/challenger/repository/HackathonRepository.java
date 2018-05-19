package com.greenmile.challenger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
	
	@Query("SELECT h.teams FROM Hackathon h WHERE h.id = :idHackathon")
	public Page<Team> getListAllTeamsByIdOfHackathon(@Param("idHackathon") Long idHackathon, Pageable pageable);
	
	@Query("SELECT h.teams FROM Hackathon h WHERE h.id = :idHackathon ORDER BY h.teams.name")
	public Page<Team> getListAllTeamsOrderByName(@Param("idHackathon") Long id, Pageable pageable);
	
	@Query("SELECT h.teams FROM Hackathon h WHERE h.id = :idHackathon ORDER BY h.teams.registrationDate")
	public Page<Team> getListAllTeamsOrderByRegistrationDate(@Param("idHackathon") Long id, Pageable pageable);

}
