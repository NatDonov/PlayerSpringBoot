package com.jb.player.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {


    ID_NOT_FOUND("id not found"),

    ID_ALREADY_EXIST("id already exist"),
    NAME_ALREADY_EXIST("sorry, name already exist"),
    UPDATE_ID("sorry, can't update team's id"),

    DELETE_TEAM("sorry, can't delete team because team's established year before 1975"),

    UPDATE_POSITION("sorry, can't update position"),

    DELETE_PLAYER(" sorry you can't delete player over 35 years");

    private String message;

    ErrMsg(String message) {
        this.message = message;
    }
}
