package com.greenmile.challenger.service.impl;

import static com.greenmile.challenger.util.ConstantsUtil.EXCEPTION_A_USER_WITH_THIS_EMAIL_ALREADY_EXISTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenmile.challenger.bean.Member;
import com.greenmile.challenger.exception.BadRequestException;
import com.greenmile.challenger.repository.MemberRepository;
import com.greenmile.challenger.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Member create(Member member) {
		if (this.memberRepository.findByEmail(member.getEmail()) != null) {
			throw new BadRequestException(EXCEPTION_A_USER_WITH_THIS_EMAIL_ALREADY_EXISTS);
		}
		return this.memberRepository.save(member);
	}
	
}
