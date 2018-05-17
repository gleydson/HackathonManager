package com.greenmile.challenger.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @EqualsAndHashCode @ToString
public class Hackathon {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter @Column
	private Long id;
	
	@Getter @Setter @Column
	private String nameEvent;
	
	@Getter @Setter @Column
	private String description;
	
	@Getter @Setter @Column
	private String local;
	
	@Getter @Setter @Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
	
	@Getter @Setter @Column
	private int numberOfParticipantsPerTeam;
	
	@Getter @Setter @Column
	private int numberOfTeam;

}
