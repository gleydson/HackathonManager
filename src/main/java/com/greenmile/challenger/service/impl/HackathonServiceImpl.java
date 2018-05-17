package com.greenmile.challenger.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.MemberTest;
import com.greenmile.challenger.service.HackathonService;

@Service
public class HackathonServiceImpl implements HackathonService {

	@Override
	public ResponseEntity<Hackathon> createEvent(Hackathon event) {
		return null;
	}

	@Override
	public ResponseEntity<List<MemberTest>> getListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<MemberTest>> getListByRegistrationDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Boolean> endEvent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
