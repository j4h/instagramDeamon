package com.dr3amers.exception;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String email) {
        super("Username with email: "+email+" has already exists");
    }

    public String toString(){
        return this.getMessage() +"\n" + getClass();
    }
}
