package com.greenmile.challenger.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @EqualsAndHashCode @ToString
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private @Getter @Setter Long id;
	
	@NotNull
	private @Getter @Setter String name;
	
	@NotNull
	private @Getter @Setter String email;
	
	@NotNull
	private @Getter @Setter String phone;
	
	@NotNull
	private @Getter @Setter String shirtSize;
	
	@NotNull @ManyToOne
	private @Getter @Setter Team team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShirtSize() {
		return shirtSize;
	}

	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
}
