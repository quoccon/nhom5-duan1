package com.example.duan1.Component.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.duan1.Adapter.PhongAdapter;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.PhongModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class PhongFragment extends Fragment {
    private RecyclerView recyclerView;
    private PhongDAO dao;
    private ArrayList<PhongModel> listPhong;
    private PhongAdapter adapter;
    private FloatingActionButton btnAdd;
    private Spinner spnLoai;
    TextInputEditText edSoP,edGia;
    private  String[] roomTypes = {"Loại 1","Loại 2","Loại 3"};
    private Button btnAddPhong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phong,container,false);
        recyclerView = view.findViewById(R.id.rcv_Phong);
        btnAdd = view.findViewById(R.id.add_phong);
        dao = new PhongDAO(getContext());
//        loadData();
        
        
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_themphong,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        spnLoai = view.findViewById(R.id.spnLoai);
        edSoP = view.findViewById(R.id.edSoP);
        edGia = view.findViewById(R.id.edGiaT);
        btnAddPhong = view.findViewById(R.id.btnAddPhong);

        ArrayAdapter<String> adapterLoai = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,roomTypes);
        spnLoai.setAdapter(adapterLoai);

//        btnAddPhong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveRoom();
//                dialog.dismiss();
//            }
//        });
    }

//    private void saveRoom() {
//    String soP = edSoP.getText().toString().trim();
//    String GiaThue = edGia.getText().toString().trim();
//    String loaiPhong = spnLoai.getSelectedItem().toString();
//
//    if (soP.isEmpty() || GiaThue.isEmpty()){
//        return;
//    }
//        int giaThue = Integer.parseInt(GiaThue);
//        PhongModel newPhong = new PhongModel();
//        newPhong.setMaPhong(Integer.parseInt(soP));
//        newPhong.setGiaThue(giaThue);
//        newPhong.setMaLoai(loaiPhong);
//        newPhong.setTrangThai(String.valueOf(0));
//
//        long results = dao.insertPhong(newPhong);
//
//        if (results > 0){
//            loadData();
//        }
//        else {
//
//        }
//
//    }
//
//    private void loadData() {
//        listPhong = (ArrayList<PhongModel>) dao.getAllPhong();
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new PhongAdapter(getContext(),listPhong);
//        recyclerView.setAdapter(adapter);
//    }
}