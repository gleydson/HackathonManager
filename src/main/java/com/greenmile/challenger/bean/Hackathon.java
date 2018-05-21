package com.greenmile.challenger.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greenmile.challenger.bean.enumeration.HackathonStatus;

import lombok.Data;

@Entity
public @Data class Hackathon {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull	
	private String local;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
	
	@NotNull
	private Integer numberOfMembersPerTeam;
	
	@NotNull
	private Integer numberOfTeam;
	
	@NotNull @Enumerated(EnumType.STRING)
	private HackathonStatus status;
	
	@OneToMany
	private List<Team> teams;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    	name="hackathon_has_member",
    	joinColumns= {
    		@JoinColumn(name="hackathon_id")
    	},
    	inverseJoinColumns= {
    		@JoinColumn(name="member_id")
    	}
    )
	private List<Member> members; 
	
	public Hackathon() {
		this.members = new ArrayList<>();
		this.teams = new ArrayList<>();
	}
	
	public Hackathon(String name, 
					String description, 
					String local, 
					Date date, 
					Integer numberOfMembersPerTeam,
					Integer numberOfTeam,
					HackathonStatus status,
					List<Team> teams,
					List<Member> members) {
		this.name = name;
		this.description = description;
		this.local = local;
		this.date = date;
		this.numberOfMembersPerTeam = numberOfMembersPerTeam;
		this.numberOfTeam = numberOfTeam;
		this.status = status;
		this.teams = teams;
		this.members = members;
	}
	
	public void addTeam(Team team) {
		this.teams.add(team);
		team.setHackathon(this);
		this.getMembers().addAll(team.getMembers());
	}

}
