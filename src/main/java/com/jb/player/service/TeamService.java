package com.jb.player.service;

import com.jb.player.beans.Player;
import com.jb.player.exceptions.ChampionsCustomException;

import java.util.List;

public interface TeamService {
    void addPlayer(Player player);

    void updatePlayer(int playerId, Player player) throws ChampionsCustomException;

    void deletePlayer(int playerId) throws ChampionsCustomException;

    List<Player> getAllPlayers(int teamId);

    Player getOnePlayer(int teamId, int playerId) throws ChampionsCustomException;

    Player getYoungerPlayer();

    Player getOlderPlayer();

    double avg();

    int sum();

}
