package com.greenmile.challenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmile.challenger.bean.Member;
import com.greenmile.challenger.repository.MemberRepository;

@RestController
@RequestMapping("api/member")
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;
	
	@PostMapping
	public ResponseEntity<Member> create(@RequestBody Member member) {
		return new ResponseEntity<Member>(this.memberRepository.save(member), HttpStatus.OK);
	}
	
}
