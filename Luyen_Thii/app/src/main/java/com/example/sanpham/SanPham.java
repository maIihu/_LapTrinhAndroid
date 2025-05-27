package com.example.sanpham;

import java.io.Serializable;

public class SanPham implements Serializable {
    int id;
    String tenSp;
    int giaTien;
    boolean khuyenMai;

    public SanPham(int id, String tenSp, int giaTien, boolean khuyenMai) {
        this.giaTien = giaTien;
        this.id = id;
        this.khuyenMai = khuyenMai;
        this.tenSp = tenSp;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public int getId() {
        return id;
    }

    public boolean isKhuyenMai() {
        return khuyenMai;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKhuyenMai(boolean khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }
}
