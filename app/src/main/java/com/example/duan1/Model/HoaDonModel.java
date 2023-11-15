package com.example.duan1.Model;

public class HoaDonModel {
    private int maHd;
    private String ngaydb;
    private String ngaykt;
    private int tongTien;

    public HoaDonModel(int maHd, String ngaydb, String ngaykt, int tongTien) {
        this.maHd = maHd;
        this.ngaydb = ngaydb;
        this.ngaykt = ngaykt;
        this.tongTien = tongTien;
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

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
