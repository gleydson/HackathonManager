package com.greenmile.challenger.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenmile.challenger.bean.enumeration.ShirtSize;

import lombok.Data;

@Entity @Data
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String pathPhoto;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String phone;
	
	@NotEmpty @Enumerated(EnumType.STRING)
	private ShirtSize shirtSize;
	
	@ManyToMany(mappedBy="members") @JsonIgnore
	private List<Team> teams;
	
	public Member() {
		this.pathPhoto = null;
		this.name = null;
		this.email = null;
		this.phone = null;
		this.shirtSize = null;
		this.teams = new ArrayList<>();
	}
	
	public void addTeam(Team team) {
		this.teams.add(team);
	}
	
}
