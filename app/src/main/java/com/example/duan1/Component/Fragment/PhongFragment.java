package com.example.duan1.Component.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    TextInputLayout a_addGT, a_addNBD,a_addNKT,a_tenP;


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
        TextInputLayout a_tenP = view.findViewById(R.id.a_addTenP);
        TextInputEditText b_tenP = view.findViewById(R.id.b_addTenP);

        Button btnaddnv = view.findViewById(R.id.addphong);

        b_tenP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    a_tenP.setError("vui lòng nhập tên phòng");
                }else {
                    a_tenP.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

        b_addNBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mucDoCongViec = {"Trống","Đang sửa chữa","Đã trả phòng"};

                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Chọn trạng thái ");
                builder.setItems(mucDoCongViec, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        b_addNBD.setText(mucDoCongViec[which]);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnaddnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextGT = dialog.findViewById(R.id.b_addGT);
                EditText editTextNBD = dialog.findViewById(R.id.b_addNBD);
                EditText editTextNKT = dialog.findViewById(R.id.b_addNKT);
                EditText editTextTenP = dialog.findViewById(R.id.b_addTenP);

                if (editTextGT != null && editTextNBD != null && editTextNKT != null && editTextTenP != null) {
                    String giaThue = editTextGT.getText().toString().trim();
                    String trangThai = editTextNBD.getText().toString().trim();
                    String maLoai = editTextNKT.getText().toString().trim();
                    String tenP = editTextTenP.getText().toString().trim();

                    if (giaThue.isEmpty() || trangThai.isEmpty() || maLoai.isEmpty() || tenP.isEmpty()) {
                        Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        // Kiểm tra nếu giá tiền không phải là số
                        try {
                            int giaThueValue = Integer.parseInt(giaThue);

                            // Tiếp tục xử lý dữ liệu ...
                            PhongModel phongModel = new PhongModel();
                            phongModel.setGiaThue(giaThueValue);
                            phongModel.setTrangThai(trangThai);
                            phongModel.setMaLoai(maLoai);
                            phongModel.setTenPhong(tenP);

                            boolean check = dao.insert(phongModel);

                            if (check) {
                                loadData();
                                Toast.makeText(getContext(), "Thêm phòng thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Thêm phòng thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            // Nếu giá tiền không phải là số, hiển thị thông báo lỗi
                            editTextGT.setError("Giá tiền phải là số");
                        }
                    }
                } else {
                    // Có lỗi xảy ra khi tìm kiếm EditText
                    Toast.makeText(getContext(), "Đã xảy ra lỗi khi lấy giá trị từ EditText", Toast.LENGTH_SHORT).show();
                }
            }
        });









//        btnaddnv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String giaThue = b_addGT.getText().toString();
//                String trangThai = b_addNBD.getText().toString();
//                String maLoai = b_addNKT.getText().toString();
//                String tenP = b_tenP.getText().toString();
//                PhongModel phongModel = new PhongModel();
//                phongModel.setGiaThue(Integer.parseInt(giaThue));
//                phongModel.setTrangThai(trangThai);
//                phongModel.setMaLoai(maLoai);
//                phongModel.setTenPhong(tenP);
//                boolean check = dao.insert( phongModel);
//
//                if (giaThue.isEmpty() || trangThai.isEmpty() || maLoai.isEmpty()|| tenP.isEmpty()) {
//                    if (giaThue.equals("")) {
//                        a_addGT.setError("vui lòng nhập đầy đủ tên nhân viên ");
//                    } else {
//                        a_addGT.setError(null);
//                    }
//                    if (trangThai.equals("")) {
//                        a_addNBD.setError("vui lòng nhập đầy đủ năm sinh");
//                    } else {
//                        a_addNBD.setError(null);
//                    }
//                    if (maLoai.equals("")) {
//                        a_addNKT.setError("vui lòng nhập tài khoản");
//                    } else {
//                        a_addNKT.setError(null);
//                    }if (tenP.equals("")){
//                        a_tenP.setError("vui lòng tên phòng");
//                    }else {
//                        a_tenP.setError(null);
//                    }
//                } else {
//                    if (check) {
//                        loadData();
//                        Toast.makeText(getContext(), "thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    } else {
//                        Toast.makeText(getContext(), "thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });


    }





}