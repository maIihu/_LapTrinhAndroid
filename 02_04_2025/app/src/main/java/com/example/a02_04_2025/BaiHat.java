package com.example.a02_04_2025;

import java.io.Serializable;

public class BaiHat implements Serializable {
    String name;
    String singer;
    float time;

    public BaiHat(String name, String singer, float time){
        this.name = name;
        this.singer = singer;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getSinger() {
        return singer;
    }

    public float getTime() {
            return time;
    }
}
