package com.greenmile.challenger.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenmile.challenger.bean.enumeration.ShirtSize;

import lombok.Data;

@Entity
public @Data class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String pathPhoto;
	
	private String name;
	
	@NotNull
	private String email;
	
	private String phone;
	
	private ShirtSize shirtSize;
	
	@ManyToMany(mappedBy="members") @JsonIgnore
	private List<Team> teams;
	
	public Member() {
		this.teams = new ArrayList<>();
	}
	
	public void addTeam(Team team) {
		this.teams.add(team);
	}
	
}
