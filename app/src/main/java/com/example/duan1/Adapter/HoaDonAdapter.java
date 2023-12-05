package com.example.duan1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1.DAO.DichVuDAO;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.DAO.LoaiPhongDAO;
import com.example.duan1.DAO.NhanVienDAO;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.HoaDonModel;
import com.example.duan1.Model.PhongModel;
import com.example.duan1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.StringJoiner;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HoaDonModel> hoaDonList;

    HoaDonDAO hoaDonDAO;

    LoaiPhongDAO loaiPhongDAO;

    KhachHangDAO khachHangDAO;

    DichVuDAO dichVuDAO;

    NhanVienDAO nhanVienDAO;

    PhongDAO phongDAO;


    public HoaDonAdapter(Context context, ArrayList<HoaDonModel> hoaDonList) {
        this.context = context;
        this.hoaDonList = hoaDonList;
        khachHangDAO = new KhachHangDAO(context);
        loaiPhongDAO = new LoaiPhongDAO(context);
        phongDAO = new PhongDAO(context);
        dichVuDAO = new DichVuDAO(context);
        nhanVienDAO = new NhanVienDAO(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtBd.setText(hoaDonList.get(position).getThoiGianBD());
        holder.txtKt.setText(hoaDonList.get(position).getThoiGianKT());
        holder.txtmahd.setText(String.valueOf(hoaDonList.get(position).getMaHd()));
        holder.txtTongtien.setText(String.valueOf(hoaDonList.get(position).getTongTien()));
        holder.txttenkh.setText(getMaKhachHang(hoaDonList.get(position).getMaKH()));
        holder.txtloaiphong.setText(getLoaiPhong(hoaDonList.get(position).getMaLoai()));
        holder.txtmaphong.setText(getmaPhong(hoaDonList.get(position).getMaPhong()));
        holder.txtDv.setText(getDichVu(hoaDonList.get(position).getMaDv()));
//        holder.txt.setText(getNhanVien(hoaDonList.get(position).getMaNv()));
        HoaDonModel hd = hoaDonList.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                diglogupdateHoaDon(hd);
                return false;
            }
        });


    }

    private String getDichVu(String maDv) {
        return dichVuDAO.getTen(maDv);
    }

    private String getmaPhong(String maPhong) {
        return phongDAO.getTen(maPhong);
    }

    private String getLoaiPhong(String maLoai) {
        return loaiPhongDAO.getTen(maLoai);
    }

    private String getMaKhachHang(String maKH) {
        return khachHangDAO.getTen(maKH);
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtmahd, txttenkh, txtmaphong, txtloaiphong, txtBd, txtKt, txtDv, txtTongtien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            txtmahd = itemView.findViewById(R.id.txtmahd);
            txttenkh = itemView.findViewById(R.id.txttenkh);
            txtmaphong = itemView.findViewById(R.id.txtmaPhong);
            txtloaiphong = itemView.findViewById(R.id.txtloaip);
            txtBd = itemView.findViewById(R.id.txtbd);
            txtKt = itemView.findViewById(R.id.txtkt);
            txtDv = itemView.findViewById(R.id.txtdv);
            txtTongtien = itemView.findViewById(R.id.txtTongtien);
        }
    }

    private void diglogupdateHoaDon(HoaDonModel hoaDonModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.update_hoadon, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();


        //        TextInputLayout a_addTGBD = view.findViewById(R.id.a_addTGBD);
//        TextInputEditText b_addTGBD = view.findViewById(R.id.b_addTGBD);
        TextInputLayout a_addTGKT = view.findViewById(R.id.a_updateTGKT);
        TextInputEditText b_addTGKT = view.findViewById(R.id.b_updateTGKT);
        TextInputLayout a_addTT = view.findViewById(R.id.a_updateTT);
        TextInputEditText b_addTT = view.findViewById(R.id.b_updateTT);
        TextInputLayout a_addMKH = view.findViewById(R.id.a_updateMKH);
        TextInputEditText b_addMKH = view.findViewById(R.id.b_updateMKH);
        TextInputLayout a_addML = view.findViewById(R.id.a_updateML);
        TextInputEditText b_addML = view.findViewById(R.id.b_updateML);
        TextInputLayout a_addMP = view.findViewById(R.id.a_updateMP);
        TextInputEditText b_addMP = view.findViewById(R.id.b_updateMP);
        TextInputLayout a_addMDV = view.findViewById(R.id.a_updateMDV);
        TextInputEditText b_addMDV = view.findViewById(R.id.b_updateMDV);
        TextInputLayout a_addMNV = view.findViewById(R.id.a_updateMNV);
        TextInputEditText b_addMNV = view.findViewById(R.id.b_updateMNV);
        Button btnadd = view.findViewById(R.id.updateHD);

        b_addTGKT.setText(hoaDonModel.getMaDv());
        b_addTT.setText(String.valueOf(hoaDonModel.getTongTien()));
        b_addMKH.setText(hoaDonModel.getMaKH());
        b_addML.setText(hoaDonModel.getMaLoai());
        b_addMP.setText(hoaDonModel.getMaPhong());
        b_addMDV.setText(hoaDonModel.getThoiGianBD());
        b_addMNV.setText(hoaDonModel.getThoiGianKT());


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


                    if (trangThai.isEmpty()) {
                        a_addTGKT.setError("vui lòng nhập đầy đủ năm sinh");
                    } else {
                        a_addTGKT.setError(null);
                    }
                    if (maLoai.isEmpty()) {
                        a_addTT.setError("vui lòng nhập tài khoản");
                    } else {
                        a_addTT.setError(null);
                    }
                    if (maKH.isEmpty()) {
                        a_addMKH.setError("vui lòng nhập ma khách hàng");
                    } else {
                        a_addMKH.setError(null);
                    }
                    if (maL.isEmpty()) {
                        a_addML.setError("vui lòng nhập ma loại");
                    } else {
                        a_addML.setError(null);
                    }
                    if (maP.isEmpty()) {
                        a_addMP.setError("vui lòng nhập ma phòng");
                    } else {
                        a_addMP.setError(null);
                    }
                    if (madv.isEmpty()) {
                        a_addMDV.setError("vui lòng nhập ma dịch vụ");
                    } else {
                        a_addMDV.setError(null);
                    }
                    if (maNV.isEmpty()) {
                        a_addMNV.setError("vui lòng nhập ma nhân viên");
                    } else {
                        a_addMNV.setError(null);
                    }

                hoaDonModel.setThoiGianKT(trangThai);
                hoaDonModel.setTongTien(Integer.valueOf(maLoai));
                hoaDonModel.setMaKH(maKH);
                hoaDonModel.setMaLoai(maL);
                hoaDonModel.setMaPhong(maP);
                hoaDonModel.setMaDv(madv);
                hoaDonModel.setMaNv(maNV);

                hoaDonDAO = new HoaDonDAO(context);
                boolean check= hoaDonDAO.updateHoaDon(hoaDonModel);


                    if (check) {
                        loadData();
                        Toast.makeText(context, "thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    }

    private void loadData() {
        hoaDonList.clear();
        hoaDonList = hoaDonDAO.getHoaDonModel();
        notifyDataSetChanged();
    }


}
