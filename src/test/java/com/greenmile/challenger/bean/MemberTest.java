package com.greenmile.challenger.bean;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

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
		
		assertThat(member.getId(), is(ID));
		assertThat(member.getName(), is(NAME));
		assertThat(member.getEmail(), is(EMAIL));
		assertThat(member.getPhone(), is(PHONE));
		assertThat(member.getShirtSize(), is(SHIRT_SIZE));
		
	}

}
