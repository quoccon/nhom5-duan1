package com.example.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.Database.db;
import com.example.duan1.Model.DichVuModel;
import com.example.duan1.Model.LoaiPhongModel;
import com.example.duan1.Model.NhanVienModel;

import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    SQLiteDatabase data;


    public DichVuDAO(Context context){
        db db1 = new db(context);
        data = db1.getWritableDatabase();
    }

    public long insertDV(DichVuModel objDV){
        ContentValues values = new ContentValues();
        values.put("tendV",objDV.getTenDV());
        values.put("giatien",objDV.getGiatien());
        values.put("mota",objDV.getMotaDV());
        return data.insert("DichVu",null,values);
    }
    public long updateDV(DichVuModel objDV){
        ContentValues values = new ContentValues();
        values.put("tendV",objDV.getTenDV());
        values.put("giatien",objDV.getGiatien());
        values.put("mota",objDV.getMotaDV());
        return  data.update("DichVu",values,"maDv = ?",new String[]{String.valueOf(objDV.getMaDV())});
    }
    public long delete(String id){
        return data.delete("DichVu","maDv = ?", new String[]{String.valueOf(id)});

    }
    public List<DichVuModel> getAll() {
        String sql = "SELECT * FROM DichVu";
        return getData(sql);
    }

    public DichVuModel getID(String id) {
        String sql = "SELECT * FROM DichVu WHERE maDv=?";
        List<DichVuModel> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<DichVuModel> getData(String sql, String... selectionArgs) {
        List<DichVuModel> list = new ArrayList<>();
        Cursor cursor = data.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DichVuModel obj = new DichVuModel();
            obj.setMaDV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maDv"))));
            obj.setTenDV(cursor.getString(cursor.getColumnIndex("tendV")));
            obj.setGiatien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giatien"))));
            obj.setMotaDV(cursor.getString(cursor.getColumnIndex("mota")));
            list.add(obj);
        }
        return list;
    }

    public String getTen(String maDv) {
        DichVuModel obj = new DichVuModel();
        Cursor cursor = data.rawQuery("select * from DichVu where maDv =? ",new String[]{maDv});
        while (cursor.moveToNext()) {

            obj.setMaDV(Integer.parseInt(cursor.getString(0)));
            obj.setTenDV(cursor.getString(1));
            Log.e("TAG", "getTen: "+obj.getTenDV() );

        }
        return obj.getTenDV();
    }
}

