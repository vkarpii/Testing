package com.webapp;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

@Slf4j
public class NumberFormattingTag extends SimpleTagSupport {
    private double number;

    public void setNumber(double number) {
        this.number = number;
    }

    @Override
    public void doTag(){
        try {
            getJspContext().getOut().write(String.format("%.2f",number));
        } catch (Exception e){
            log.error("NumberFormatTag Error : {}",e.getMessage());
        }
    }
}
