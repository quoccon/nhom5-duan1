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

import com.example.duan1.Component.Fragment.LoaiPhongFragment;
import com.example.duan1.Model.LoaiPhongModel;
import com.example.duan1.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiPhongModel> {
    private Context context;
    LoaiPhongFragment loaiPhongFragment;

    private ArrayList<LoaiPhongModel> list;

    TextView tv_maLoai,tv_tenLoai;
    ImageView deleteLP;


    public LoaiSachAdapter (Context context, LoaiPhongFragment loaiPhongFragment , ArrayList<LoaiPhongModel> list){
        super(context,0 ,list);
        this.context  = context;
        this.list = list;
        this.loaiPhongFragment = loaiPhongFragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loaiphong,null);
        }
        final LoaiPhongModel item = list.get(position);
        if (item !=null){
            //ánh xạ
            tv_maLoai = v.findViewById(R.id.tv_maLP);
            tv_tenLoai = v.findViewById(R.id.tv_tenLP);

            //set
            tv_maLoai.setText(item.getMaLoai() + " ");
            tv_tenLoai.setText(item.getTenLoai());
        }

        // code hành động nút xoóa
        deleteLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }
}
