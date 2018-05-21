package com.greenmile.challenger.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Member;
import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.exception.BadRequestException;
import com.greenmile.challenger.repository.HackathonRepository;
import com.greenmile.challenger.repository.TeamRepository;
import com.greenmile.challenger.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private HackathonRepository hackathonRepository;

	@Override
	public ResponseEntity<Team> subscribe(Team team) {
		Hackathon hackathon = this.hackathonRepository.findById(team.getHackathon().getId()).get();
		
		for (Team t : hackathon.getTeams()) {
			if (t.getName().equals(team.getName())) {
				throw new BadRequestException("This team name is already being used");
			}
		}
		
		if (team.getMembers().size() > hackathon.getNumberOfMembersPerTeam()) {
			throw new BadRequestException("Number of participants greater than allowed");
		}

		for (Member mTeam : team.getMembers()) {
			for (Member mHack : hackathon.getMembers()) {
				if (mTeam.getName().equals(mHack.getName()) && mTeam.getEmail().equals(mHack.getEmail())) {					
					throw new BadRequestException("This member is already participating in this hackathon in another team");
				}
			}
		}


		Set<Member> membersThisTeam = new HashSet<Member>(team.getMembers());
		if (membersThisTeam.size() != team.getMembers().size()) {
			throw new BadRequestException("There can be no duplicate members");
		}

		Team t = this.teamRepository.save(team);
		
		hackathon.getTeams().add(t);
		hackathon.getMembers().addAll(team.getMembers());
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> unsubscribe(Long id) {
		this.teamRepository.deleteById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
