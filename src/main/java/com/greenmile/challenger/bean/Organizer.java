package com.greenmile.challenger.bean;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @EqualsAndHashCode @ToString
public class Organizer {
	
	@Getter @Setter @Column
	private String name;

	@Getter @Setter @Column(unique = true)
	private String username;
	
	@Getter @Setter @Column 
	private String password;
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}

}
