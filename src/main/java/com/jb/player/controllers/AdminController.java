package com.jb.player.controllers;

import com.jb.player.beans.Team;
import com.jb.player.exceptions.ChampionsCustomException;
import com.jb.player.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("teams")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeam(@RequestBody Team team) throws ChampionsCustomException {
        adminService.addTeam(team);
    }



    @PutMapping("teams/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTeam(@PathVariable int teamId, @RequestBody Team team) throws ChampionsCustomException {
        adminService.updateTeam(teamId, team);
    }




    @DeleteMapping("teams/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable int teamId) throws ChampionsCustomException {
        adminService.deleteTeam(teamId);
    }



    @GetMapping("teams")
    public List<Team> getAllTeams() {
        return adminService.getAllTeams();
    }


    @GetMapping("teams/{teamId}")
    public Team getOneTeam(@PathVariable int teamId) throws ChampionsCustomException {
        return adminService.getOneTeam(teamId);
    }


    @GetMapping("teams/east-year/younger")
    public Team getYoungerTeam() {
        return adminService.getYoungerTeam();
    }



    @GetMapping("teams/east-year/older")
    public Team getOlderTeam() {
        return adminService.getOlderTeam();
    }


    @GetMapping("teams/by-char-start")
    public List<Team> getAllTeamsByCharacterStart(@RequestParam String chart) {
        return adminService.getAllTeamsByCharacterStart(chart);
    }



    @GetMapping("teams/by-char-end")
    public List<Team> getAllTeamsByCharacterEnd(@RequestParam String chartEnd) {
        return adminService.getAllTeamsByCharacterEnd(chartEnd);
    }

    @GetMapping("teams/name-order-up")
    public List<Team> getAllTeamsByNameUp() {
        return adminService.getAllTeamsByNameUp();
    }



    @GetMapping("teams/name-order-down")
    public List<Team> getAllTeamsByNameDown() {
        return adminService.getAllTeamsByNameDown();
    }


    @GetMapping("teams/east-year/order-up")
    public List<Team> getAllTeamByYearUp() {
        return adminService.getAllTeamByYearUp();
    }



    @GetMapping("teams/east-year/order-down")
    public List<Team> getAllTeamByYearDown() {
        return adminService.getAllTeamByYearDown();
    }



    @GetMapping("teams/by-with-text")
    public List<Team> getAllTeamWithText(@RequestParam String text) {
        return adminService.getAllTeamWithText(text);
    }


}
