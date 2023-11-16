package com.example.duan1.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.db;
import com.example.duan1.Model.HoaDonModel;

public class HoaDonDAO {
    private final db dbHelper;

    public HoaDonDAO(Context context){
        dbHelper = new db(context);
    }

    public long insertHoaDon(HoaDonModel hoaDonModel){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("giobd",hoaDonModel.getGiodb());
        values.put("giokt",hoaDonModel.getGiokt());
        values.put("ngaybd",hoaDonModel.getNgaydb());
        values.put("ngaykt",hoaDonModel.getNgaykt());
        values.put("tongTien",hoaDonModel.getTongTien());
        values.put("maKH",hoaDonModel.getMaKH());
        values.put("maLoai",hoaDonModel.getMaLoai());
        values.put("maPhong",hoaDonModel.getMaPhong());
        values.put("maDv",hoaDonModel.getMaDv());
        values.put("maNv",hoaDonModel.getMaNv());

        long id = sqLiteDatabase.insert("HoaDon",null,values);
        sqLiteDatabase.close();
        return id;
    }



    public int updateHoaDon(HoaDonModel hoaDonModel){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("giodb", hoaDonModel.getGiodb());
        values.put("ngaydb", hoaDonModel.getNgaydb());
        values.put("giokt", hoaDonModel.getGiokt());
        values.put("ngaykt", hoaDonModel.getNgaykt());
        values.put("tongTien", hoaDonModel.getTongTien());
        values.put("maKH", hoaDonModel.getMaKH());
        values.put("maLoai", hoaDonModel.getMaLoai());
        values.put("maPhong", hoaDonModel.getMaPhong());
        values.put("maDv", hoaDonModel.getMaDv());
        values.put("maNv", hoaDonModel.getMaNv());

        int rowsAffected = sqLiteDatabase.update("HoaDon",values,"maHD = ?", new String[]{String.valueOf(hoaDonModel.getMaHd())});
        sqLiteDatabase.close();
        return rowsAffected;
    }


    public void deleteHoaDon(long maHD){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.delete("HoaDon","maHD = ?",new String[]{String.valueOf(maHD)});
        sqLiteDatabase.close();
    }
}
