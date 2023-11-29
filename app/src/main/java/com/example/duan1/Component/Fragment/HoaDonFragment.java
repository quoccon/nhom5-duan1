package com.example.duan1.Component.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1.Model.PhongModel;
import com.example.duan1.R;


public class HoaDonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);


        Bundle bundle = getArguments();
        if(bundle!=null){
            PhongModel phongModel = bundle.getParcelable("Phong");
            String gioKetThuc = bundle.getString("gioKetThuc");
        }
        // Inflate the layout for this fragment
        return view;
    }
}