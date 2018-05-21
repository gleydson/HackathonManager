package com.greenmile.challenger.service;

import org.springframework.http.ResponseEntity;

import com.greenmile.challenger.bean.Member;

public interface MemberService {
	public ResponseEntity<Member> create(Member member);
}
