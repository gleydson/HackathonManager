package com.greenmile.challenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Organizer;
import com.greenmile.challenger.repository.OrganizerRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private OrganizerRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Organizer organizer = repository.findByUsername(username);
		String ORGANIZER_NOT_FOUND_EXCEPTION = "Organizer not found!";
		if (organizer == null) throw new UsernameNotFoundException(ORGANIZER_NOT_FOUND_EXCEPTION );
		return organizer;
	}

}