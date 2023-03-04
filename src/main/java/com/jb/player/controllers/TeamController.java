package com.jb.player.controllers;

import com.jb.player.beans.Player;
import com.jb.player.exceptions.ChampionsCustomException;
import com.jb.player.service.TeamService;
import com.jb.player.service.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/team")
public class TeamController {
    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping("players")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlayer(@RequestBody Player player){
        teamService.addPlayer(player);
    }

    @PutMapping("players/{playerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePlayer(@PathVariable int playerId, @RequestBody Player player) throws ChampionsCustomException{
        teamService.updatePlayer(playerId, player);
    }

    @DeleteMapping("players/{playerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayer(@PathVariable int playerId) throws ChampionsCustomException{
        teamService.deletePlayer(playerId);
    }

    @GetMapping("players/{teamId}")
    public List<Player> getAllPlayers(@PathVariable int teamId){
        return teamService.getAllPlayers(teamId);
    }



    @GetMapping("players/{teamId}/{playerId}")
    public Player getOnePlayer(@PathVariable int teamId, @PathVariable int playerId) throws ChampionsCustomException{
        return teamService.getOnePlayer(teamId,playerId);
    }

    @GetMapping("players/younger")
    public Player getYoungerPlayer(){
        return teamService.getYoungerPlayer();
    }

    @GetMapping("players/older")
    public Player getOlderPlayer(){
        return teamService.getOlderPlayer();
    }

    @GetMapping("players/num-shirt/avg")
    public double avg(){
        return teamService.avg();
    }

    @GetMapping("players/num-shirt/sum")
    public int sum(){
        return teamService.sum();
    }
}
