package com.jb.player.service;

import com.jb.player.beans.Player;
import com.jb.player.exceptions.ChampionsCustomException;
import com.jb.player.exceptions.ErrMsg;
import com.jb.player.repos.PlayerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private PlayerRepos playerRepos;


    @Override
    public void addPlayer(Player player) {
        playerRepos.save(player);
    }

    @Override
    public void updatePlayer(int playerId, Player player) throws ChampionsCustomException {
        Player playerFromDb = playerRepos.findById(playerId).orElseThrow(()-> new ChampionsCustomException(ErrMsg.ID_NOT_FOUND));
        if(playerFromDb.getPosition()!= player.getPosition()){
            throw new ChampionsCustomException(ErrMsg.UPDATE_POSITION);
        }
        player.setId(playerId);
        playerRepos.saveAndFlush(player);

    }

    @Override
    public void deletePlayer(int playerId) throws ChampionsCustomException {
        Player playerFromDb = playerRepos.findById(playerId).orElseThrow(()-> new ChampionsCustomException(ErrMsg.ID_NOT_FOUND));
        if(playerFromDb.getBirthday().before(Date.valueOf(LocalDate.now().minusYears(35)))){
            throw new ChampionsCustomException(ErrMsg.DELETE_PLAYER);
        }
        playerRepos.deleteById(playerId);

    }

    @Override
    public List<Player> getAllPlayers(int teamId) {
        return playerRepos.getAllPlayersByTeamId(teamId);
    }

    @Override
    public Player getOnePlayer(int teamId, int playerId) throws ChampionsCustomException {
        if(!playerRepos.existsById(playerId)){
            throw new ChampionsCustomException(ErrMsg.ID_NOT_FOUND);
        }
        return playerRepos.getOnePlayersByTeamIdAndPlayerId(teamId, playerId);
    }

    @Override
    public Player getYoungerPlayer() {
        return playerRepos.youngerPLayer();
    }

    @Override
    public Player getOlderPlayer() {
        return playerRepos.olderPlayer();
    }

    @Override
    public double avg() {
        return playerRepos.avg();
    }

    @Override
    public int sum() {
        return playerRepos.sum();
    }
}



