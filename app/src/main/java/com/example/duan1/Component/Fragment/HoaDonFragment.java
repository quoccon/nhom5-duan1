package com.example.duan1.Component.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1.Adapter.HoaDonAdapter;
import com.example.duan1.Adapter.NhanVienAdapter;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.Model.HoaDonModel;
import com.example.duan1.Model.PhongModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class HoaDonFragment extends Fragment {

    FloatingActionButton fladd;

    RecyclerView recyclerView;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<HoaDonModel> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);


        fladd = view.findViewById(R.id.add_HD);
        recyclerView = view.findViewById(R.id.rcvHd);
        hoaDonDAO = new HoaDonDAO(getContext());
        loadData();


        fladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddTV();
            }
        });




        return view;
    }
    private void loadData() {
        list = hoaDonDAO.getHoaDonModel();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        hoaDonAdapter = new HoaDonAdapter(getContext(), list);
        recyclerView.setAdapter(hoaDonAdapter);
    }

    private void dialogAddTV(){



    }
}