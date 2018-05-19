package com.greenmile.challenger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.greenmile.challenger.config.jwt.AuthenticationFilterConfigJwt;
import com.greenmile.challenger.config.jwt.LoginFilterConfigJwt;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String login = "/login";
		String index = "/";
		
		http.cors().and().csrf().disable();
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, login).permitAll()
			.antMatchers(HttpMethod.GET, index).permitAll()
			.anyRequest().authenticated();
			
		http.addFilterBefore(new LoginFilterConfigJwt(login,
			authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		
		http.addFilterBefore(new AuthenticationFilterConfigJwt(),
			UsernamePasswordAuthenticationFilter.class);
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.passwordEncoder(NoOpPasswordEncoder.getInstance())
					.withUser("admin")
					.password("123")
					.authorities("ROLE_ADMIN");
		auth
			.inMemoryAuthentication()
				.passwordEncoder(NoOpPasswordEncoder.getInstance())
					.withUser("user")
					.password("123")
					.authorities("ROLE_USER");
		auth.userDetailsService(userDetailsService);		
	}
	
}