package com.example.duan1.Model;

public class PhongModel {
    private int maPhong;
    private int giaThue;
    private String trangThai;
    private String maLoai;

    private String gioBatDau;

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }
    private String gioKetThuc;

    public String getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public PhongModel() {
    }

    public PhongModel(int maPhong, int giaThue, String trangThai, String maLoai) {
        this.maPhong = maPhong;
        this.giaThue = giaThue;
        this.trangThai = trangThai;
        this.maLoai = maLoai;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }
}
