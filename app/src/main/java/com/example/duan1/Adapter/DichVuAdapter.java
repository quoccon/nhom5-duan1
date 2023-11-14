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
import com.example.duan1.Model.DichVuModel;
import com.example.duan1.R;

import java.util.ArrayList;

public class DichVuAdapter extends ArrayAdapter<DichVuModel> {
    private Context context;
    DichVuFragment dichVuFragment;
    private ArrayList <DichVuModel> list;

    TextView tv_maDV , tv_tenDV, tv_giatien,tv_mota;
    ImageView deleteDV;

    public DichVuAdapter(Context context , DichVuFragment dichVuFragment , ArrayList<DichVuModel> list){
        super(context,0,list);
        this.context = context;
        this.dichVuFragment = dichVuFragment;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_dichvu,null);
        }
        final DichVuModel item = list.get(position);
        if (item != null){
            tv_maDV = v.findViewById(R.id.tv_maDV);
            tv_tenDV = v.findViewById(R.id.tv_tenDV);
            tv_giatien = v.findViewById(R.id.tv_GiaDV);
            tv_mota = v.findViewById(R.id.tv_motaDV);

            tv_maDV.setText(item.getMaDV()+ "");
            tv_tenDV.setText(item.getTenDV());
            tv_giatien.setText(item.getGiatien() + "");
            tv_mota.setText(item.getMotaDV());

            deleteDV = v.findViewById(R.id.deleteDV);
        }
        deleteDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }
}
