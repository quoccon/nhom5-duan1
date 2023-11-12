package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class db extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATA_VERSION = 1;



    public db(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_NhanVien = "create table NhanVien(maNV,tenNv,namsinh,cccd,username,password)";
        sqLiteDatabase.execSQL(tb_NhanVien);
        String tb_LoaiPhong = "create table LoaiPhong(maLoai,tenLoai)";
        sqLiteDatabase.execSQL(tb_LoaiPhong);
        String tb_Phong = "create table Phong(maPhong,soPhong,giaThue,trangthai,maLoai)";
        sqLiteDatabase.execSQL(tb_Phong);
        String tb_KhachHang = "create table KhachHang(maKH,tenKH,namsinh,cccd)";
        sqLiteDatabase.execSQL(tb_KhachHang);
        String tb_DichVu = "create table Dichvu(maDv,tenDv,moTa,giaTien)";
        sqLiteDatabase.execSQL(tb_DichVu);
        String tb_HoaDon = "create table HoaDon(maHD,gioBd,gioKT,ngayBD,ngayKT,tongtien,maKH,maLoai,maPhong,maDv,maNV)";
        sqLiteDatabase.execSQL(tb_HoaDon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!=i1){
            sqLiteDatabase.execSQL("drop table if exists NhanVien");
            sqLiteDatabase.execSQL("drop table if exists LoaiPhong");
            sqLiteDatabase.execSQL("drop table if exists Phong");
            sqLiteDatabase.execSQL("drop table if exists KhachHang");
            sqLiteDatabase.execSQL("drop table if exists Dichvu");
            sqLiteDatabase.execSQL("drop table if exists HoaDon");
            onCreate(sqLiteDatabase);
        }
    }
}
