package com.example.duan1.Model;

public class HoaDonModel {
    private int maHd;

    private int tongTien;
    private String tinhtrang;
    private String maKH;
    private String maLoai;
    private String maPhong;
    private String maDv;

    private String ThoiGianBD;
    private String ThoiGianKT;
    public HoaDonModel() {
    }

    public HoaDonModel(int maHd, int tongTien, String tinhtrang, String maKH, String maLoai, String maPhong, String maDv, String thoiGianBD, String thoiGianKT) {
        this.maHd = maHd;
        this.tongTien = tongTien;
        this.tinhtrang = tinhtrang;
        this.maKH = maKH;
        this.maLoai = maLoai;
        this.maPhong = maPhong;
        this.maDv = maDv;
        ThoiGianBD = thoiGianBD;
        ThoiGianKT = thoiGianKT;
    }

    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaDv() {
        return maDv;
    }

    public void setMaDv(String maDv) {
        this.maDv = maDv;
    }

    public String getThoiGianBD() {
        return ThoiGianBD;
    }

    public void setThoiGianBD(String thoiGianBD) {
        ThoiGianBD = thoiGianBD;
    }

    public String getThoiGianKT() {
        return ThoiGianKT;
    }

    public void setThoiGianKT(String thoiGianKT) {
        ThoiGianKT = thoiGianKT;
    }
}
