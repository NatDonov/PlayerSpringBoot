package com.jb.player.service;

import com.jb.player.beans.Team;
import com.jb.player.exceptions.ChampionsCustomException;

import java.util.List;

public interface AdminService {
    void addTeam(Team team) throws ChampionsCustomException;

    void updateTeam(int teamId, Team team) throws ChampionsCustomException;

    void deleteTeam(int teamId) throws ChampionsCustomException;

    List<Team> getAllTeams();

    Team getOneTeam(int teamId) throws ChampionsCustomException;

    Team getYoungerTeam();

    Team getOlderTeam();

    List<Team> getAllTeamsByCharacterStart(String chart);

    List<Team> getAllTeamsByCharacterEnd(String chartEnd);

    List<Team> getAllTeamsByNameUp();

    List<Team> getAllTeamsByNameDown();

    List<Team> getAllTeamByYearUp();

    List<Team> getAllTeamByYearDown();

    List<Team> getAllTeamWithText(String text);


}
