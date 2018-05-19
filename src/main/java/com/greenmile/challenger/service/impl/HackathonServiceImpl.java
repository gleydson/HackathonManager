package com.greenmile.challenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.exception.ResourceNotFoundException;
import com.greenmile.challenger.repository.HackathonRepository;
import com.greenmile.challenger.repository.TeamRepository;
import com.greenmile.challenger.service.HackathonService;

@Service
public class HackathonServiceImpl implements HackathonService {

	@Autowired
	private HackathonRepository hackathonRepository;
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public ResponseEntity<Hackathon> createHackathon(Hackathon hackathon) {
		hackathon.setStatus("OPEN");
		return new ResponseEntity<Hackathon>(this.hackathonRepository.save(hackathon), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Hackathon> getHackathonById(Long id) {
		this.verifyIsHackathonExists(id);
		return new ResponseEntity<Hackathon>(this.hackathonRepository.findById(id).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Hackathon> updateHackathon(Hackathon hackathon) {
		this.verifyIsHackathonExists(hackathon.getId());
		return new ResponseEntity<Hackathon>(this.hackathonRepository.save(hackathon), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> deleteHackathonById(Long id) {
		this.verifyIsHackathonExists(id);
		this.hackathonRepository.deleteById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Team>> getListAllTeams(Long idHackathon, Pageable pageable) {
		this.verifyIsHackathonExists(idHackathon);
		return new ResponseEntity<Page<Team>>(this.teamRepository.getListAllTeamsByIdOfHackathon(idHackathon, pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Team>> getListAllTeamsOrderByName(Long idHackathon, Pageable pageable) {
		this.verifyIsHackathonExists(idHackathon);
		return new ResponseEntity<Page<Team>>(this.teamRepository.getListAllTeamsOrderByName(idHackathon, pageable), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Page<Team>> getListAllTeamsOrderByRegistrationDate(Long idHackathon, Pageable pageable) {
		this.verifyIsHackathonExists(idHackathon);
		return new ResponseEntity<Page<Team>>(this.teamRepository.getListAllTeamsOrderByRegistrationDate(idHackathon, pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> endSubscriptions(Long id) {
		this.verifyIsHackathonExists(id);
		this.hackathonRepository.findById(id).get().setStatus("FINALIZED");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	private void verifyIsHackathonExists(Long id) {
		if (this.hackathonRepository.findById(id).get() == null) {
			throw new ResourceNotFoundException("Hackathon not found for id = " + id);
		}
	}

}
