package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.db;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.Model.PhongModel;

import java.util.ArrayList;

public class KhachHangDAO {

    db DbHelper;

    public KhachHangDAO(Context context) {
        DbHelper = new db(context);
    }

    public ArrayList<KhachHangModel> getKhachHangModel() {
        ArrayList<KhachHangModel> list = new ArrayList<>();
        SQLiteDatabase db = DbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from KhachHangModel", null);
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            do {
                list.add(new KhachHangModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }


    public boolean insert(KhachHangModel khachHangModel){
        SQLiteDatabase sqLiteDatabase = DbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tenkhachhang",khachHangModel.getTenKhachHang());
        values.put("namsinh",khachHangModel.getNamSinh());
        values.put("CCCD",khachHangModel.getCCCD());

        long check = sqLiteDatabase.insert("KhachHang",null,values);
        sqLiteDatabase.close();
        return true;
    }


    public boolean update(int maKH,String tenkhachhang, int namsinh, String CCCD) {
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tenkhachhang",tenkhachhang);
        values.put("namsinh", namsinh);
        values.put("CCCD", CCCD);
        long check = db.update("KhachHang",values,"MaKH =?", new String[]{String.valueOf(maKH)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
    public void delete(int maKH){
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        db.delete("KhachHang","MaKH = ?",new String[]{String.valueOf(maKH)});
        db.close();
    }


    public boolean insert(String tenkhachhang, int nams, String cccd) {
        SQLiteDatabase sqLiteDatabase = DbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenkhachhang",tenkhachhang);
        values.put("namsinh",nams);
        values.put("CCCD",cccd);
        long check = sqLiteDatabase.insert("KhachHang",null,values);
        if (check == -1) {
            return false;
        } else {
            return false;
        }
    }
}
