package com.greenmile.challenger.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
public @Data class Hackathon {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String nameEvent;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String description;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String local;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private int numberOfParticipantsPerTeam;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private int numberOfTeam;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String status;
	
	@OneToMany
	private List<Team> teams;
	
	@ManyToMany
    @JoinTable(
    	name="hackathon_has_member",
    	joinColumns= {
    		@JoinColumn(name="hackathon_id")
    	},
    	inverseJoinColumns= {
    		@JoinColumn(name="member_id")
    	}
    )
	private List<Member> members; 

}
