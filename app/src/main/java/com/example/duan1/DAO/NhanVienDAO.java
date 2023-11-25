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

    public boolean insert(String tenNv, String namsinh, String Username, String password) {
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenNv", tenNv);
        values.put("namsinh", namsinh);
        values.put("Username", Username);
        values.put("password", password);
        long check = db.insert("NhanVien", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean update(int MaNV, String tenNv, String namsinh, String Username, String password) {
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenNv", tenNv);
        values.put("namsinh", namsinh);
        values.put("Username", Username);
        values.put("password", password);
        long check = db.update("NhanVien", values, "MaNV =?", new String[]{String.valueOf(MaNV)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean delete(int MaNV) {
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        long check = db.delete("NhanVien", "MaNV = ?", new String[]{String.valueOf(MaNV)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<NhanVienModel> tim(String name) {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        SQLiteDatabase db = DbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from NhanVien where tenNv like ?", new String[]{name});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new NhanVienModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));

            } while (cursor.moveToNext());
        }
        return list;
    }
}
