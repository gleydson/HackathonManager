package com.greenmile.challenger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenmile.challenger.bean.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {	
	
	Team findByUsername(String username);
	
	Page<Team> findFirst10ByIdHackathon(Long IdHackathon, Pageable pageable);
	
	Page<Team> findFirst10ByIdHackathonOrderByName(Long idHackathon, Pageable pageable);
	
	Page<Team> findFirst10ByIdHackathonOrderByRegistrationDate(Long idHackathon, Pageable pageable);
	
}
