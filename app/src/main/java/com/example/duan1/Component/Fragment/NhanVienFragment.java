package com.example.duan1.Component.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan1.Adapter.NhanVienAdapter;
import com.example.duan1.DAO.NhanVienDAO;
import com.example.duan1.Model.NhanVienModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class NhanVienFragment extends Fragment {
    FloatingActionButton addnv;
    RecyclerView rcv;
    NhanVienDAO dao;

    SearchView searchView;
    ArrayList<NhanVienModel> list;


    public NhanVienFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        rcv = view.findViewById(R.id.rcv_NV);
        addnv = view.findViewById(R.id.add_NV);
        searchView = view.findViewById(R.id.sv);
        dao = new NhanVienDAO(getContext());
        loadData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    list.clear();
                    list.addAll(dao.getNhanVienModel());
                    adapter.notifyDataSetChanged();
                }else {
                    list.clear();
                    list.addAll(dao.tim("%"+newText+"%"));
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });





        addnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddTV();
            }
        });
        return view;
    }

    NhanVienAdapter adapter;

    private void loadData() {
        list = dao.getNhanVienModel();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        adapter = new NhanVienAdapter(getContext(), list);
        rcv.setAdapter(adapter);
    }


    @SuppressLint("MissingInflatedId")
    private void dialogAddTV() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_nhanvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout a_addNV = view.findViewById(R.id.a_addNV);
        TextInputEditText b_addNV = view.findViewById(R.id.b_addNV);
        TextInputLayout a_addNSNNV = view.findViewById(R.id.a_addNSNV);
        TextInputEditText b_addNSNV = view.findViewById(R.id.b_addNSNV);
        TextInputLayout a_addTK = view.findViewById(R.id.a_addTK);
        TextInputEditText b_addTK = view.findViewById(R.id.b_addTK);
        TextInputLayout a_addMK = view.findViewById(R.id.a_addMK);
        TextInputEditText b_addMK = view.findViewById(R.id.b_addMK);
        Button btnaddnv = view.findViewById(R.id.addnhanvien);

        b_addNV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addNV.setError(" vui lòng nhập tên nhân viên");
                } else {
                    a_addNV.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        b_addNSNV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addNSNNV.setError("vui lòng nhập năm sinh nhân viên");
                } else {
                    a_addNSNNV.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addTK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addMK.setError("vui lòng nhập tài khoản");
                } else {
                    a_addTK.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        b_addMK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addMK.setError("vui lòng nhập mật khẩu nhân viên");
                } else {
                    a_addMK.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnaddnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = b_addNV.getText().toString();
                String namsinh = b_addNSNV.getText().toString();
                String taikhoan = b_addTK.getText().toString();
                String MatKhau = b_addMK.getText().toString();
                boolean check = dao.insert(ten, namsinh, taikhoan, MatKhau);

                if (ten.isEmpty() || namsinh.isEmpty() || taikhoan.isEmpty() || MatKhau.isEmpty()) {
                    if (ten.equals("")) {
                        a_addNV.setError("vui lòng nhập đầy đủ tên nhân viên ");
                    } else {
                        a_addNV.setError(null);
                    }
                    if (namsinh.equals("")) {
                        a_addNSNNV.setError("vui lòng nhập đầy đủ năm sinh");
                    } else {
                        a_addNSNNV.setError(null);
                    }
                    if (taikhoan.equals("")) {
                        a_addTK.setError("vui lòng nhập tài khoản");
                    } else {
                        a_addTK.setError(null);
                    }
                    if (MatKhau.equals("")) {
                        a_addMK.setError("vui lòng nhập mật khẩu");
                    } else {
                        a_addMK.setError(null);
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