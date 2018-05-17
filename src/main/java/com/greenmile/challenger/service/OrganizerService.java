package com.greenmile.challenger.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.MemberTest;

public interface OrganizerService {

	public ResponseEntity<Hackathon> createEvent(Hackathon event);
	public ResponseEntity<Page<MemberTest>> getListAllTeams(Pageable pageable);
	public ResponseEntity<List<MemberTest>> getListByName(String name);
	public ResponseEntity<List<MemberTest>> getListByRegistrationDate(Date date);
	public ResponseEntity<Boolean> endEvent();
	
}
