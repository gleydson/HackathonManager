package com.greenmile.challenger.controller;

import static com.greenmile.challenger.util.ConstantsUtil.HACKATHON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(HACKATHON)
public class HackathonController {

	@Autowired
	private HackathonService hackathonService;
	
	@PostMapping("/create-hackathon")
	public ResponseEntity<Hackathon> createHackathon(@RequestBody Hackathon hackathon) {
		return this.hackathonService.createHackathon(hackathon);
	}
	
	@GetMapping("/get-hackathon/{id}")
	public ResponseEntity<Hackathon> getHackathonById(@PathVariable Long id) {
		return this.hackathonService.getHackathonById(id);
	}
	
	@PutMapping("/update-hackathon")
	public ResponseEntity<Hackathon> updateHackathon(@RequestBody Hackathon hackathon) {
		return this.hackathonService.updateHackathon(hackathon);
	}
	
	@DeleteMapping("/delete-hackathon/idHackathon")
	public ResponseEntity<Boolean> deleteHackathonById(@PathVariable Long idHackathon) {
		return this.hackathonService.deleteHackathonById(idHackathon);
	}
	
	@GetMapping("/{idHackathon}/get-list-all-teams")
	public ResponseEntity<Page<Team>> getListAllTeamsByIdOfHackathon(@PathVariable Long idHackathon, Pageable pageable) {
		return this.hackathonService.getListAllTeams(idHackathon, pageable);
	}
	
	@GetMapping("/{idHackathon}/get-list-all-teams-order-by-name")
	public ResponseEntity<Page<Team>> getListAllTeamsByIdOfHackathonOrderByNome(@PathVariable Long idHackathon, Pageable pageable) {
		return this.hackathonService.getListAllTeamsOrderByName(idHackathon, pageable);
	}
	
	@GetMapping("/{idHackathon}/get-list-all-teams-order-by-registration-date")
	public ResponseEntity<Page<Team>> getListAllTeamsByIdOfHackathonOrderByRegistrationDate(@PathVariable Long idHackathon, Pageable pageable) {
		return this.hackathonService.getListAllTeamsOrderByRegistrationDate(idHackathon, pageable);
	}
	
	@GetMapping("/endSubscription/{idHackathon}")
	public ResponseEntity<Boolean> endSubscriptions(@PathVariable Long idHackathon) {
		return this.hackathonService.endSubscriptions(idHackathon);
	}
	

	
}
