package com.greenmile.challenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenmile.challenger.bean.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

	public Organizer findByUsername(String username);

}
