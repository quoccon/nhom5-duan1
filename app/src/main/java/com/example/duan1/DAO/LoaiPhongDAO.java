package com.example.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.db;
import com.example.duan1.Model.LoaiPhongModel;

import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDAO {

     SQLiteDatabase data;


    public LoaiPhongDAO(Context context){
        db db1 = new db(context);
        data = db1.getWritableDatabase();
    }

    public long insertLP(LoaiPhongModel objLoai){
        ContentValues values = new ContentValues();
        values.put("tenLoai",objLoai.getTenLoai());
        return data.insert("LoaiPhong",null,values);
    }
    public long updateLP(LoaiPhongModel objLoai){
        ContentValues values = new ContentValues();
        values.put("tenLoai",objLoai.getTenLoai());
        return  data.update("LoaiPhong",values,"maLoai = ?",new String[]{String.valueOf(objLoai.getMaLoai())});
    }
    public long delete(String id){
        return data.delete("LoaiPhong","maLoai = ?", new String[]{String.valueOf(id)});

    }
    public List<LoaiPhongModel> getAll() {
        String sql = "SELECT * FROM LoaiPhong";
        return getData(sql);
    }

    public LoaiPhongModel getID(String id) {
        String sql = "SELECT * FROM LoaiPhong WHERE maLoai=?";
        List<LoaiPhongModel> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<LoaiPhongModel> getData(String sql, String... selectionArgs) {
        List<LoaiPhongModel> list = new ArrayList<>();
        Cursor cursor = data.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiPhongModel obj = new LoaiPhongModel();
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }
}
