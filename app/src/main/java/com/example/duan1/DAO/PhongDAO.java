package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.db;
import com.example.duan1.Model.PhongModel;

import java.util.ArrayList;
import java.util.List;

public class PhongDAO {
    db dbheper;

    SQLiteDatabase sqLiteDatabase;

    public PhongDAO(Context context) {
        dbheper = new db(context);
        sqLiteDatabase= dbheper.getWritableDatabase();
    }

    public ArrayList<PhongModel> getDSphong() {
        ArrayList<PhongModel> list = new ArrayList<>();
        SQLiteDatabase db = dbheper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select sc.MaSach, sc.TenSach, sc.GiaThue, ls.MaLoai, ls.HoTen from Sach sc, LoaiSach ls where sc.MaLoai = ls.MaLoai", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new PhongModel(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean insert(String tensach, int tienthue, int maloai) {
        SQLiteDatabase db = dbheper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenSach", tensach);
        values.put("GiaThue", tienthue);
        values.put("MaLoai", maloai);
        long check = db.insert("Sach", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
}
