package com.webapp.exeption;

import com.webapp.exeption.user.IllegalPasswordException;

public class IllegalInputException extends Throwable{
    public IllegalInputException(String message){
        super(message);
    }
}
