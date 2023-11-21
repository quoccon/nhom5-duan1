package com.example.duan1.Component.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan1.Adapter.KhachHangAdapter;
import com.example.duan1.Adapter.NhanVienAdapter;
import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.DAO.NhanVienDAO;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.Model.NhanVienModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class KhachHangFragment extends Fragment {

    FloatingActionButton add_KH;
    RecyclerView rcv_KH;
    KhachHangDAO dao;
    ArrayList<KhachHangModel> list;
    KhachHangAdapter adapter;

    public KhachHangFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        rcv_KH = view.findViewById(R.id.rcv_KH);
        add_KH = view.findViewById(R.id.add_KH);
        dao = new KhachHangDAO(getContext());

        add_KH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diglogaddKH();

            }
        });
        return view;
    }


    private void loadData() {
        list = dao.getKhachHangModel();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv_KH.setLayoutManager(layoutManager);
        adapter = new KhachHangAdapter(getContext(), list);
        rcv_KH.setAdapter(adapter);
    }

    private void diglogaddKH() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_khachhang, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();


        TextInputEditText b_addKH = view.findViewById(R.id.b_addKH);
        TextInputEditText b_addNSKH = view.findViewById(R.id.b_addNSKH);
        TextInputEditText b_addCCCD = view.findViewById(R.id.b_addCCCD);
        TextInputLayout a_addKH = view.findViewById(R.id.a_addKH);
        TextInputLayout a_addNSKH = view.findViewById(R.id.a_addNSKH);
        TextInputLayout a_addCCCD = view.findViewById(R.id.a_addCCCD);
        Button btnaddKH = view.findViewById(R.id.addkhachhang);


        b_addKH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    b_addKH.setError("vui lòng nhập tên khách hàng ");
                } else {
                    b_addKH.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addNSKH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    b_addNSKH.setError("vui lòng nhập năm sinh");
                } else {
                    b_addNSKH.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addCCCD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    b_addNSKH.setError("vui lòng nhập CCCD");
                } else {
                    b_addCCCD.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnaddKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkhachhang = b_addKH.getText().toString();
                String namsinh = b_addNSKH.getText().toString();
                String CCCD = b_addCCCD.getText().toString();

                if (tenkhachhang.isEmpty() || namsinh.isEmpty() || CCCD.isEmpty()) {
                    if (tenkhachhang.equals("")) {
                        a_addKH.setError("vui lòng nhập tên khách hàng ");
                    } else {
                        a_addKH.setError(null);
                    }
                    if (namsinh.equals("")) {
                        a_addNSKH.setError("vui lòng nhập năm sinh");
                    } else {
                        a_addNSKH.setError(null);
                    }
                    if (CCCD.equals("")) {
                        a_addCCCD.setError("vui lòng nhập căn cước");
                    } else {
                        a_addCCCD.setError(null);
                    }

                } else {
                    int nams = Integer.parseInt(namsinh);
                    boolean check = dao.insert(tenkhachhang,nams,CCCD);
                    if (check) {
                        loadData();
                        Toast.makeText(getContext(), "thêm thành công khách hàng", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "thêm khách hàng thất bại", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }
}