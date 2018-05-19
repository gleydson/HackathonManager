package com.greenmile.challenger.bean;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
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

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull @Column(unique = true)
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String name;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	private String username;
	
	@NotNull @JsonIgnore
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String password;
	
	@NotNull @NotEmpty(message = "Preenchimento Obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date registrationDate;
	
	@ManyToOne
	private Hackathon hackathon;
	
	@ManyToMany
    @JoinTable(
    	name="team_has_member",
    	joinColumns= {
    		@JoinColumn(name="team_id")
    	},
    	inverseJoinColumns= {
    		@JoinColumn(name="member_id")
    	}
    )
	private Set<Member> members;

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
