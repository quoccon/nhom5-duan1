package com.example.duan1.Model;

public class NhanVienModel {
    private int MaNV;
    private String tenNV;
    private String NamSinh;
    private String TaiKhoan;
    private String MatKhau;

    public NhanVienModel() {
    }

    public NhanVienModel(int maNV, String tenNV, String namSinh, String taiKhoan, String matKhau) {
        MaNV = maNV;
        this.tenNV = tenNV;
        NamSinh = namSinh;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
