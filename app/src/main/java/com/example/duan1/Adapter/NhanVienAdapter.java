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
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.NhanVienDAO;
import com.example.duan1.Model.NhanVienModel;
import com.example.duan1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NhanVienModel> list;

    NhanVienDAO dao;

    public NhanVienAdapter(Context context, ArrayList<NhanVienModel> list) {
        this.context = context;
        this.list = list;
        dao = new NhanVienDAO(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhanvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienAdapter.ViewHolder holder, int position) {
        holder.txtmaNV.setText(String.valueOf(list.get(position).getMaNv()));
        holder.txttenNV.setText(list.get(position).getTenNv());
        holder.txtnamsinhNV.setText(list.get(position).getNamsinh());
        holder.txttaikhoanNV.setText(list.get(position).getUsername());
        holder.txtmatKhauNV.setText(list.get(position).getPassword());
        NhanVienModel nv = list.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                diglogupdateNhanVien(nv);
                return false;
            }
        });

        holder.deleteNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Bạn thật sự muốn xóa nhân viên này chứ");
                builder.setPositiveButton("xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NhanVienDAO dao = new NhanVienDAO(context);
                        boolean check = dao.delete(list.get(holder.getAdapterPosition()).getMaNv());
                        if (check) {
                            list.clear();
                            list = dao.getNhanVienModel();
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa nhân viên không thành công", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Hủy", null);
                builder.create().show();

            }
        });


    }

    private void loadData() {
        list.clear();
        list = dao.getNhanVienModel();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmaNV, txttenNV, txtnamsinhNV, txttaikhoanNV, txtmatKhauNV;

        ImageView deleteNV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaNV = itemView.findViewById(R.id.MaNV);
            txttenNV = itemView.findViewById(R.id.TenNV);
            txtnamsinhNV = itemView.findViewById(R.id.NamSinh_NV);
            txttaikhoanNV = itemView.findViewById(R.id.Taikhoan_NV);
            txtmatKhauNV = itemView.findViewById(R.id.Matkhau_NV);
            deleteNV = itemView.findViewById(R.id.deleteNV);

        }
    }


    private void diglogupdateNhanVien(NhanVienModel nhanVienModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_nhanvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        TextInputLayout a_updateNV = view.findViewById(R.id.a_updateNV);
        TextInputEditText b_updateNV = view.findViewById(R.id.b_updateNV);
        TextInputLayout a_updateNSNNV = view.findViewById(R.id.a_updateNSNV);
        TextInputEditText b_updateNSNV = view.findViewById(R.id.b_updateNSNV);
        TextInputLayout a_updateTK = view.findViewById(R.id.a_updateTK);
        TextInputEditText b_updateTK = view.findViewById(R.id.b_updateTK);
        TextInputLayout a_updateMK = view.findViewById(R.id.a_updateMK);
        TextInputEditText b_updateMK = view.findViewById(R.id.b_updateMK);
        Button btnupdateNV = view.findViewById(R.id.updatenhanvien);

        b_updateNV.setText(nhanVienModel.getTenNv());
        b_updateNSNV.setText(nhanVienModel.getNamsinh());
        b_updateTK.setText(nhanVienModel.getUsername());
        b_updateMK.setText(nhanVienModel.getPassword());

        b_updateNV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateNV.setError("vui lòng nhập tên nhân viên");
                } else {
                    a_updateNV.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_updateNSNV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateNSNNV.setError("vui lòng nhập năm sinh");
                } else {
                    a_updateNSNNV.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_updateTK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateTK.setError("vui lòng nhập tài khoản");
                } else {
                    a_updateTK.setError(null);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_updateMK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateMK.setError("vui lòng nhập mật khẩu");
                } else {
                    a_updateMK.setError(null);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnupdateNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten = b_updateNV.getText().toString();
                String namsinh = b_updateNSNV.getText().toString();
                String taikhoan = b_updateTK.getText().toString();
                String MatKhau = b_updateMK.getText().toString();

                boolean check = dao.update(nhanVienModel.getMaNv(),ten, namsinh, taikhoan, MatKhau);

                if (ten.isEmpty() || namsinh.isEmpty() || taikhoan.isEmpty() || MatKhau.isEmpty()) {
                    if (ten.equals("")) {
                        a_updateNV.setError("vui lòng nhập đầy đủ tên nhân viên ");
                    } else {
                        a_updateNV.setError(null);
                    }
                    if (namsinh.equals("")) {
                        a_updateNSNNV.setError("vui lòng nhập đầy đủ năm sinh");
                    } else {
                        a_updateNSNNV.setError(null);
                    }
                    if (taikhoan.equals("")) {
                        a_updateTK.setError("vui lòng nhập tài khoản");
                    } else {
                        a_updateTK.setError(null);
                    }
                    if (MatKhau.equals("")) {
                        a_updateMK.setError("vui lòng nhập mật khẩu");
                    } else {
                        a_updateMK.setError(null);
                    }
                } else {
                    if (check) {
                        loadData();
                        Toast.makeText(context, "update nhân viên thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "update nhân viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


}
