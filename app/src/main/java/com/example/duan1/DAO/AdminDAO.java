package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.db;

public class AdminDAO {
     db dbHelper;
    public AdminDAO(Context context) {
        dbHelper = new db(context);

    }

    public  boolean checkLogin(String MaAM, String MatKhau) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Admin where MaAM = ? and MatKhau = ?", new String[]{MaAM, MatKhau});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean updateMK(String username, String oldPass, String newPass){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Admin where MaAM = ? and MatKhau = ?", new String[]{username,oldPass});
        if (cursor.getCount() > 0){
            ContentValues values = new ContentValues();
            values.put("MatKhau", newPass);
            long check = db.update("Admin",values,"MaAM = ?",new String[]{username});
            if(check == -1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
}
