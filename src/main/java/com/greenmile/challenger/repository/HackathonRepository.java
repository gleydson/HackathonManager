package com.greenmile.challenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenmile.challenger.bean.Hackathon;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> { 
	
}
