package com.bridgeit.ipl.service;

import java.util.List;

import com.bridgeit.ipl.model.DreamTeam;
import com.bridgeit.ipl.model.Team;

public interface TeamService {

	void addTeam(Team team);

	List<Team> displayAllTeam();

	String getTeamName(String name);

	Team getTeamById(long teamId);

	void addDreamTeam(DreamTeam dt);

	List<DreamTeam> getDreamTeamList();

	DreamTeam getDreamTeamDetail(int dreamteamId);

}
