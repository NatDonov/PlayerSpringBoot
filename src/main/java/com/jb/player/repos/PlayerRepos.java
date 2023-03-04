package com.jb.player.repos;

import com.jb.player.beans.Player;
import com.jb.player.beans.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepos extends JpaRepository<Player, Integer> {

    @Query(value = "select * from players inner join teams_players on players_id = id where team_id = ?", nativeQuery = true)
    List<Player> getAllPlayersByTeamId(int teamId);


    @Query(value = "select * from players inner join teams_players on players_id = id where team_id = ? and players_id = ?", nativeQuery = true)
    Player getOnePlayersByTeamIdAndPlayerId(int teamId, int playerId);


    @Query(value = "SELECT * FROM players WHERE `birthday` = ( SELECT MAX(`birthday`) FROM players) limit 1", nativeQuery = true)
    Player youngerPLayer();

    @Query(value = "SELECT * FROM players WHERE `birthday` = ( SELECT MIN(`birthday`) FROM players) limit 1", nativeQuery = true)
    Player olderPlayer();

    @Query(value = "select avg(shirt_number) from players", nativeQuery = true)
    double avg();

    @Query(value = "select sum(shirt_number) from players", nativeQuery = true)
    int sum();



}
