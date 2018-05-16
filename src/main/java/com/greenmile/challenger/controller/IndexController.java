package com.greenmile.challenger.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class IndexController {
	
	@GetMapping
	public String toIndex() {
		return "index";
	}
}