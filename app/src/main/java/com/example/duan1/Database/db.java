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
        String dbNhanVien = "create table NhanVien(MaNv integer primary key autoincrement,tenNv text,namsinh text, password text)";
        sqLiteDatabase.execSQL(dbNhanVien);

        String dbLoaiPhong = "create table LoaiPhong(maLoai integer primary key autoincrement, tenLoai text)";
        sqLiteDatabase.execSQL(dbLoaiPhong);

        String dbPhong = "create table Phong(maPhong integer primary key autoincrement, giaThue integer,trangThai text, maLoai text references LoaiPhong(maLoai))";
        sqLiteDatabase.execSQL(dbPhong);

        String dbKhachHang = "create table KhachHang(maKH integer primary key autoincrement,tenKH text, namsinh integer, cccd text)";
        sqLiteDatabase.execSQL(dbKhachHang);

        String dbDichVu = "create table DichVu(maDv integer primary key autoincrement, tendV text, giatien integer, mota text)";
        sqLiteDatabase.execSQL(dbDichVu);

        String dbHoaDon = "create table HoaDon(maHD integer primary key autoincrement, giodb text, ngaydb text, giokt text,ngaykt text,tongTien integer," +
                "maKH integer references KhachHang(maKH),maLoai integer references LoaiPhong(maLoai),maPhong integer references Phong(maPhong),maDv integer references DichVu(maDv),maNv integer references NhanVien(maNv))";
        sqLiteDatabase.execSQL(dbHoaDon);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("drop table if exists NhanVien");
            sqLiteDatabase.execSQL("drop table if exists LoaiPhong");
            sqLiteDatabase.execSQL("drop table if exists Phong");
            sqLiteDatabase.execSQL("drop table if exists KhachHang");
            sqLiteDatabase.execSQL("drop table if exists DichVu");
            sqLiteDatabase.execSQL("drop table if exists HoaDon");
            onCreate(sqLiteDatabase);
        }
    }
}
