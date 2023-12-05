package com.example.duan1.Model;

public class HoaDonModel {
    private int maHd;
    private String ThoiGianBD;
    private String ThoiGianKT;

    private int tongTien;
    private String maKH;
    private String maLoai;
    private String maPhong;
    private String maDv;
    private String maNv;

    public HoaDonModel() {
    }

    public HoaDonModel(int maHd, String thoiGianBD, String thoiGianKT, int tongTien, String maKH, String maLoai, String maPhong, String maDv, String maNv) {
        this.maHd = maHd;
        ThoiGianBD = thoiGianBD;
        ThoiGianKT = thoiGianKT;
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

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
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

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }
}
