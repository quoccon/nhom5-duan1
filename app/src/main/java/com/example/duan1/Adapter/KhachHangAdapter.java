package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1.Component.Fragment.DichVuFragment;
import com.example.duan1.Component.Fragment.KhachHangFragment;
import com.example.duan1.Model.DichVuModel;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.R;

import java.util.ArrayList;

public class KhachHangAdapter extends ArrayAdapter<KhachHangModel> {
    private Context context;
    KhachHangFragment khachHangFragment;
    private ArrayList <KhachHangModel> list;


    TextView  txtmaKH,txtTenKH, txtNamSinh_KH, txtCCCD_KH;
    ImageView deleteKH;

    public KhachHangAdapter(Context context , KhachHangFragment khachHangFragment , ArrayList<KhachHangModel> list){
        super(context,0,list);
        this.context = context;
        this.khachHangFragment = khachHangFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khachhang,null);
        }
        final KhachHangModel item = list.get(position);
        if (item != null){
            txtmaKH = v.findViewById(R.id.MaKH);
            txtTenKH = v.findViewById(R.id.TenKH);
            txtNamSinh_KH = v.findViewById(R.id.NamSinh_KH);
            txtCCCD_KH = v.findViewById(R.id.CCCD_KH);
            deleteKH = v.findViewById(R.id.deleteKH);

            txtmaKH.setText(item.getMaKH() + " ");
            txtTenKH.setText(item.getTenKhachHang());
            txtNamSinh_KH.setText(item.getNamSinh());
            txtCCCD_KH.setText(item.getCCCD());

            deleteKH = v.findViewById(R.id.deleteKH);
        }
        deleteKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khachHangFragment.DeleteKH(String.valueOf(item.getMaKH()));
            }
        });
        return v;
    }
}
