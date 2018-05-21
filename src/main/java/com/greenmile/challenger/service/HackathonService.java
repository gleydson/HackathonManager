package com.greenmile.challenger.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;

public interface HackathonService {
	public ResponseEntity<Hackathon> createHackathon(Hackathon hackathon);
	public ResponseEntity<Hackathon> getHackathonById(Long id);
	public ResponseEntity<List<Hackathon>> getAllHackathon();
	public ResponseEntity<Hackathon> updateHackathon(Hackathon hackathon);
	public ResponseEntity<Boolean> deleteHackathonById(Long id);
	public ResponseEntity<Page<Team>> getListAllTeams(Long id, Pageable pageable);
	public ResponseEntity<Page<Team>> getListAllTeamsOrderByName(Long id, Pageable pageable);
	public ResponseEntity<Page<Team>> getListAllTeamsOrderByRegistrationDate(Long id, Pageable pageable);
	public ResponseEntity<Boolean> endSubscriptions(Long id);
}
