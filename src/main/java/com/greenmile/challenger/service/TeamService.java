package com.greenmile.challenger.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.greenmile.challenger.bean.Team;

public interface TeamService {

	ResponseEntity<Page<Team>> getListAllTeams(Pageable pageable);

}
