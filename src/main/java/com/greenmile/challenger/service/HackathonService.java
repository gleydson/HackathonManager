package com.greenmile.challenger.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Team;

public interface HackathonService {
	public Hackathon createHackathon(Hackathon hackathon);
	public Hackathon getHackathonById(Long id);
	public List<Hackathon> getAllHackathon();
	public Hackathon updateHackathon(Hackathon hackathon);
	public Boolean deleteHackathonById(Long id);
	public Page<Team> getListAllTeams(Long id, Pageable pageable);
	public Page<Team> getListAllTeamsOrderByName(Long id, Pageable pageable);
	public Page<Team> getListAllTeamsOrderByRegistrationDate(Long id, Pageable pageable);
	public Boolean endSubscriptions(Long id);
}
