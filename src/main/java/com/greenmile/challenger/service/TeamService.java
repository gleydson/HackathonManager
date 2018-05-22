package com.greenmile.challenger.service;

import com.greenmile.challenger.bean.Team;

public interface TeamService {
	public Team subscribe(Long idHackathon, Team team);
	public Team getTeam(Long idTeam);
	public Boolean unsubscribe(Long idTeam);
}
