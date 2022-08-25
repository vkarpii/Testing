package com.webapp.exeption.user;

public class IllegalPasswordException extends Throwable{
    public IllegalPasswordException(String message){
        super(message);
    }
}
