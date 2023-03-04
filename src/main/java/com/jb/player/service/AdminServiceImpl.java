package com.jb.player.service;

import com.jb.player.beans.Team;
import com.jb.player.exceptions.ChampionsCustomException;
import com.jb.player.exceptions.ErrMsg;
import com.jb.player.repos.TeamRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private TeamRepos teamRepos;

    @Override
    public void addTeam(Team team) throws ChampionsCustomException {
        if(teamRepos.existsByName(team.getName())){
           throw new ChampionsCustomException(ErrMsg.NAME_ALREADY_EXIST);
        }
        teamRepos.save(team);
    }

    @Override
    public void updateTeam(int teamId, Team team) throws ChampionsCustomException {
        if(!teamRepos.existsById(teamId)){
            throw new ChampionsCustomException(ErrMsg.ID_NOT_FOUND);
        }
        if(teamId != team.getId()){
            throw new ChampionsCustomException(ErrMsg.UPDATE_ID);
        }
        team.setId(teamId);
        teamRepos.saveAndFlush(team);
    }

    @Override
    public void deleteTeam(int teamId) throws ChampionsCustomException {

        Team teamFromDb = teamRepos.findById(teamId).orElseThrow(()-> new ChampionsCustomException(ErrMsg.ID_NOT_FOUND));
        if( teamFromDb.getEstablishedYear() < 1975){
            throw new ChampionsCustomException(ErrMsg.DELETE_TEAM);
        }

        teamRepos.deleteById(teamId);

    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepos.findAll();
    }

    @Override
    public Team getOneTeam(int teamId) throws ChampionsCustomException {
        return teamRepos.findById(teamId).orElseThrow(()-> new ChampionsCustomException(ErrMsg.ID_NOT_FOUND));
    }

    @Override
    public Team getYoungerTeam() {
        return teamRepos.youngerTeam();
    }

    @Override
    public Team getOlderTeam() {
        return teamRepos.olderTeam();
    }

    @Override
    public List<Team> getAllTeamsByCharacterStart(String chart) {
        return teamRepos.findByNameStartingWith(chart);
    }

    @Override
    public List<Team> getAllTeamsByCharacterEnd(String chartEnd) {
        return teamRepos.findByNameEndingWith(chartEnd);
    }

    @Override
    public List<Team> getAllTeamsByNameUp() {
        return teamRepos.findAllByOrderByNameAsc();
    }

    @Override
    public List<Team> getAllTeamsByNameDown() {
        return teamRepos.findAllByOrderByNameDesc();
    }

    @Override
    public List<Team> getAllTeamByYearUp() {
        return teamRepos.findAllByOrderByEstablishedYearAsc();
    }

    @Override
    public List<Team> getAllTeamByYearDown() {
        return teamRepos.findAllByOrderByEstablishedYearDesc();
    }

    @Override
    public List<Team> getAllTeamWithText(String text) {
        return teamRepos.getAllTeamWithText(text);
    }
}




