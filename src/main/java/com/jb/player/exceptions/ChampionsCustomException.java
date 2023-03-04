package com.jb.player.exceptions;

public class ChampionsCustomException extends Exception{

    public ChampionsCustomException(ErrMsg errMsg){

        super(errMsg.getMessage());
    }

}
