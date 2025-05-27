package com.example.thuchi3;

public class ThuChi {
    String id;
    int name;
    String date;
    double cost;

    public ThuChi(String id, int name, String date, double cost) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.cost = cost;
    }

    public ThuChi() {

    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
