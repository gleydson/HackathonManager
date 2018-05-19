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
import com.greenmile.challenger.exception.ConflictException;
import com.greenmile.challenger.repository.HackathonRepository;
import com.greenmile.challenger.repository.MemberRepository;
import com.greenmile.challenger.repository.TeamRepository;
import com.greenmile.challenger.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private HackathonRepository hackathonRepository;

	@Override
	public ResponseEntity<Team> subscribe(Team team) {
		Hackathon hackathon = this.hackathonRepository.findById(team.getHackathon().getId()).get();
		Set<Member> allMembers = new HashSet<Member>(hackathon.getMembers());
		for (Member m : team.getMembers()) {
			if (allMembers.contains(m)) {				
				throw new ConflictException("This member is already participating in this hackathon in another team");
			}
		}
		
		Set<Member> membersThisTeam = new HashSet<Member>(team.getMembers());
		if (membersThisTeam.size() != team.getMembers().size()) {
			throw new ConflictException("There can be no duplicate members");
		}
		
		this.memberRepository.saveAll(team.getMembers());
		
		return new ResponseEntity<>(this.teamRepository.save(team), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> unsubscribe(Long id) {
		this.teamRepository.deleteById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
