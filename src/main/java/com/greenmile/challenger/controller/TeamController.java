package com.greenmile.challenger.controller;

import static com.greenmile.challenger.util.ConstantsUtil.TEAM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.service.TeamService;

@RestController
@RequestMapping(TEAM)
public class TeamController {
	
	@Autowired
	private TeamService service;
	
	@PostMapping
	public ResponseEntity<Team> subscribe(@RequestBody Team team) {
		return this.service.subscribe(team);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> unsubscribe(@PathVariable Long id) {
		return this.service.unsubscribe(id);
	}
}
