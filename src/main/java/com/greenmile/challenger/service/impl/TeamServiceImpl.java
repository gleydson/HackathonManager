package com.greenmile.challenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.MemberTest;
import com.greenmile.challenger.repository.TeamRepository;
import com.greenmile.challenger.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository repository;
	
	@Override
	public ResponseEntity<Page<MemberTest>> getListAllTeams(Pageable pageable) {
		return new ResponseEntity<Page<MemberTest>>(this.repository.findAll(pageable), HttpStatus.OK);
	}

}
