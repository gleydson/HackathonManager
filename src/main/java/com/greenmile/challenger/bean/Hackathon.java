package com.greenmile.challenger.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greenmile.challenger.bean.enumeration.HackathonStatus;

import lombok.Data;

@Entity @Data
public class Hackathon {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHackathon;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotEmpty	
	private String local;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotEmpty
	private Date date;
	
	@NotEmpty
	private Integer numberOfMembersPerTeam;
	
	@NotEmpty
	private Integer numberOfTeam;
	
	@NotEmpty @Enumerated(EnumType.STRING)
	private HackathonStatus status;
	
	@OneToMany
	private List<Team> teams;
	
	@ManyToMany
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
		this.name = null;
		this.description = null;
		this.local = null;
		this.date = null;
		this.numberOfMembersPerTeam = null;
		this.numberOfTeam = null;
		this.status = null;
		this.members = new ArrayList<>();
		this.teams = new ArrayList<>();
	}
	
	public void addTeam(Team team) {
		this.teams.add(team);
		this.getMembers().addAll(team.getMembers());
	}

}
