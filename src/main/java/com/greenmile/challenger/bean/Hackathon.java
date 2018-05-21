package com.greenmile.challenger.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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

import lombok.Data;

@Entity
public @Data class Hackathon {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@NotNull
	private String status;
	
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
	
	public Hackathon() { }
	
	public Hackathon(String name, 
					String description, 
					String local, 
					Date date, 
					Integer numberOfMembersPerTeam,
					Integer numberOfTeam,
					String status,
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

}
