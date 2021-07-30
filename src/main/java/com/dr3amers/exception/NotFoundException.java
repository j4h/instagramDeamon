package com.dr3amers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(int id) {
        super("ID: " +id + " not found.");
    }
    public NotFoundException(String msg) { super(msg); }

    public String toString(){
        return this.getMessage() +"\n" + getClass();
    }
}
