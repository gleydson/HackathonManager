package com.greenmile.challenger.test;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Member;
import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.repository.HackathonRepository;
import com.greenmile.challenger.repository.MemberRepository;
import com.greenmile.challenger.repository.TeamRepository;

import lombok.Data;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TeamTest {
	
	private String BASE_PATH = "http://localhost:8887";
	
	@Autowired
	private HackathonRepository hackathonRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	private String token;
	private RestTemplate restTemplate = new RestTemplate();
	
	@Before
	public void setUp() throws Exception {
		this.token = this.restTemplate.
				postForEntity(
						BASE_PATH + "/login",
						new Account("admin", "admin"),
						Account.class)
				.getHeaders()
				.values()
				.toArray()[6]
						.toString();
		this.token = this.token.substring(1, this.token.length() - 1);
	}
	
	@Test
	public void insertTeam() throws Exception {
		this.hackathonRepository.deleteAll();
		this.memberRepository.deleteAll();
		this.teamRepository.deleteAll();
		
		Hackathon hackathon = new Hackathon("hack01", "hack01", "ufc-quixada", new Date(), 2, 10, "OPEN", null, null);
		Member joao = new Member("joao", "joao@gmail.com", "88900720", "G", null);
		Member maria = new Member("maria", "maria@gmail.com", "88900720", "G", null);
		
		Member joaoRescue = this.memberRepository.save(joao);
		Member mariaRescue = this.memberRepository.save(maria);
		
		Hackathon hackathonRescue = this.hackathonRepository.save(hackathon);
		
		List<Member> members = new ArrayList<Member>();
		members.add(joaoRescue);
		members.add(mariaRescue);
		
		Team team01 = new Team("team01", "team01", "123", new Date(), hackathonRescue, members);
		
		RequestEntity<Team> requestOne = RequestEntity
			     .post(new URI(BASE_PATH + "/api/team"))
			     .header("Authorization", this.token)
			     .body(team01);
		Team teamRescue = this.restTemplate.exchange(requestOne, Team.class).getBody();
		
		assertEquals(teamRescue.getName(), team01.getName());
		assertEquals(teamRescue.getHackathon().getName(), hackathonRescue.getName());
		
	}
	
	private @Data class Account {
		private String username;
		private String password;
		Account(String username, String password) {
			this.username = username; 
			this.password = password;
		}
	}

}
