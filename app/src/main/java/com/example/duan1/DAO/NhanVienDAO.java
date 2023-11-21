package com.example.duan1.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.db;
import com.example.duan1.Model.NhanVienModel;

import java.util.ArrayList;

public class NhanVienDAO {
    db DbHelper;

    public NhanVienDAO(Context context) {
        DbHelper = new db(context);
    }

    public ArrayList<NhanVienModel> getNhanVienModel() {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        SQLiteDatabase db = DbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from NhanVien", null);
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            do {
                list.add(new NhanVienModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean insert(String tennhanvien, String namsinh, String taikhoan, String matkhau) {
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tennhanvien",tennhanvien);
        values.put("namsinh", namsinh);
        values.put("taikhoan", taikhoan);
        values.put("matkhau", matkhau);
        long check = db.insert("NhanVien",null, values);
        if (check == -1) {
            return false;
        } else {
            return false;
        }
    }
    public boolean update(int maNV,String tennhanvien, String namsinh, String taikhoan, String matkhau) {
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tennhanvien",tennhanvien);
        values.put("namsinh", namsinh);
        values.put("taikhoan", taikhoan);
        values.put("matkhau", matkhau);
        long check = db.update("NhanVien",values,"MaNV =?", new String[]{String.valueOf(maNV)});
        if (check == -1) {
            return false;
        } else {
            return false;
        }
    }
    public int delete(int maNV){
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        db.delete("NhanVien","MaNV = ?",new String[]{String.valueOf(maNV)});
        db.close();
        return -1;
    }
}
