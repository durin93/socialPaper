package com.duru.socialpaper.exception;

import com.duru.socialpaper.domain.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityRestControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(SecurityRestControllerAdvice.class);

    @ExceptionHandler(NoAuthentication.class)
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    public Errors noAuthentication(){
        log.debug("no Authentication is happened!");
        return Errors.of("no Authentication");
    }
    @ExceptionHandler(UnAuthorization.class)
    @ResponseStatus(value=HttpStatus.FORBIDDEN)
    public Errors UnAuthorization(Exception e){
        log.debug("UnAuthorization is happened!");
        return Errors.of(e.getMessage());
    }


    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
    public Errors invalidException(Exception e){
        log.debug("invalidException is happened!");
        return Errors.of(e.getMessage());
    }






}
