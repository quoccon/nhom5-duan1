package com.example.duan1.Component.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1.Adapter.NhanVienAdapter;
import com.example.duan1.Adapter.PhongAdapter;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.NhanVienModel;
import com.example.duan1.Model.PhongModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class PhongFragment extends Fragment {
    private RecyclerView recyclerView;
    private PhongDAO dao;
    ArrayList<PhongModel> list;
    private PhongAdapter adapter;
    private FloatingActionButton btnAdd;
    private Spinner spnLoai;
    TextInputEditText edSoP, edGia;
    private String[] roomTypes = {"Loại 1", "Loại 2", "Loại 3"};
    private Button btnAddPhong;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phong, container, false);
        recyclerView = view.findViewById(R.id.rcv_Phong);
        btnAdd = view.findViewById(R.id.add_phong);
        dao = new PhongDAO(getContext());
        loadData();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddTV();
            }
        });
        return view;
    }


    private void loadData() {
        list = dao.getphongModel();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PhongAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    private void dialogAddTV() {


        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_phong, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        TextInputLayout a_addGT = view.findViewById(R.id.a_addGT);
        TextInputEditText b_addGT = view.findViewById(R.id.b_addGT);
        TextInputLayout a_addNBD = view.findViewById(R.id.a_addNBD);
        TextInputEditText b_addNBD = view.findViewById(R.id.b_addNBD);
        TextInputLayout a_addNKT = view.findViewById(R.id.a_addNKT);
        TextInputEditText b_addNKT = view.findViewById(R.id.b_addNKT);

        Button btnaddnv = view.findViewById(R.id.addphong);
        b_addGT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addGT.setError(" vui lòng nhập giá thuê");
                } else {
                    a_addGT.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        b_addNBD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addNBD.setError("vui lòng nhập năm sinh nhân viên");
                } else {
                    a_addNBD.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addNKT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addNKT.setError("vui lòng nhập tài khoản");
                } else {
                    a_addNKT.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnaddnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String giaThue = b_addGT.getText().toString();
                String trangThai = b_addNBD.getText().toString();
                String maLoai = b_addNKT.getText().toString();
                PhongModel phongModel = new PhongModel();
                phongModel.setGiaThue(Integer.parseInt(giaThue));
                phongModel.setTrangThai(trangThai);
                phongModel.setMaLoai(maLoai);
                boolean check = dao.insert( phongModel);

                if (giaThue.isEmpty() || trangThai.isEmpty() || maLoai.isEmpty()) {
                    if (giaThue.equals("")) {
                        a_addGT.setError("vui lòng nhập đầy đủ tên nhân viên ");
                    } else {
                        a_addGT.setError(null);
                    }
                    if (trangThai.equals("")) {
                        a_addNBD.setError("vui lòng nhập đầy đủ năm sinh");
                    } else {
                        a_addNBD.setError(null);
                    }
                    if (maLoai.equals("")) {
                        a_addNKT.setError("vui lòng nhập tài khoản");
                    } else {
                        a_addNKT.setError(null);
                    }
                } else {
                    if (check) {
                        loadData();
                        Toast.makeText(getContext(), "thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }




}