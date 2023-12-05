package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.db;
import com.example.duan1.Model.HoaDonModel;
import com.example.duan1.Model.NhanVienModel;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private final db dbHelper;

    SQLiteDatabase db;

    public HoaDonDAO(Context context) {
        dbHelper = new db(context);
    }

    public ArrayList<HoaDonModel> getHoaDonModel() {
        ArrayList<HoaDonModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HoaDon", null);
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            do {
                list.add(new HoaDonModel(cursor.getInt(0),
                        cursor.getString(1),cursor.getString(2),cursor.getInt(3),
                        cursor.getString(4),cursor.getString(5),
                        cursor.getString(6),cursor.getString(7),cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean insert(HoaDonModel hoaDonModel) {
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ThoiGianBD", hoaDonModel.getThoiGianBD());
        values.put("ThoiGianKT", hoaDonModel.getThoiGianKT());
        values.put("tongTien", hoaDonModel.getTongTien());
        values.put("maKH", hoaDonModel.getMaKH());
        values.put("maLoai", hoaDonModel.getMaLoai());
        values.put("maPhong", hoaDonModel.getMaPhong());
        values.put("maDv", hoaDonModel.getMaDv());
        values.put("maNv", hoaDonModel.getMaNv());

        long check = db.insert("HoaDon", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean updateHoaDon(HoaDonModel hoaDonModel) {
        SQLiteDatabase db =dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ThoiGianBD", hoaDonModel.getThoiGianBD());
        values.put("ThoiGianKT", hoaDonModel.getThoiGianKT());
        values.put("tongTien", hoaDonModel.getTongTien());
        values.put("maKH", hoaDonModel.getMaKH());
        values.put("maLoai", hoaDonModel.getMaLoai());
        values.put("maPhong", hoaDonModel.getMaPhong());
        values.put("maDv", hoaDonModel.getMaDv());
        values.put("maNv", hoaDonModel.getMaNv());
        long check = db.update("HoaDon", values, "maHd =?", new String[]{String.valueOf(hoaDonModel.getMaHd())});
        if (check == -1) {
            return false;
        } else {
            return true;
        }

    }


    public void deleteHoaDon(long maHD) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.delete("HoaDon", "maHD = ?", new String[]{String.valueOf(maHD)});
        sqLiteDatabase.close();
    }


}
