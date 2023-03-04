package com.jb.player.advice;

import com.jb.player.exceptions.ChampionsCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ChampionControllerAdvice {
    @ExceptionHandler(value = {ChampionsCustomException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handle(Exception e){
        return new ErrorDetails("Player system error", e.getMessage());
    }
}
