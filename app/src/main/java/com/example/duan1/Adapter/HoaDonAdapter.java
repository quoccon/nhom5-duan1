package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1.DAO.DichVuDAO;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.DAO.LoaiPhongDAO;
import com.example.duan1.DAO.NhanVienDAO;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.HoaDonModel;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.StringJoiner;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HoaDonModel> hoaDonList;

    HoaDonDAO hoaDonDAO;

    LoaiPhongDAO loaiPhongDAO;

    KhachHangDAO khachHangDAO;

    DichVuDAO dichVuDAO;

    NhanVienDAO nhanVienDAO;

    PhongDAO phongDAO;




    public HoaDonAdapter(Context context, ArrayList<HoaDonModel> hoaDonList) {
        this.context = context;
        this.hoaDonList = hoaDonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtBd.setText(hoaDonList.get(position).getThoiGianBD());
        holder.txtKt.setText(hoaDonList.get(position).getThoiGianKT());
        holder.txtmahd.setText(String.valueOf(hoaDonList.get(position).getMaHd()));
        holder.txtTongtien.setText(String.valueOf(hoaDonList.get(position).getTongTien()));
        holder.txttenkh.setText(getMaKhachHang(hoaDonList.get(position).getMaKH()));
        holder.txtloaiphong.setText(getLoaiPhong(hoaDonList.get(position).getMaLoai()));
        holder.txtmaphong.setText(getmaPhong(hoaDonList.get(position).getMaPhong()));
        holder.txtDv.setText(getDichVu(hoaDonList.get(position).getMaDv()));
//        holder.txt.setText(getNhanVien(hoaDonList.get(position).getMaNv()));



    }

    private String getDichVu(String maDv) {
        return dichVuDAO.getTen(maDv);
    }

    private String getmaPhong(String maPhong) {
        return phongDAO.getTen(maPhong);
    }

    private String getLoaiPhong(String maLoai) {
        return loaiPhongDAO.getTen(maLoai);
    }

    private String getMaKhachHang(String maKH) {
        return khachHangDAO.getTen(maKH);
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtmahd,txttenkh,txtmaphong,txtloaiphong,txtBd,txtKt,txtDv,txtTongtien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            txtmahd = itemView.findViewById(R.id.txtmahd);
            txttenkh = itemView.findViewById(R.id.txttenkh);
            txtmaphong = itemView.findViewById(R.id.txtmaPhong);
            txtloaiphong = itemView.findViewById(R.id.txtloaip);
            txtBd = itemView.findViewById(R.id.txtbd);
            txtKt = itemView.findViewById(R.id.txtkt);
            txtDv = itemView.findViewById(R.id.txtdv);
            txtTongtien = itemView.findViewById(R.id.txtTongtien);
        }
    }
}
