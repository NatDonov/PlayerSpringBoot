package com.jb.player.clr;

import com.jb.player.beans.Team;
import com.jb.player.exceptions.ChampionsCustomException;
import com.jb.player.repos.TeamRepos;
import com.jb.player.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AdminServiceTest implements CommandLineRunner {
    @Autowired
    private AdminServiceImpl adminServer;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("************* ADD TEAM **************");

        System.out.println(">>>>>>successful add<<<<<<<<<");
        adminServer.addTeam(Team.builder().name("Luki").establishedYear(2001).build());
        try {
            System.out.println("--------- add team with some name --------");
            adminServer.addTeam(Team.builder().name("Paris").establishedYear(1994).build());
        } catch (ChampionsCustomException championsCustomException) {
            System.out.println(championsCustomException);
        }


        System.out.println("************* UPDATE TEAM **************");
        Team teamFromDb = adminServer.getOneTeam(4);
        System.out.println("******* Before update: *********");
        System.out.println(teamFromDb);
        teamFromDb.setName("Chelsi update");
        adminServer.updateTeam(4, teamFromDb);
        System.out.println("******* After update: *********");
        System.out.println(teamFromDb);

        try {
            System.out.println("------ Set id -------");
            Team teamFromDb1 = adminServer.getOneTeam(1);
            adminServer.updateTeam(2, teamFromDb1);
        } catch (ChampionsCustomException championsCustomException) {
            System.out.println(championsCustomException);
        }

        try {
            System.out.println("------- Not found -------");
            Team teamFromDb1 = adminServer.getOneTeam(10);
            teamFromDb1.setName("New");
            adminServer.updateTeam(10, teamFromDb1);
        } catch (ChampionsCustomException championsCustomException) {
            System.out.println(championsCustomException);
        }

        System.out.println("************* DELETE TEAM **************");

        System.out.println(">>>>>>successful delete<<<<<<<<<");
        adminServer.deleteTeam(3);
        try {
            System.out.println("------- Not found -------");
            adminServer.deleteTeam(10);
        } catch (ChampionsCustomException championsCustomException) {
            System.out.println(championsCustomException);
        }
        try {
            System.out.println("------- Established Year before 1975 --------");
            adminServer.deleteTeam(1);
        } catch (ChampionsCustomException championsCustomException) {
            System.out.println(championsCustomException);
        }


        System.out.println("************* GET ALL TEAMS **************");
        adminServer.getAllTeams().forEach(System.out::println);

        System.out.println("************* GET YOUNGER TEAM **************");
        System.out.println(adminServer.getYoungerTeam());

        System.out.println("************* GET OLDER TEAM **************");
        System.out.println(adminServer.getOlderTeam());

        System.out.println("************* GET TEAM WITH STARTING C  **************");
        adminServer.getAllTeamsByCharacterStart("c").forEach(System.out::println);


        System.out.println("************* GET TEAM WITH ENDING A  **************");
        adminServer.getAllTeamsByCharacterEnd("a").forEach(System.out::println);

        System.out.println("************* GET ALL TEAMS NAME UP **************");
        adminServer.getAllTeamsByNameUp().forEach(System.out::println);

        System.out.println("************* GET ALL TEAMS NAME DOWN **************");
        adminServer.getAllTeamsByNameDown().forEach(System.out::println);

        System.out.println("************* GET ALL TEAMS YEAR UP **************");
        adminServer.getAllTeamByYearUp().forEach(System.out::println);

        System.out.println("************* GET ALL TEAMS YEAR DOWN **************");
        adminServer.getAllTeamByYearDown().forEach(System.out::println);


        System.out.println("*********** get All Team With Text AR ************");
        adminServer.getAllTeamWithText("ar").forEach(System.out::println);






    }
}
