package com.webapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Test implements Serializable {

    private ArrayList<Question> questions;
    private int id;
    private int localeId;
    private String name;
    private int maxTime;
    private int maxAttemps;
    private int complexity;
    private Group group;
    private Subject subject;

    private int numberOfQueries;
    public Test(){
        questions = new ArrayList<>();
        group = new Group();
    }
    public Test(String name,int maxTime,int maxAttemps,int complexity){
        questions = new ArrayList<>();
        group = new Group();
        this.name = name;
        this.maxTime = maxTime;
        this.maxAttemps = maxAttemps;
        this.complexity = complexity;
        subject = new Subject();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getNumberOfQueries() {
        return numberOfQueries;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setNumberOfQueries(int numberOfQueries) {
        this.numberOfQueries = numberOfQueries;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", localeId=" + localeId +
                ", name='" + name + '\'' +
                ", maxTime=" + maxTime +
                ", maxAttemps=" + maxAttemps +
                ", complexity=" + complexity +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocaleId() {
        return localeId;
    }

    public void setLocaleId(int localeId) {
        this.localeId = localeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMaxAttemps() {
        return maxAttemps;
    }

    public void setMaxAttemps(int maxAttemps) {
        this.maxAttemps = maxAttemps;
    }

    public String getComplexity() {
        String res = "";
        switch (complexity){
            case 2: res = "Middle"; break;
            case 3: res = "Hard"; break;
            default: res = "Easy";
        }
        return res;
    }
    public int getComplexityBoolean() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public void setLocaleIdByString(String locale){
        if ("ua".equalsIgnoreCase(locale))
            localeId = 2;
        else
            localeId = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id && localeId == test.localeId && maxTime == test.maxTime && maxAttemps == test.maxAttemps && complexity == test.complexity && numberOfQueries == test.numberOfQueries && Objects.equals(questions, test.questions) && Objects.equals(name, test.name) && Objects.equals(group, test.group) && Objects.equals(subject, test.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions, id, localeId, name, maxTime, maxAttemps, complexity, group, subject, numberOfQueries);
    }
}
