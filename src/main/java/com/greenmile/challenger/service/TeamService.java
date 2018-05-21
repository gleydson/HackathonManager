package com.greenmile.challenger.service;

import org.springframework.http.ResponseEntity;

import com.greenmile.challenger.bean.Team;

public interface TeamService {
	public ResponseEntity<Team> subscribe(Long idHackathon, Team team);
	public ResponseEntity<Boolean> unsubscribe(Long idTeam);
}
