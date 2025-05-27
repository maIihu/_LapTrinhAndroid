package com.example.khachsan;

import java.io.Serializable;
import java.util.Date;

public class HoaDon  {
    String id;
    String name;
    Date dateTime;
    double cost;
    int timeLimit;

    public HoaDon(String id, String name, Date dateTime, double cost, int timeLimit) {
        this.cost = cost;
        this.name = name;
        this.id = id;
        this.dateTime = dateTime;
        this.timeLimit = timeLimit;
    }

    public double tinhTien(){
        return cost * timeLimit * (100 - 5)/100;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}
