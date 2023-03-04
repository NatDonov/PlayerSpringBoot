package com.jb.player.clr;

import com.jb.player.beans.Player;
import com.jb.player.beans.Position;
import com.jb.player.beans.Team;
import com.jb.player.exceptions.ChampionsCustomException;
import com.jb.player.service.TeamService;
import com.jb.player.service.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(3)
public class TeamServiceTest implements CommandLineRunner {
    @Autowired
    private TeamServiceImpl teamService;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%    TEAM TEST  %%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("************* ADD PLAYER **************");

        System.out.println(">>>>>>successful add<<<<<<<<<");

        teamService.addPlayer(Player.builder()
                .firstName("Moti")
                .lastName("Benami")
                .weight(170)
                .shirtNumber(16)
                .birthday(Date.valueOf(LocalDate.now().minusYears(25)))
                .position(Position.C)
                .isForeign(true)
                .build());

        System.out.println("************* UPDATE PLAYER **************");
        Player playerFromDb = teamService.getOnePlayer(2, 3);
        System.out.println("Before update: " + playerFromDb);
        System.out.println(">>>>>>successful update<<<<<<<<<");
        playerFromDb.setWeight(170);
        teamService.updatePlayer(3, playerFromDb);
        System.out.println("After update: " + playerFromDb);
        try {
            System.out.println("--------- Set position ---------");
            Player playerFromDb1 = teamService.getOnePlayer(2, 3);
            playerFromDb1.setPosition(Position.SG);
            teamService.updatePlayer(3, playerFromDb1);
        } catch (ChampionsCustomException championsCustomException) {
            System.out.println(championsCustomException);
        }

        System.out.println("************* DELETE PLAYER **************");
        System.out.println(">>>>>>successful delete<<<<<<<<<");
        teamService.deletePlayer(5);
        try {
            System.out.println("--------- delete player over 35 ---------");
            teamService.deletePlayer(6);
        } catch (ChampionsCustomException championsCustomException) {
            System.out.println(championsCustomException);
        }


        System.out.println("************* GET ALL PLAYERS BY TEAM'S ID(1) **************");
        teamService.getAllPlayers(1).forEach(System.out::println);

        System.out.println("************* GET ONE PLAYERS BY TEAM'S ID(1) AND PLAYER ID(1) **************");
        System.out.println(teamService.getOnePlayer(1, 1));


        System.out.println("************* GET YOUNGER TEAM **************");
        System.out.println(teamService.getYoungerPlayer());

        System.out.println("************* GET OLDER TEAM **************");
        System.out.println(teamService.getOlderPlayer());


        System.out.println("************* AVG T-SHIRT **************");
        System.out.println(teamService.avg());

        System.out.println("************* SUM T-SHIRT **************");
        System.out.println(teamService.sum());
    }
}
