package com.webapp.entity;

public enum Role {
    STUDENT(1),ADMIN(2);

    private int id = 0;

    Role(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public static Role getRole(int id){
        return ADMIN.id == id ? ADMIN:STUDENT;
    }

}
