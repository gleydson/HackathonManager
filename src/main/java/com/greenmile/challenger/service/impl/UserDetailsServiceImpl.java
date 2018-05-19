package com.greenmile.challenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.repository.TeamRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TeamRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Team team = repository.findByUsername(username);
		String ORGANIZER_NOT_FOUND_EXCEPTION = "Team not found!";
		if (team == null) throw new UsernameNotFoundException(ORGANIZER_NOT_FOUND_EXCEPTION );
		return team;
	}

}