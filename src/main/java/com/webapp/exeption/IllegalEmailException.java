package com.webapp.exeption;

public class IllegalEmailException extends Throwable{
    public IllegalEmailException(String message){
        super(message);
    }
}
