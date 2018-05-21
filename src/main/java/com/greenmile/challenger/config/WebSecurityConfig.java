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
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		final String[] PUBLIC_MATCHERS = {
				"/h2-console/**"
		};
		
		final String[] PUBLIC_MATCHERS_GET = {
				"/api/hackathon"
		};
		
		final String[] PUBLIC_MATCHERS_POST = {
				"/login",
				"/api/team"
		};
		
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
			
		http.addFilterBefore(new LoginFilterConfigJwt(PUBLIC_MATCHERS_POST[0], authenticationManager()), 
				UsernamePasswordAuthenticationFilter.class);
		
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
					.password("admin")
					.roles("ADMIN");
		auth.userDetailsService(userDetailsService);//.passwordEncoder(bCryptPasswordEncoder());
	}
	
	/*@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
}