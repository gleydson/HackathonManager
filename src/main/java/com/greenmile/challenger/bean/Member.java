package com.greenmile.challenger.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
public @Data class Member implements Comparable<Member> {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String phone;
	
	@NotNull
	private String shirtSize;
	
	@ManyToMany(mappedBy="members")
	private List<Team> teams;
	
	public Member() {
		this.teams = new ArrayList<>();
	}
	
	public Member(String name, String email, String phone, String shirtSize, List<Team> teams) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.shirtSize = shirtSize;
		this.teams = teams;
	}

	@Override
	public int compareTo(Member o) {
		return this.getEmail().compareTo(o.getEmail());
	}
	
}
