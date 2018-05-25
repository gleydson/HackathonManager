package com.greenmile.challenger.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.greenmile.challenger.bean.Hackathon;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HackathonTest {

	@LocalServerPort
    private int port;

    private URL base;
    
    private String token;

    @Autowired
    private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + this.port + "/api");
		ResponseEntity<String> response = this.template.getForEntity(this.base.toString() + "/login", String.class);
		this.token = response.getHeaders().getFirst("Authorization");
	}

	@Test
	public void createHackathon() throws Exception {
		Hackathon hackathon = new Hackathon();
		RequestEntity<Hackathon> request = this.requestPost(this.base + "/hackathon", token, hackathon);
		ResponseEntity<Hackathon> response = this.template.exchange(request, Hackathon.class);
		assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
	}
	
	private RequestEntity<Hackathon> requestPost(String url, String token, Hackathon hackathon) throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
		header.add("Authorization", token);
		return new RequestEntity<Hackathon>(
				hackathon,
				header,
				HttpMethod.POST,
				new URI(url),
				Hackathon.class);
	}
	
}