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
    private final db dbHelper;

    public PhongDAO(Context context){
        dbHelper = new db(context);
    }

  public List<PhongModel> getAllPhong() {
        List<PhongModel> listPhong = new ArrayList<>();

        try (SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
             Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Phong", null)) {

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    listPhong.add(new PhongModel(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getString(3)
                    ));
                } while (cursor.moveToNext());
            }
        }

        return listPhong;
    }


    public long insertPhong(PhongModel phongModel){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("giaThue",phongModel.getGiaThue());
        values.put("trangthai",phongModel.getTrangThai());
        values.put("maLoai",phongModel.getMaLoai());

        long id = sqLiteDatabase.insert("Phong",null,values);
        sqLiteDatabase.close();
        return id;
    }



    public int updatePhong(PhongModel phongModel){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("giaThue", phongModel.getGiaThue());
        values.put("trangThai", phongModel.getTrangThai());
        values.put("maLoai", phongModel.getMaLoai());

        int rowsAffected = sqLiteDatabase.update("Phong",values,"maPhong = ?",new String[]{String.valueOf(phongModel.getMaPhong())});
        sqLiteDatabase.close();
        return rowsAffected;
    }


    public boolean deletePhong(int maPhong){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.delete("Phong","maPhong = ?",new String[]{String.valueOf(maPhong)});
        sqLiteDatabase.close();
        return false;
    }
}
