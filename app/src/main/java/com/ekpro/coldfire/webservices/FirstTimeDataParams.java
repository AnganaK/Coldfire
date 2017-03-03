package com.ekpro.coldfire.webservices;

import java.util.ArrayList;

/**
 * Created by xps-windows on 3/3/2017.
 */

public class FirstTimeDataParams {
    private int username;
    private String department;
    private ArrayList interest;
    private String tags;
    private int years;


    public ArrayList getInterest() {
        return interest;
    }

    public void setInterest(ArrayList interest) {
        this.interest = interest;
    }

    public int getUname() {
        return username;
    }

    public void setUname(int uname) {
        this.username = uname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}
