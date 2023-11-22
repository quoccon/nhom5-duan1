package com.example.duan1.Model;

public class KhachHangModel {
    private int MaKH;
    private String TenKhachHang;
    private String NamSinh;
    private String CCCD;

    public KhachHangModel() {
    }

    public KhachHangModel(int maKH, String tenKhachHang, String namSinh, String CCCD) {
        MaKH = maKH;
        TenKhachHang = tenKhachHang;
        NamSinh = namSinh;
        this.CCCD = CCCD;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }
}
