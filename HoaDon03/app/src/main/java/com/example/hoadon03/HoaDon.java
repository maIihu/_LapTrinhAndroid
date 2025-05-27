package com.example.hoadon03;

public class HoaDon {
    String id;
    String name;
    String date;
    double cost;
    int limitDate;

    public HoaDon() {}
    public HoaDon(String name, String date, double cost, int limitDate) {
        this.name = name;
        this.date = date;
        this.cost = cost;
        this.limitDate = limitDate;
    }

    public double tinhTien() {
        int A = 48;
        int chietKhau = 5;
        return cost * limitDate * (100 - chietKhau)/100;
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

    public int getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(int limitDate) {
        this.limitDate = limitDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
