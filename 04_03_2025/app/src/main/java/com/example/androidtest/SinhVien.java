package com.example.androidtest;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String name;
    private int yearOfBirth;

    public String getName() {
        return name;
    }

    public int getDataOfBirth() {
        return yearOfBirth;
    }

    public SinhVien(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDataOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
