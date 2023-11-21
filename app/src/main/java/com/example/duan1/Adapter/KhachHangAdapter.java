package com.example.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.R;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<KhachHangModel> list;

    KhachHangDAO dao;

    public KhachHangAdapter (Context context ,ArrayList<KhachHangModel> list ){
        this.context=context;
        this.list = list;
        dao = new KhachHangDAO(context);
    }

    @NonNull
    @Override
    public KhachHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangAdapter.ViewHolder holder, int position) {
        holder.txtMaKH.setText(String.valueOf(list.get(position).getMaKH()));
        holder.txtTenKH.setText(list.get(position).getTenKhachHang());
        holder.txtNamSinh_KH.setText(String.valueOf(list.get(position).getNamSinh()));
        holder.txtCCCD_KH.setText(String.valueOf(list.get(position).getCCCD()));
        KhachHangModel nv = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaKH, txtTenKH, txtNamSinh_KH, txtSDT_KH, txtCCCD_KH;
        ImageView deleteKH;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaKH = itemView.findViewById(R.id.MaKH);
            txtTenKH = itemView.findViewById(R.id.TenKH);
            txtNamSinh_KH = itemView.findViewById(R.id.NamSinh_KH);
            txtSDT_KH = itemView.findViewById(R.id.SDT_KH);
            txtCCCD_KH = itemView.findViewById(R.id.CCCD_KH);
            deleteKH = itemView.findViewById(R.id.deleteKH);

        }
    }
}
