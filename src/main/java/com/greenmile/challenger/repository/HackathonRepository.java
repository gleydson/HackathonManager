package com.greenmile.challenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenmile.challenger.bean.Hackathon;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> { 
	
	@Query("SELECT h FROM Hackathon h WHERE id = :id")
	public Hackathon getById(@Param("id") Long id);
	
}
