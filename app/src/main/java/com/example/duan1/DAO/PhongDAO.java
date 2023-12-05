package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.Database.db;
import com.example.duan1.Model.NhanVienModel;
import com.example.duan1.Model.PhongModel;

import java.util.ArrayList;
import java.util.List;

public class PhongDAO {
    db dbheper;

    SQLiteDatabase data;

    public PhongDAO(Context context) {
        dbheper = new db(context);
        data = dbheper.getWritableDatabase();



    }

    public ArrayList<PhongModel> getphongModel() {
        ArrayList<PhongModel> list = new ArrayList<>();
        SQLiteDatabase db = dbheper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Phong", null);
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            do {
                list.add(new PhongModel(cursor.getInt(0),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean insert(PhongModel phongModel) {
        SQLiteDatabase db =dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("giaThue", phongModel.getGiaThue());
        values.put("trangThai",phongModel.getTrangThai());
        values.put("maloai", phongModel.getMaLoai());
        values.put("TenPhong",phongModel.getTenPhong());

        long check = db.insert("Phong", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean update(PhongModel phongModel) {
        SQLiteDatabase db = dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("giaThue", phongModel.getGiaThue());
        values.put("trangThai",phongModel.getTrangThai());
        values.put("maloai", phongModel.getMaLoai());
        values.put("TenPhong",phongModel.getTenPhong());

        long check = db.update("Phong", values, "maPhong =?", new String[]{String.valueOf(phongModel.getMaPhong())});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
    public int delete(int maPhong){
        SQLiteDatabase db = dbheper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from HoaDon where maPhong = ?",new String[]{String.valueOf(maPhong)});
        if(cursor.getCount() != 0){
            return -1;
        }

        long check = db.delete("Phong","maPhong = ?", new String[]{String.valueOf(maPhong)});
        if(check == -1){
            return 0;
        }else{
            return 1;
        }
    }

    public String getTen(String maPhong) {
        PhongModel obj = new PhongModel();
        Cursor cursor = data.rawQuery("select * from Phong where maPhong =? ",new String[]{maPhong});
        while (cursor.moveToNext()) {

            obj.setMaPhong(Integer.parseInt(cursor.getString(0)));
            obj.setTenPhong(cursor.getString(1));
            Log.e("TAG", "getTen: "+obj.getTenPhong() );

        }
        return obj.getTenPhong();
    }
}








