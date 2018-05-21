package com.greenmile.challenger.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.greenmile.challenger.bean.Member;

public class MemberTest {
	
	private final Long ID = 1L;
	private final String NAME = "João Carlos da Silva";
	private final String EMAIL = "joao@gmail.com";
	private final String PHONE = "85988776655";
	private final String SHIRT_SIZE = "G";

	@Test
	public void initiatingNullObject() {
		Member member = new Member();
		assertNull(member.getId());
		assertNull(member.getName());
		assertNull(member.getEmail());
		assertNull(member.getPhone());
		assertNull(member.getShirtSize());
	}
	
	@Test
	public void settingObjectData() {
		Member member = new Member();
		
		member.setId(ID);
		member.setName(NAME);
		member.setEmail(EMAIL);
		member.setPhone(PHONE);
		member.setShirtSize(SHIRT_SIZE);
		
		assertEquals(member.getId(), ID);
		assertEquals(member.getName(), NAME);
		assertEquals(member.getEmail(), EMAIL);
		assertEquals(member.getPhone(), PHONE);
		assertEquals(member.getShirtSize(), SHIRT_SIZE);
	}

}
