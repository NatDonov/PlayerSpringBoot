package com.jb.player.beans;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter

public enum Position {

    PG("point guard"),

    SG("shooting guard"),

    SF("small forward"),

    PF("power forward"),

    C("center");

    private String mess;

    Position( String mess){
        this.mess = mess;
    }


}
