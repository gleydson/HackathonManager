package com.greenmile.challenger.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.greenmile.challenger.bean.Hackathon;
import com.greenmile.challenger.bean.Member;
import com.greenmile.challenger.bean.Team;
import com.greenmile.challenger.repository.HackathonRepository;
import com.greenmile.challenger.service.HackathonService;

import lombok.Data;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HackathonTest {
	
	final String BASE_PATH = "http://localhost:8887";

	private RestTemplate restTemplate = new RestTemplate();
	
	Gson gson = new Gson();
	
	@Autowired
	private HackathonRepository hackathonRepository;
	
	@Autowired
	private HackathonService hackathonService;
	
	private String token;

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
	public void initiatingNullObject() {
		Hackathon hackathon = new Hackathon();
		
		assertNull(hackathon.getId());
		assertNull(hackathon.getName());
		assertNull(hackathon.getDescription());
		assertNull(hackathon.getLocal());
		assertNull(hackathon.getDate());
		assertNull(hackathon.getStatus());
		assertNull(hackathon.getNumberOfMembersPerTeam());
		assertNull(hackathon.getNumberOfTeam());
		assertNull(hackathon.getNumberOfTeam());
		assertNull(hackathon.getMembers());
		assertNull(hackathon.getTeams());
	}
	
	@Test
	public void settingObjectData() {
		Hackathon hackathon = new Hackathon();
		
		Long ID = 1L;
		String NAME = "Hack01";
		String DESCRIPTION = "Hack01-DESCRIPTION";
		String LOCAL = "UFC QUIXADA";
		Date DATE = new Date();
		String STATUS = "OPEN";
		Integer NUMBEROFMEMBERSPERTEAM = 5;
		Integer NUMBEROFTEAMS = 5;
		List<Member> MEMBERS = null;
		List<Team> TEAMS = null;
		
		hackathon.setId(ID);
		hackathon.setName(NAME);
		hackathon.setDescription(DESCRIPTION);
		hackathon.setLocal(LOCAL);
		hackathon.setDate(DATE);
		hackathon.setStatus(STATUS);
		hackathon.setNumberOfMembersPerTeam(NUMBEROFMEMBERSPERTEAM);
		hackathon.setNumberOfTeam(NUMBEROFTEAMS);
		hackathon.setMembers(MEMBERS);
		hackathon.setTeams(TEAMS);
		
		assertEquals(hackathon.getId(), ID);
		assertEquals(hackathon.getName(), NAME);
		assertEquals(hackathon.getDescription(), DESCRIPTION);
		assertEquals(hackathon.getLocal(), LOCAL);
		assertEquals(hackathon.getDate(), DATE);
		assertEquals(hackathon.getStatus(), STATUS);
		assertEquals(hackathon.getNumberOfMembersPerTeam(), NUMBEROFMEMBERSPERTEAM);
		assertEquals(hackathon.getNumberOfTeam(), NUMBEROFTEAMS);
		assertEquals(hackathon.getMembers(), MEMBERS);
		assertEquals(hackathon.getTeams(), TEAMS);
		
	}
	
	@Test
	public void createHackathonWhitApiRest() throws Exception {
		this.hackathonRepository.deleteAll();
		Hackathon hackathon = new Hackathon();
		
		hackathon.setName("Hack 01");
		hackathon.setDescription("Primeiro Hackathon");
		hackathon.setLocal("UFC Quixadá");
		hackathon.setStatus("OPEN");
		hackathon.setNumberOfMembersPerTeam(3);
		hackathon.setNumberOfTeam(2);
		hackathon.setMembers(null);
		hackathon.setTeams(null);
		 
		RequestEntity<Hackathon> request = RequestEntity
			     .post(new URI(BASE_PATH + "/api/hackathon"))
			     .header("Authorization", this.token)
			     .body(hackathon);

		ResponseEntity<Hackathon> response = this.restTemplate.exchange(request, Hackathon.class);
		Long id = response.getBody().getId();
		Hackathon hackathonRescue = this.hackathonRepository.findById(id).get();
		
		assertEquals(response.getStatusCodeValue(), 200);
		assertEquals(hackathon.getName(), hackathonRescue.getName());
		assertEquals(hackathon.getDescription(), hackathonRescue.getDescription());
		assertEquals(hackathon.getLocal(), hackathonRescue.getLocal());
		assertEquals(hackathon.getNumberOfMembersPerTeam(), hackathonRescue.getNumberOfMembersPerTeam());
		assertEquals(hackathon.getNumberOfTeam(), hackathonRescue.getNumberOfTeam());
	}
	
	@Test
	public void updateHackathonWhitApiRest() throws Exception {
		this.hackathonRepository.deleteAll();
		Hackathon hackathon = new Hackathon("Hack 01", "Primeiro Hackathon", "UFC Quixadá", new Date(), 3, 2, "OPEN", null, null);
		 
		RequestEntity<Hackathon> requestOne = RequestEntity
			     .post(new URI(BASE_PATH + "/api/hackathon"))
			     .header("Authorization", this.token)
			     .body(hackathon);
		Hackathon hackathonRescue = this.restTemplate.exchange(requestOne, Hackathon.class).getBody();
		
		hackathonRescue.setName("Hack 01 - Modificado");
		hackathonRescue.setDescription("Esta descrição foi alterada");
		hackathonRescue.setNumberOfMembersPerTeam(5);
		hackathonRescue.setNumberOfTeam(5);
		
		RequestEntity<Hackathon> requestTwo = RequestEntity
			     .put(new URI(BASE_PATH + "/api/hackathon"))
			     .header("Authorization", this.token)
			     .body(hackathonRescue);
		ResponseEntity<Hackathon> response = this.restTemplate.exchange(requestTwo, Hackathon.class);
		Long id = response.getBody().getId();
		Hackathon hackathonRescueAgain = this.hackathonRepository.findById(id).get();
		
		assertEquals(response.getStatusCodeValue(), 200);
		assertEquals(hackathonRescue.getName(), hackathonRescueAgain.getName());
		assertEquals(hackathonRescue.getDescription(), hackathonRescueAgain.getDescription());
		assertEquals(hackathonRescue.getNumberOfMembersPerTeam(), hackathonRescueAgain.getNumberOfMembersPerTeam());
		assertEquals(hackathonRescue.getNumberOfTeam(), hackathonRescueAgain.getNumberOfTeam());
		
	}
	
	@Test
	public void getHackathonByIdWhitApiRest() throws Exception {
		this.hackathonRepository.deleteAll();
		Hackathon hackathon = new Hackathon("Hack 01", "Primeiro Hackathon", "UFC Quixadá", new Date(), 3, 2, "OPEN", null, null);
		 
		RequestEntity<Hackathon> requestOne = RequestEntity
			     .post(new URI(BASE_PATH + "/api/hackathon"))
			     .header("Authorization", this.token)
			     .body(hackathon);
		Long id = this.restTemplate.exchange(requestOne, Hackathon.class).getBody().getId();
		
		RequestEntity<Void> requestTwo = RequestEntity
				.get(new URI(BASE_PATH + "/api/hackathon/" + id))
				.header("Authorization", this.token)
				.build();

		ResponseEntity<Hackathon> response = this.restTemplate.exchange(requestTwo, Hackathon.class);
		Hackathon hackathonRescue = response.getBody();
		
		assertEquals(response.getStatusCodeValue(), 200);
		assertEquals(hackathon.getName(), hackathonRescue.getName());
		assertEquals(hackathon.getDescription(), hackathonRescue.getDescription());
	}
	
	@Test
	public void createHackathonWhitService() {
		Hackathon hackathon = new Hackathon();
		
		hackathon.setName("Hack 01");
		hackathon.setDescription("Primeiro Hackathon");
		hackathon.setLocal("UFC Quixadá");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = sdf.parse("27/07/2006");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hackathon.setDate(date);
		hackathon.setStatus("OPEN");
		hackathon.setNumberOfMembersPerTeam(3);
		hackathon.setNumberOfTeam(2);
		hackathon.setMembers(null);
		hackathon.setTeams(null);
		
		Long id = this.hackathonService.createHackathon(hackathon).getBody().getId();
		Hackathon hackathonRescue = this.hackathonRepository.findById(id).get();
		
		assertEquals(hackathon.getName(), hackathonRescue.getName());
		assertEquals(hackathon.getDescription(), hackathonRescue.getDescription());
		assertEquals(hackathon.getLocal(), hackathonRescue.getLocal());
		assertEquals(hackathon.getDate(), hackathonRescue.getDate());
		assertEquals(hackathon.getNumberOfMembersPerTeam(), hackathonRescue.getNumberOfMembersPerTeam());
		assertEquals(hackathon.getNumberOfTeam(), hackathonRescue.getNumberOfTeam());
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
