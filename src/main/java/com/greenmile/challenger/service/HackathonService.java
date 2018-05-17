package com.greenmile.challenger.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;

public interface HackathonService {

	ResponseEntity<Hackathon> createEvent(Hackathon event);
	ResponseEntity<List<Team>> getListByName(String name);
	ResponseEntity<List<Team>> getListByRegistrationDate(Date date);
	ResponseEntity<Boolean> endEvent();

}
