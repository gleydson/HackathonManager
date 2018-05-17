package com.greenmile.challenger.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.MemberTest;

public interface HackathonService {

	ResponseEntity<Hackathon> createEvent(Hackathon event);
	ResponseEntity<List<MemberTest>> getListByName(String name);
	ResponseEntity<List<MemberTest>> getListByRegistrationDate(Date date);
	ResponseEntity<Boolean> endEvent();

}
