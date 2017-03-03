package com.ekpro.coldfire.models;

import java.util.ArrayList;

/**
 * Created by incredible on 7/7/16.
 */
public class StudentDetails {

    int id;
    String name;
    String username;
    String password;
    boolean returning;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passw) {
        this.password = passw;
    }

    public boolean getReturning() {
        return returning;
    }

    public void setReturning(boolean ret) {
        this.returning = ret;
    }


}
