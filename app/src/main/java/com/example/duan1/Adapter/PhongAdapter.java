package com.example.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.LoaiPhongDAO;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.NhanVienModel;
import com.example.duan1.Model.PhongModel;
import com.example.duan1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PhongModel> list;

    LoaiPhongDAO loaiPhongDAO;

    PhongDAO phongDAO;

    public PhongAdapter(Context context, ArrayList<PhongModel> list) {
        this.context = context;
        this.list = list;
        loaiPhongDAO = new LoaiPhongDAO(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhongModel phongModel = list.get(position);
        holder.txtLoai.setText(getLoaiPhong(list.get(position).getMaLoai()));
        holder.txtMaPhong.setText(String.valueOf(list.get(position).getMaPhong()));
        holder.txtGia.setText(String.valueOf(list.get(position).getGiaThue()));
        holder.txtTrangThai.setText(list.get(position).getTrangThai());

        PhongModel P = list.get(position);


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                diglogupdateNhanVien(P);
                return false;
            }
        });

        holder.imgDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa Sách");
                builder.setMessage("Bạn có chắc muốn xóa sách này chứ ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int check = phongDAO.delete(list.get(holder.getAdapterPosition()).getMaPhong());
                        switch (check){
                            case 1:
                                loadData();
                                Toast.makeText(context, "Xóa thành công sách", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa không thành công sách", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được sách này vì đang còn tồn tại trong phiếu mượn", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Hủy",null);
                builder.create().show();
            }
        });

    }

    private String getLoaiPhong(String maLoai) {


        return loaiPhongDAO.getTen(maLoai);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLoai, txtMaPhong, txtGia, txtTrangThai;
        ImageView imgDele;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLoai = itemView.findViewById(R.id.txtMaLoai);
            txtMaPhong = itemView.findViewById(R.id.txtMaP);
            txtGia = itemView.findViewById(R.id.txtGia);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            imgDele = itemView.findViewById(R.id.imgDele);
        }
    }

    private void loadData() {
        list.clear();
        list = phongDAO.getphongModel();
        notifyDataSetChanged();
    }

    private void diglogupdateNhanVien(PhongModel phongModel) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_phong, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        TextInputLayout a_updateGT = view.findViewById(R.id.a_updateGT);
        TextInputEditText b_updateGT = view.findViewById(R.id.b_updateGT);
        TextInputLayout a_updateNBD = view.findViewById(R.id.a_updateNBD);
        TextInputEditText b_updateNBD = view.findViewById(R.id.b_updateNBD);
        TextInputLayout a_updateNKT = view.findViewById(R.id.a_updateNKT);
        TextInputEditText b_updateNKT = view.findViewById(R.id.b_updateNKT);
        Button btnupdateNV = view.findViewById(R.id.updatephong);

        b_updateNKT.setText(Integer.valueOf(phongModel.getGiaThue()));
        b_updateNBD.setText(phongModel.getTrangThai());
        b_updateGT.setText(phongModel.getMaPhong());


        b_updateGT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateGT.setError("vui lòng nhập tên nhân viên");
                } else {
                    a_updateGT.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_updateNBD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateNBD.setError("vui lòng nhập năm sinh");
                } else {
                    a_updateNBD.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_updateNKT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateNKT.setError("vui lòng nhập tài khoản");
                } else {
                    a_updateNKT.setError(null);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnupdateNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String giaThue = b_updateGT.getText().toString();
                String trangThai = b_updateNBD.getText().toString();
                String maLoai = b_updateNKT.getText().toString();
                PhongModel phongModel = new PhongModel();
                phongModel.setGiaThue(Integer.valueOf(giaThue));
                phongModel.setTrangThai(trangThai);
                phongModel.setMaLoai(maLoai);
                boolean check = phongDAO.update(phongModel);

                if (giaThue.isEmpty() || trangThai.isEmpty() || maLoai.isEmpty()) {
                    if (giaThue.equals("")) {
                        a_updateGT.setError("vui lòng nhập đầy đủ tên nhân viên ");
                    } else {
                        a_updateGT.setError(null);
                    }
                    if (trangThai.equals("")) {
                        a_updateNBD.setError("vui lòng nhập đầy đủ năm sinh");
                    } else {
                        a_updateNBD.setError(null);
                    }
                    if (maLoai.equals("")) {
                        a_updateNKT.setError("vui lòng nhập tài khoản");
                    } else {
                        a_updateNKT.setError(null);
                    }
                } else {
                    if (check) {
                        loadData();
                        Toast.makeText(context, "thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


}
