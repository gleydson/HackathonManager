package com.greenmile.challenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmile.challenger.bean.MemberTest;
import com.greenmile.challenger.service.OrganizerService;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {

	@Autowired
	private OrganizerService service;
	
	@GetMapping("/getListAllTeams")
	public ResponseEntity<Page<MemberTest>> getListAllTeams(Pageable pageable) {
		return this.service.getListAllTeams(pageable);
	}
}
