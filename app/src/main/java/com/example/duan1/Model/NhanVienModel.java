package com.example.duan1.Model;

public class NhanVienModel {
    private int MaNv;
    private String tenNv;
    private String namsinh;
    private String Username;
    private String password;

    public NhanVienModel() {
    }

    public NhanVienModel(int maNv, String tenNv, String namsinh, String username, String password) {
        MaNv = maNv;
        this.tenNv = tenNv;
        this.namsinh = namsinh;
        Username = username;
        this.password = password;
    }

    public int getMaNv() {
        return MaNv;
    }

    public void setMaNv(int maNv) {
        MaNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
