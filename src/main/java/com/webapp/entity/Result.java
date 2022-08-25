package com.webapp.entity;

import java.io.Serializable;

public class Result implements Serializable {
    private double result;
    private String nameOfTest;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getNameOfTest() {
        return nameOfTest;
    }

    public void setNameOfTest(String nameOfTest) {
        this.nameOfTest = nameOfTest;
    }
}
