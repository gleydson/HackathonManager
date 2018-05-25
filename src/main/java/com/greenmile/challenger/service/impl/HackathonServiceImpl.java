package com.greenmile.challenger.service.impl;

import static com.greenmile.challenger.util.ConstantsUtil.EXCEPTION_HACKATHON_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.bean.enumeration.HackathonStatus;
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
	public Hackathon createHackathon(Hackathon hackathon) {
		hackathon.setStatus(HackathonStatus.OPEN);
		return this.hackathonRepository.save(hackathon);
	}

	@Override
	public Hackathon getHackathonById(Long id) {
		this.verifyIsHackathonExists(id);
		return this.hackathonRepository.findById(id).get();
	}
	
	@Override
	public List<Hackathon> getAllHackathon() {
		return this.hackathonRepository.findAll();
	}

	@Override
	public Hackathon updateHackathon(Hackathon hackathon) {
		this.verifyIsHackathonExists(hackathon.getIdHackathon());
		return this.hackathonRepository.save(hackathon);
	}

	@Override
	public Boolean deleteHackathonById(Long id) {
		this.verifyIsHackathonExists(id);
		this.hackathonRepository.deleteById(id);
		return true;
	}

	@Override
	public Page<Team> getListAllTeams(Long idHackathon, Pageable pageable) {
		this.verifyIsHackathonExists(idHackathon);
		return this.teamRepository.findFirst10ByIdHackathon(idHackathon, pageable);
	}

	@Override
	public Page<Team> getListAllTeamsOrderByName(Long idHackathon, Pageable pageable) {
		this.verifyIsHackathonExists(idHackathon);
		return this.teamRepository.findFirst10ByIdHackathonOrderByName(idHackathon, pageable);
	}
	
	@Override
	public Page<Team> getListAllTeamsOrderByRegistrationDate(Long idHackathon, Pageable pageable) {
		this.verifyIsHackathonExists(idHackathon);
		return this.teamRepository.findFirst10ByIdHackathonOrderByRegistrationDate(idHackathon, pageable);
	}

	@Override
	public Boolean endSubscriptions(Long id) {
		this.verifyIsHackathonExists(id);
		Hackathon hackathon = this.hackathonRepository.findById(id).get();
		hackathon.setStatus(HackathonStatus.CLOSED);
		return true;
	}
	
	private void verifyIsHackathonExists(Long id) {
		if (!this.hackathonRepository.existsById(id)) {
			throw new ResourceNotFoundException(EXCEPTION_HACKATHON_NOT_FOUND);
		}
	}

}
