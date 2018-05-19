package com.greenmile.challenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenmile.challenger.bean.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	
	public Team findByUsername(String username);
	
}
