package com.greenmile.challenger.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
public @Data class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String name;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String email;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String phone;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String shirtSize;
	
	@ManyToMany(mappedBy="members")
	private List<Team> teams;
	
	public Member() {
		
	}
	
	public Member(String name, String email, String phone, String shirtSize, List<Team> teams) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.shirtSize = shirtSize;
		this.teams = teams;
	}
	
}
