package com.webapp.exeption.recaptcha;

public class IllegalReCaptchaException extends Throwable{
    public IllegalReCaptchaException(String message){
        super(message);
    }
}
