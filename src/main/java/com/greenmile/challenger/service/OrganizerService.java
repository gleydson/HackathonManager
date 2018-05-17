package com.greenmile.challenger.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;

public interface OrganizerService {

	public ResponseEntity<Hackathon> createEvent(Hackathon event);
	public ResponseEntity<Page<Team>> getListAllTeams(Pageable pageable);
	public ResponseEntity<List<Team>> getListByName(String name);
	public ResponseEntity<List<Team>> getListByRegistrationDate(Date date);
	public ResponseEntity<Boolean> endEvent();
	
}
