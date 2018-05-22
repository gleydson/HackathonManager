package com.greenmile.challenger.controller;

import static com.greenmile.challenger.util.ConstantsUtil.API_HACKATHON;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.service.HackathonService;

@RestController
@Transactional
@RequestMapping(API_HACKATHON)
public class HackathonController {

	@Autowired
	private HackathonService hackathonService;
	
	@PostMapping
	public ResponseEntity<Hackathon> createHackathon(@RequestBody Hackathon hackathon) {	
		return new ResponseEntity<Hackathon>(this.hackathonService.createHackathon(hackathon), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hackathon> getHackathonById(@PathVariable Long id) {
		return new ResponseEntity<Hackathon>(this.hackathonService.getHackathonById(id), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Hackathon>> getAllHackathon() {
		return new ResponseEntity<List<Hackathon>>(this.hackathonService.getAllHackathon(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Hackathon> updateHackathon(@RequestBody Hackathon hackathon) {
		return new ResponseEntity<Hackathon>(this.hackathonService.updateHackathon(hackathon), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idHackathon}")
	public ResponseEntity<Boolean> deleteHackathonById(@PathVariable Long idHackathon) {
		return new ResponseEntity<Boolean>(this.hackathonService.deleteHackathonById(idHackathon), HttpStatus.OK);
	}
	
	@GetMapping("/{idHackathon}/get-list-all-teams")
	public ResponseEntity<Page<Team>> getListAllTeamsByIdOfHackathon(@PathVariable Long idHackathon, Pageable pageable) {
		return new ResponseEntity<Page<Team>>(this.hackathonService.getListAllTeams(idHackathon, pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{idHackathon}/get-list-all-teams-order-by-name")
	public ResponseEntity<Page<Team>> getListAllTeamsByIdOfHackathonOrderByNome(@PathVariable Long idHackathon, Pageable pageable) {
		return new ResponseEntity<Page<Team>>(this.hackathonService.getListAllTeamsOrderByName(idHackathon, pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{idHackathon}/get-list-all-teams-order-by-registration-date")
	public ResponseEntity<Page<Team>> getListAllTeamsByIdOfHackathonOrderByRegistrationDate(@PathVariable Long idHackathon, Pageable pageable) {
		return new ResponseEntity<Page<Team>>(this.hackathonService.getListAllTeamsOrderByRegistrationDate(idHackathon, pageable), HttpStatus.OK);
	}
	
	@GetMapping("/endSubscription/{idHackathon}")
	public ResponseEntity<Boolean> endSubscriptions(@PathVariable Long idHackathon) {
		return new ResponseEntity<Boolean>(this.hackathonService.endSubscriptions(idHackathon), HttpStatus.OK);
	}
	
}
