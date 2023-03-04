package com.jb.player.repos;

import com.jb.player.beans.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepos extends JpaRepository<Team, Integer> {


    boolean existsByName(String name);

    List<Team> findByEstablishedYearLessThan(int num);


    @Query(value = "SELECT * FROM teams WHERE `established_year` = ( SELECT MAX(`established_year`) FROM teams) limit 1", nativeQuery = true)
    Team youngerTeam();

    @Query(value = "SELECT * FROM teams WHERE `established_year` = ( SELECT MIN(`established_year`) FROM teams) limit 1", nativeQuery = true)
    Team olderTeam();


    List<Team> findByNameStartingWith(String chart);

    List<Team> findByNameEndingWith(String chart);

    List<Team> findAllByOrderByNameAsc();

    List<Team> findAllByOrderByNameDesc();

    List<Team> findAllByOrderByEstablishedYearAsc();

    List<Team> findAllByOrderByEstablishedYearDesc();

    @Query(value = "select * from teams where name like %?%", nativeQuery = true)
    List<Team> getAllTeamWithText(String text);









}
