package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1.Model.HoaDonModel;
import com.example.duan1.R;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HoaDonModel> hoaDonList;

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
        HoaDonModel hoaDon = hoaDonList.get(position);


        holder.txtmahd.setText(hoaDon.getMaHd());

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
