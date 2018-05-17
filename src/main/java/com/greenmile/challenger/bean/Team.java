package com.greenmile.challenger.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @EqualsAndHashCode @ToString
public class Team {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private @Getter @Setter Long id;
	
	@NotNull @Column(unique = true)
	private @Getter @Setter String name;
	
	@OneToMany
	private @Getter @Setter List<Member> member;

}
