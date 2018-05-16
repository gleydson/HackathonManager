package com.greenmile.challenger.bean;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @EqualsAndHashCode @ToString
public class Members {
	
	@Getter @Setter @Column
	private String name;
	
	@Getter @Setter @Column
	private String email;
	
	@Getter @Setter @Column
	private String phone;
	
	@Getter @Setter @Column
	private String shirtSize;
	
}
