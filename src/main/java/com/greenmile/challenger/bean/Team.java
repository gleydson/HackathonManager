package com.greenmile.challenger.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @EqualsAndHashCode @ToString
public class Team {
	
	@Getter @Setter @Column(unique = true)
	private String name;
	
	@Getter @Setter @Column	
	private List<Members> members;

}
