package com.greenmile.challenger.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public @Data class Team implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @Column(unique = true)
	private String name;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date registrationDate;
	
	@ManyToOne @JsonIgnore
	private Hackathon hackathon;
	
	@ManyToMany @JsonIgnore
    @JoinTable(
    	name="team_has_member",
    	joinColumns= {
    		@JoinColumn(name="team_id")
    	},
    	inverseJoinColumns= {
    		@JoinColumn(name="member_id")
    	}
    )
	private List<Member> members;
	
	public Team() {
		this.members = new ArrayList<>();
	}
	
	public Team(String name, String username, String password, Date registrationDate, Hackathon hackathon, List<Member> members) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
		this.hackathon = hackathon;
		this.members = members;
	}
	
	public void addMember(Member member) {
		this.members.add(member);
		member.getTeams().add(this);
	}
	
	public void addMember(List<Member> members) {
		this.setMembers(members);
		for (Member member : members) {
			member.getTeams().add(this);
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
