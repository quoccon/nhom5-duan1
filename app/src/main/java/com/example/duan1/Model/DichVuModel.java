package com.example.duan1.Model;

public class DichVuModel {
    private int maDV;
    private String tenDV;
    private int giatien;
    private String motaDV;

    public DichVuModel() {
    }

    public DichVuModel(int maDV, String tenDV, int giatien, String motaDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giatien = giatien;
        this.motaDV = motaDV;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public String getMotaDV() {
        return motaDV;
    }

    public void setMotaDV(String motaDV) {
        this.motaDV = motaDV;
    }
}
