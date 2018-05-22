package com.greenmile.challenger.controller;

import static com.greenmile.challenger.util.ConstantsUtil.API_TEAM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.service.TeamService;

@RestController
@RequestMapping(API_TEAM)
public class TeamController {
	
	@Autowired
	private TeamService service;
	
	@PostMapping("/{idHackathon}/subscribe")
	public ResponseEntity<Team> subscribe(@PathVariable Long idHackathon, @RequestBody Team team) {
		return new ResponseEntity<Team>(this.service.subscribe(idHackathon, team), HttpStatus.OK);
	}
	
	@GetMapping("/{idTeam}")
	public ResponseEntity<Team> getTeam(@PathVariable Long idTeam) {
		return new ResponseEntity<Team>(this.service.getTeam(idTeam), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idTeam}")
	public ResponseEntity<Boolean> unsubscribe(@PathVariable Long idTeam) {
		return new ResponseEntity<Boolean>(this.service.unsubscribe(idTeam), HttpStatus.OK);
	}
	
}
