package com.example.thuchi;

public class BaiHat {
    int id;
    String name;
    String singer;
    int like;
    int share;

    public BaiHat(int id, String name, String singer, int like, int share) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.like = like;
        this.share = share;
    }

    public String getTenCuoiCung() {
        if (singer == null || singer.isEmpty()) return "";
        String[] parts = singer.trim().split("\\s+");
        return parts[parts.length - 1];
    }


    public BaiHat() {

    }

    public int tinhDiem(){
        int A = 68;
        return like + share * 5 + A;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
