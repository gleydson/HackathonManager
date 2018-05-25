package com.greenmile.challenger.controller;

import static com.greenmile.challenger.util.ConstantsUtil.API_MEMBER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenmile.challenger.bean.Member;
import com.greenmile.challenger.service.MemberService;

@RestController
@RequestMapping(API_MEMBER)
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping
	public ResponseEntity<Member> create(@RequestBody Member member) {
		return new ResponseEntity<Member>(this.memberService.create(member), HttpStatus.OK);
	}
	
}
