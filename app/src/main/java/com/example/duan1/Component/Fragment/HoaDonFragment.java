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

import com.example.duan1.Adapter.HoaDonAdapter;
import com.example.duan1.Adapter.NhanVienAdapter;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.Model.HoaDonModel;
import com.example.duan1.Model.PhongModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class HoaDonFragment extends Fragment {

    FloatingActionButton fladd;

    RecyclerView recyclerView;
   private HoaDonDAO dao;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<HoaDonModel> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);


        fladd = view.findViewById(R.id.add_HD);
        recyclerView = view.findViewById(R.id.rcvHd);
        dao = new HoaDonDAO(getContext());
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
        list = dao.getHoaDonModel();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        hoaDonAdapter = new HoaDonAdapter(getContext(), list);
        recyclerView.setAdapter(hoaDonAdapter);
    }


    private void dialogAddTV() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_hoadon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

//        TextInputLayout a_addTGBD = view.findViewById(R.id.a_addTGBD);
//        TextInputEditText b_addTGBD = view.findViewById(R.id.b_addTGBD);
        TextInputLayout a_addTGKT = view.findViewById(R.id.a_addTGKT);
        TextInputEditText b_addTGKT = view.findViewById(R.id.b_addTGKT);
        TextInputLayout a_addTT = view.findViewById(R.id.a_addTT);
        TextInputEditText b_addTT = view.findViewById(R.id.b_addTT);
        TextInputLayout a_addMKH = view.findViewById(R.id.a_addMKH);
        TextInputEditText b_addMKH = view.findViewById(R.id.b_addMKH);
        TextInputLayout a_addML = view.findViewById(R.id.a_addML);
        TextInputEditText b_addML = view.findViewById(R.id.b_addML);
        TextInputLayout a_addMP = view.findViewById(R.id.a_addMP);
        TextInputEditText b_addMP = view.findViewById(R.id.b_addMP);
        TextInputLayout a_addMDV = view.findViewById(R.id.a_addMDV);
        TextInputEditText b_addMDV = view.findViewById(R.id.b_addMDV);
        TextInputLayout a_addMNV = view.findViewById(R.id.a_addMNV);
        TextInputEditText b_addMNV = view.findViewById(R.id.b_addMNV);
        Button btnadd = view.findViewById(R.id.addHD);


        b_addMKH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addMKH.setError("vui lòng nhập ma khách hàng");
                } else {
                    a_addMKH.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addMP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addMP.setError("vui lòng nhập ma phòng");
                } else {
                    a_addMP.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addMDV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addMDV.setError("vui lòng nhập ma dịch vụ");
                } else {
                    a_addMDV.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addMNV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addMNV.setError("vui lòng nhập ma nhân viên");
                } else {
                    a_addMNV.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addML.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addML.setError("vui lòng nhâp ma khách hàng");
                } else {
                    a_addML.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        b_addTGBD.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 0) {
//                    a_addTGBD.setError("vui lòng nhập thời gian bắt đầu");
//                } else {
//                    a_addTGKT.setError(null);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        b_addTGKT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addTGKT.setError("vui lòng nhập thời gian kết thúc");
                } else {
                    a_addTGKT.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_addTT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_addTT.setError("vui lòng nhập  tổng tiền");
                } else {
                    a_addTT.setError(null);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String giaThue = b_addTGBD.getText().toString();
                String trangThai = b_addTGKT.getText().toString();
                String maLoai = b_addTT.getText().toString();
                String maKH = b_addMKH.getText().toString();
                String maL = b_addML.getText().toString();
                String maP = b_addMP.getText().toString();
                String madv = b_addMDV.getText().toString();
                String maNV = b_addMNV.getText().toString();

                HoaDonModel hoaDonModel = new HoaDonModel();
//                hoaDonModel.setThoiGianBD(giaThue);
                hoaDonModel.setThoiGianKT(trangThai);
                hoaDonModel.setTongTien(Integer.valueOf(maLoai));
                hoaDonModel.setMaKH(maKH);
                hoaDonModel.setMaLoai(maL);
                hoaDonModel.setMaPhong(maP);
                hoaDonModel.setMaDv(madv);
                hoaDonModel.setMaNv(maNV);
                boolean check = dao.insert(hoaDonModel);

                if ( trangThai.isEmpty() || maLoai.isEmpty()||
                        maKH.isEmpty()||maL.isEmpty()||maP.isEmpty()||madv.isEmpty()||maNV.isEmpty()) {
//                    if (giaThue.equals("")) {
//                        a_addTGBD.setError("vui lòng nhập đầy đủ tên nhân viên ");
//                    } else {
//                        a_addTGBD.setError(null);
//                    }
                    if (trangThai.equals("")) {
                        a_addTGKT.setError("vui lòng nhập đầy đủ năm sinh");
                    } else {
                        a_addTGKT.setError(null);
                    }
                    if (maLoai.equals("")) {
                        a_addTT.setError("vui lòng nhập tài khoản");
                    } else {
                        a_addTT.setError(null);
                    }
                    if (maKH.equals("")){
                        a_addMKH.setError("vui lòng nhập ma khách hàng");
                    }else {
                        a_addMKH.setError(null);
                    }
                    if (maL.equals("")){
                        a_addML.setError("vui lòng nhập ma loại");
                    }else {
                        a_addML.setError(null);
                    }
                    if (maP.equals("")){
                        a_addMP.setError("vui lòng nhập ma phòng");
                    }else {
                        a_addMP.setError(null);
                    }
                    if (madv.equals("")){
                        a_addMDV.setError("vui lòng nhập ma dịch vụ");
                    }else {
                        a_addMDV.setError(null);
                    }
                    if (maNV.equals("")){
                        a_addMNV.setError("vui lòng nhập ma nhân viên");
                    }else {
                        a_addMNV.setError(null);
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