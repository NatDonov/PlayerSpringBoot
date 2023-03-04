package com.jb.player.clr;


import com.jb.player.beans.Player;
import com.jb.player.beans.Position;
import com.jb.player.beans.Team;
import com.jb.player.repos.PlayerRepos;
import com.jb.player.repos.TeamRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
public class Init implements CommandLineRunner {

    @Autowired
    private PlayerRepos playerRepos;

    @Autowired
    private TeamRepos teamRepos;


    @Override
    public void run(String... args) throws Exception {



        Player p1 = Player.builder().
                firstName("Jerar").
                lastName("Pikke").
                birthday(Date.valueOf(LocalDate.now().minusYears(35))).
                isForeign(true).
                position(Position.PF).
                weight(180).
                shirtNumber(20).
                build();

        Player p2 = Player.builder().
                firstName("Chabi").
                lastName("Alonson").
                birthday(Date.valueOf(LocalDate.now().minusYears(30))).
                isForeign(false).
                position(Position.SF).
                weight(178).
                shirtNumber(5).
                build();

        Player p3 = Player.builder().
                firstName("Robert").
                lastName("Elvadovski").
                birthday(Date.valueOf(LocalDate.now().minusYears(34))).
                isForeign(true).
                position(Position.C).
                weight(160).
                shirtNumber(7).
                build();

        Player p4 = Player.builder().
                firstName("Serginio").
                lastName("Deset").
                birthday(Date.valueOf(LocalDate.now().minusYears(28))).
                isForeign(false).
                position(Position.SF).
                weight(181).
                shirtNumber(10).
                build();

        Player p5 = Player.builder().
                firstName("Leo").
                lastName("Messi").
                birthday(Date.valueOf(LocalDate.now().minusYears(36))).
                isForeign(true).
                position(Position.C).
                weight(167).
                shirtNumber(15).
                build();

        Player p6 = Player.builder().
                firstName("Christian").
                lastName("Ronaldo").
                birthday(Date.valueOf(LocalDate.now().minusYears(37))).
                isForeign(false).
                position(Position.SG).
                weight(182).
                shirtNumber(1).
                build();





        Team t1 = Team.builder().name("Barcelona").establishedYear(1970).players(List.of(p1, p2)).build();
        Team t2 = Team.builder().name("Paris").establishedYear(2000).players(List.of(p3)).build();
        Team t3 = Team.builder().name("Manchester United").establishedYear(1980).players(List.of(p5)).build();
        Team t4 = Team.builder().name("Chelsi").establishedYear(2010).players(List.of(p4)).build();
        Team t5 = Team.builder().name("MacabiNetanya").establishedYear(2008).players(List.of(p6)).build();


        teamRepos.saveAll(List.of(t1,t2,t3,t4,t5));
        teamRepos.findAll().forEach(System.out::println);

    }
}
