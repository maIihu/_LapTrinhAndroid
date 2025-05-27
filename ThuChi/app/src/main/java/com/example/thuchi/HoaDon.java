package com.example.thuchi;

import java.util.Date;

public class HoaDon {
    String ma;
    String hoTen;
    Date ngayThang;
    double donGia;
    int soNgay;

    public HoaDon(String ma, String hoTen, Date ngayThang, double donGia, int soNgay) {
        this.donGia = donGia;
        this.hoTen = hoTen;
        this.ma = ma;
        this.ngayThang = ngayThang;
        this.soNgay = soNgay;
    }

    public double tinhTienHD(){
        return donGia * soNgay * (100 -5*(4%4+1))/100;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(Date ngayThang) {
        this.ngayThang = ngayThang;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }
}
