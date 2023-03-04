package com.jb.player.clr;

import com.jb.player.beans.Player;
import com.jb.player.beans.Position;
import com.jb.player.beans.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Order(4)
public class PlayerControllerTest implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;


    private String url = "http://localhost:8080/api/admin";

    private String url2 = "http://localhost:8080/api/team";

    @Override
    public void run(String... args) throws Exception {

        System.out.println("######################### ADMIN CONTROLLER TEST ###################");

        Player p1 = Player.builder().
                firstName("Moshe").
                lastName("Herb").
                birthday(Date.valueOf(LocalDate.now().minusYears(30))).
                isForeign(false).
                position(Position.PG).
                weight(177).
                shirtNumber(2).
                build();

        Team t1 = Team.builder().name("London").establishedYear(1980).players(List.of(p1)).build();


        System.out.println("------------ADD TEAM---------------");
        ResponseEntity<String> res = restTemplate.postForEntity(url + "/teams", t1, String.class );
        System.out.println(res.getStatusCode());
//        System.out.println(res.getStatusCodeValue() == 204 ? "ok" : "oops");


        System.out.println("------------UPDATE TEAM---------------");
//        t1.setName("London update");
//        HttpEntity<Team> myTeam = new HttpEntity<>(t1);
//        ResponseEntity<String> res2 = restTemplate.exchange(url + "/teams/7", HttpMethod.PUT, myTeam, String.class);
//        System.out.println(res2.getStatusCodeValue() == 204 ? "ok" : "oops");

        Team res3 = restTemplate.getForObject(url+"/teams/7", Team.class);
        System.out.println("Before update: " +res3);

        res3.setName("London new");
        restTemplate.put(url + "/teams/7" , res3);

        System.out.print("After update: ");
        System.out.println(restTemplate.getForObject(url+"/teams/7", Team.class));


        System.out.println("--------------DELETE TEAM-------------");
        restTemplate.delete(url + "/teams/4");
        System.out.println("-------------GET ALL TEAMS--------------");
        Arrays.asList(restTemplate.getForObject(url+"/teams", Team[].class)).forEach(System.out::println);


        System.out.println("-------------GET ONE TEAM--------------");
        Team res2 = restTemplate.getForObject(url+"/teams/5", Team.class);
        System.out.println(res2);

        System.out.println("-------------GET YOUNGER TEAM -------------------");
        Team res4 = restTemplate.getForObject(url + "/teams/east-year/younger", Team.class);
        System.out.println(res4);


        System.out.println("-------------GET OLDER TEAM -------------------");
        Team res5 = restTemplate.getForObject(url + "/teams/east-year/older", Team.class);
        System.out.println(res5);

        System.out.println("-------------GET OLDER WITH CHAR START -------------------");
        Team[] res6 = restTemplate.getForObject(url + "/teams/by-char-start?chart=p", Team[].class);
        Arrays.asList(res6).forEach(System.out::println);


        System.out.println("-------------GET OLDER WITH CHAR END -------------------");
        Team[] res7 = restTemplate.getForObject(url + "/teams/by-char-end?chartEnd=a", Team[].class);
        Arrays.asList(res7).forEach(System.out::println);

        System.out.println("-------------GET TEAMS BY NAME UP -------------------");
        Team[] res8 = restTemplate.getForObject(url + "/teams/name-order-up", Team[].class);
        Arrays.asList(res8).forEach(System.out::println);

        System.out.println("-------------GET TEAMS BY NAME DOWN -------------------");
        Team[] res9 = restTemplate.getForObject(url + "/teams/name-order-down", Team[].class);
        Arrays.asList(res9).forEach(System.out::println);


        System.out.println("-------------GET TEAMS BY YEAR UP -------------------");
        Team[] res10 = restTemplate.getForObject(url + "/teams/east-year/order-up", Team[].class);
        Arrays.asList(res10).forEach(System.out::println);

        System.out.println("-------------GET TEAMS BY YEAR DOWN -------------------");
        Team[] res11 = restTemplate.getForObject(url + "/teams/east-year/order-down", Team[].class);
        Arrays.asList(res11).forEach(System.out::println);

        System.out.println("-------------GET TEAMS WITH TEXT -------------------");
        Team[] res12 = restTemplate.getForObject(url + "/teams/by-with-text?text=ar", Team[].class);
        Arrays.asList(res12).forEach(System.out::println);

        System.out.println("######################### TEAM CONTROLLER TEST ###################");

        Player p2 = Player.builder().
                firstName("Avi").
                lastName("Brush").
                birthday(Date.valueOf(LocalDate.now().minusYears(22))).
                isForeign(true).
                position(Position.SG).
                weight(100).
                shirtNumber(5).
                build();
        System.out.println("------------ADD PLAYER---------------");
        ResponseEntity<String> res13 = restTemplate.postForEntity(url2 + "/players", p2, String.class );
        System.out.println(res.getStatusCode());

        System.out.println("------------UPDATE PLAYER---------------");

        System.out.println("After update: " + p2);
        p2.setFirstName("Avi new");
        restTemplate.put(url2 + "/players/9" , p2);
        System.out.println("Before update: " + p2);


        System.out.println("-------------- GET ALL PLAYERS BY TEAM ID------------------");
        Player[] res15 = restTemplate.getForObject(url2 + "/players/5", Player[].class);
        Arrays.asList(res15).forEach(System.out::println);

        System.out.println("-------------- GET ONE PLAYERS BY TEAM ID------------------");
        Player res16 = restTemplate.getForObject(url2 + "/players/1/2", Player.class);
        System.out.println(res16);

        System.out.println("-------------- GET YOUNGER PLAYER------------------");
        Player res17 = restTemplate.getForObject(url2 + "/players/younger", Player.class);
        System.out.println(res17);



        System.out.println("-------------- GET OLDER PLAYER------------------");
        Player res18 = restTemplate.getForObject(url2 + "/players/older", Player.class);
        System.out.println(res18);


        System.out.println("-------------- AVG------------------");
        Double res19 = restTemplate.getForObject(url2 + "/players/num-shirt/avg", Double.class);
        System.out.println(res19);

        System.out.println("-------------- SUM------------------");
        Integer res20 = restTemplate.getForObject(url2 + "/players/num-shirt/sum", Integer.class);
        System.out.println(res20);




























    }
}
