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
	@Getter @Setter
	private Long id;
	
	@Getter @Setter @NotNull
	private String name;
	
	@Getter @Setter @NotNull
	private String email;
	
	@Getter @Setter @NotNull
	private String phone;
	
	@Getter @Setter @NotNull
	private String shirtSize;
	
	@Getter @Setter
	@NotNull @ManyToOne
	private Team team;
	
}
