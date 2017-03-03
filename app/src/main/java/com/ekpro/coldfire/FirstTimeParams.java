package com.ekpro.coldfire;

import java.util.ArrayList;

/**
 * Created by xps-windows on 3/3/2017.
 */

public class FirstTimeParams {

    private ArrayList<String> department;
    private ArrayList<String> interest;
    private String tags;
    private int years;

    public ArrayList<String> getDepartment() {
        return department;
    }

    public void setDepartment(ArrayList<String> department) {
        this.department = department;
    }

    public ArrayList<String> getInterest() {
        return interest;
    }

    public void setInterest(ArrayList<String> interest) {
        this.interest = interest;
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
