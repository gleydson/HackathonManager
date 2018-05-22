package com.greenmile.challenger.service.impl;

import static com.greenmile.challenger.util.ConstantsUtil.EXCEPTION_HACKATHON_NOT_FOUND;
import static com.greenmile.challenger.util.ConstantsUtil.EXCEPTION_MEMBER_IS_ALREADY_PARTICIPATING_IN_THIS_HACKATHON;
import static com.greenmile.challenger.util.ConstantsUtil.EXCEPTION_MEMBER_IS_NOT_REGISTERED_IN_THE_SYSTEM;
import static com.greenmile.challenger.util.ConstantsUtil.EXCEPTION_NAME_OF_THIS_TEAM_IS_ALREADY_IN_USE;
import static com.greenmile.challenger.util.ConstantsUtil.EXCEPTION_NUMBER_OF_MEMBERS_GREATER_THAN_ALLOWED;
import static com.greenmile.challenger.util.ConstantsUtil.EXCEPTION_TEAM_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Member;
import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.exception.BadRequestException;
import com.greenmile.challenger.exception.ResourceNotFoundException;
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
	public ResponseEntity<Team> subscribe(Long idHackathon, Team team) {

		if (!hackathonExist(idHackathon)) {
			throw new ResourceNotFoundException(EXCEPTION_HACKATHON_NOT_FOUND);
		}
		
		Hackathon hackathon = this.hackathonRepository.findById(idHackathon).get();
		
		if (this.theAmountOfMembersIsOk(team, hackathon)) {
			throw new BadRequestException(EXCEPTION_NUMBER_OF_MEMBERS_GREATER_THAN_ALLOWED);
		}
		
		if (!this.membersNotExist(team)) {
			throw new BadRequestException(EXCEPTION_MEMBER_IS_NOT_REGISTERED_IN_THE_SYSTEM);
		}

		if (this.areTheMembersAlreadyIsThisHackathon(team, hackathon)) {
			throw new BadRequestException(EXCEPTION_MEMBER_IS_ALREADY_PARTICIPATING_IN_THIS_HACKATHON);
		}
		
		if (this.thisTeamNameIsAlreadyInUse(team, hackathon)) {
			throw new BadRequestException(EXCEPTION_NAME_OF_THIS_TEAM_IS_ALREADY_IN_USE);
		}
		
		team.addMember(this.getMembersListOfDataBase(team));
		team.addHackathon(hackathon);
		Team teamRescue = this.teamRepository.save(team);
		
		hackathon.addTeam(teamRescue);
		for (Member m : team.getMembers()) {
			m.addTeam(teamRescue);
		}
		
		this.memberRepository.saveAll(team.getMembers());
		this.hackathonRepository.save(hackathon);
		
		return new ResponseEntity<Team>(teamRescue, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Team> getTeam(Long idTeam) {
		if (!this.teamRepository.existsById(idTeam)) {
			throw new ResourceNotFoundException(EXCEPTION_TEAM_NOT_FOUND);
		}
		return new ResponseEntity<Team>(this.teamRepository.findById(idTeam).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> unsubscribe(Long id) {
		this.teamRepository.deleteById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	private Boolean hackathonExist(Long idHackathon) {
		return this.hackathonRepository.existsById(idHackathon);
	}
	
	private Boolean theAmountOfMembersIsOk(Team team, Hackathon hackathon) {
		return team.getMembers().size() > hackathon.getNumberOfMembersPerTeam();
	}
	
	private Boolean membersNotExist(Team team) {
		for (Member member : this.getMembersListOfDataBase(team)) {
			if (member == null) {
				return false;
			}
			if (!this.memberRepository.existsById(member.getId())) {
				return false;
			}
		}
		return true;
	}
	
	private List<Member> getMembersListOfDataBase(Team team) {
		List<Member> members = new ArrayList<>();
		for (Member member : team.getMembers()) {
			members.add(this.memberRepository.findByEmail(member.getEmail()));
		}
		return members;
	}
	
	private Boolean areTheMembersAlreadyIsThisHackathon(Team team, Hackathon hackathon) {
		for (Member member : this.getMembersListOfDataBase(team)) {
			if (hackathon.getMembers().contains(member)) {
				return true;
			}
		}
		return false;
	}
	
	private Boolean thisTeamNameIsAlreadyInUse(Team team, Hackathon hackathon) {
		for (Team t : hackathon.getTeams()) {
			if (t.getName().equals(team.getName())) {
				return true;
			}
		}
		return false;
	}

}
