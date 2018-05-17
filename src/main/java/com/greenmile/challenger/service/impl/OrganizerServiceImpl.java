package com.greenmile.challenger.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.service.HackathonService;
import com.greenmile.challenger.service.OrganizerService;
import com.greenmile.challenger.service.TeamService;

@Service
public class OrganizerServiceImpl implements OrganizerService {

	@Autowired
	private HackathonService eventService;
	
	@Autowired
	private TeamService teamService;
	
	@Override
	public ResponseEntity<Hackathon> createEvent(Hackathon event) {
		return this.eventService.createEvent(event);
	}

	@Override
	public ResponseEntity<Page<Team>> getListAllTeams(Pageable pageable) {
		return this.teamService.getListAllTeams(pageable);
	}

	@Override
	public ResponseEntity<List<Team>> getListByName(String name) {
		return this.eventService.getListByName(name);
	}

	@Override
	public ResponseEntity<List<Team>> getListByRegistrationDate(Date date) {
		return this.eventService.getListByRegistrationDate(date);
	}

	@Override
	public ResponseEntity<Boolean> endEvent() {
		return this.eventService.endEvent();
	}

}
