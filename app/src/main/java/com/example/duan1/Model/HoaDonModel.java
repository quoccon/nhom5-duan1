package com.example.duan1.Model;

public class HoaDonModel {
    private int maHd;
    private String ngaydb;
    private String ngaykt;
    private String giodb;
    private String giokt;
    private int tongTien;
    private int maKH;
    private int maLoai;
    private int maPhong;
    private int maDv;
    private int maNv;

    public HoaDonModel(int maHd, String ngaydb, String ngaykt, String giodb, String giokt, int tongTien, int maKH, int maLoai, int maPhong, int maDv, int maNv) {
        this.maHd = maHd;
        this.ngaydb = ngaydb;
        this.ngaykt = ngaykt;
        this.giodb = giodb;
        this.giokt = giokt;
        this.tongTien = tongTien;
        this.maKH = maKH;
        this.maLoai = maLoai;
        this.maPhong = maPhong;
        this.maDv = maDv;
        this.maNv = maNv;
    }

    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    public String getNgaydb() {
        return ngaydb;
    }

    public void setNgaydb(String ngaydb) {
        this.ngaydb = ngaydb;
    }

    public String getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(String ngaykt) {
        this.ngaykt = ngaykt;
    }

    public String getGiodb() {
        return giodb;
    }

    public void setGiodb(String giodb) {
        this.giodb = giodb;
    }

    public String getGiokt() {
        return giokt;
    }

    public void setGiokt(String giokt) {
        this.giokt = giokt;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaDv() {
        return maDv;
    }

    public void setMaDv(int maDv) {
        this.maDv = maDv;
    }

    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }
}
