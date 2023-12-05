package com.example.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.Database.db;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.Model.LoaiPhongModel;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    SQLiteDatabase data;


    public KhachHangDAO(Context context){
        db db1 = new db(context);
        data = db1.getWritableDatabase();
    }

    public long insertKH(KhachHangModel objDV){
        ContentValues values = new ContentValues();
        values.put("tenKH",objDV.getTenKhachHang());
        values.put("namsinh",objDV.getNamSinh());
        values.put("cccd",objDV.getCCCD());
        return data.insert("KhachHang",null,values);
    }
    public long updateKH(KhachHangModel objDV){
        ContentValues values = new ContentValues();
        values.put("tenKH",objDV.getTenKhachHang());
        values.put("namsinh",objDV.getNamSinh());
        values.put("cccd",objDV.getCCCD());
        return  data.update("KhachHang",values,"maKH = ?",new String[]{String.valueOf(objDV.getMaKH())});
    }
    public long delete(String id){
        return data.delete("KhachHang","maKH = ?", new String[]{String.valueOf(id)});

    }
    public List<KhachHangModel> getAll() {
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHangModel getID(String id) {
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHangModel> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<KhachHangModel> getData(String sql, String... selectionArgs) {
        List<KhachHangModel> list = new ArrayList<>();
        Cursor cursor = data.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            KhachHangModel obj = new KhachHangModel();
            obj.setMaKH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKH"))));
            obj.setTenKhachHang(cursor.getString(cursor.getColumnIndex("tenKH")));
            obj.setNamSinh(cursor.getString(cursor.getColumnIndex("namsinh")));
            obj.setCCCD(cursor.getString(cursor.getColumnIndex("cccd")));
            list.add(obj);
        }
        return list;
    }

    public String getTen(String maKH) {
        KhachHangModel obj = new KhachHangModel();
        Cursor cursor = data.rawQuery("select * from KhachHang where maKH =? ",new String[]{maKH});
        while (cursor.moveToNext()) {

            obj.setMaKH(Integer.parseInt(cursor.getString(0)));
            obj.setTenKhachHang(cursor.getString(1));
            Log.e("TAG", "getTen: "+obj.getTenKhachHang() );

        }
        return obj.getTenKhachHang();
    }


}

