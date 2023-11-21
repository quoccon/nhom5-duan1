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

import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<KhachHangModel> list;

    KhachHangDAO dao;

    public KhachHangAdapter(Context context, ArrayList<KhachHangModel> list) {
        this.context = context;
        this.list = list;
        dao = new KhachHangDAO(context);
    }

    @NonNull
    @Override
    public KhachHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangAdapter.ViewHolder holder, int position) {
        holder.txtMaKH.setText(String.valueOf(list.get(position).getMaKH()));
        holder.txtTenKH.setText(list.get(position).getTenKhachHang());
        holder.txtNamSinh_KH.setText(String.valueOf(list.get(position).getNamSinh()));
        holder.txtCCCD_KH.setText(String.valueOf(list.get(position).getCCCD()));
        KhachHangModel KH = list.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogUpdateTV(KH);
                return false;
            }
        });
        holder.deleteKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Bạn chắc chắn muốn xóa thành viên này chứ");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int check = dao.delete(list.get(holder.getAdapterPosition()).getMaKH());
                        switch (check) {
                            case 0:
                                loadData();
                                Toast.makeText(context, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(context, "Xóa nhân viên thất bại", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }


                    }
                });
                builder.setNegativeButton("Hủy", null);
                builder.create().show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaKH, txtTenKH, txtNamSinh_KH, txtSDT_KH, txtCCCD_KH;
        ImageView deleteKH;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaKH = itemView.findViewById(R.id.MaKH);
            txtTenKH = itemView.findViewById(R.id.TenKH);
            txtNamSinh_KH = itemView.findViewById(R.id.NamSinh_KH);
            txtSDT_KH = itemView.findViewById(R.id.SDT_KH);
            txtCCCD_KH = itemView.findViewById(R.id.CCCD_KH);
            deleteKH = itemView.findViewById(R.id.deleteKH);

        }
    }

    private void loadData() {
        list.clear();
        list = dao.getKhachHangModel();
        notifyDataSetChanged();
    }

    private void dialogUpdateTV(KhachHangModel tv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_khachhang, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText b_updateKH = view.findViewById(R.id.b_updateKH);
        TextInputEditText b_updateNSKH = view.findViewById(R.id.b_updateNSKH);
        TextInputEditText b_updateCCCD = view.findViewById(R.id.b_updateCCCD);
        TextInputLayout a_updateKH = view.findViewById(R.id.a_updateKH);
        TextInputLayout a_updateNSKH = view.findViewById(R.id.a_updateNSKH);
        TextInputLayout a_updateCCCD = view.findViewById(R.id.a_updateCCCD);
        Button btnupdateKH = view.findViewById(R.id.updatekhachhang);

        b_updateKH.setText(tv.getTenKhachHang());
        b_updateNSKH.setText(String.valueOf(tv.getNamSinh()));
        b_updateCCCD.setText(tv.getCCCD());

        b_updateKH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateKH.setError("vui lòng nhập tên khách hàng ");
                } else {
                    a_updateKH.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_updateNSKH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateNSKH.setError(" vui lòng nhập năm sinh ");
                } else {
                    a_updateNSKH.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b_updateCCCD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    a_updateCCCD.setError("vui lòng nhập căn cước");
                }else {
                    a_updateCCCD.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnupdateKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkhachhang = b_updateKH.getText().toString();
                String namsinh = b_updateNSKH.getText().toString();
                String CCCD = b_updateCCCD.getText().toString();

                if (tenkhachhang.isEmpty() || namsinh.isEmpty() || CCCD.isEmpty()) {
                    if (tenkhachhang.equals("")) {
                        a_updateKH.setError("vui lòng nhập tên khách hàng ");
                    } else {
                        a_updateKH.setError(null);
                    }
                    if (namsinh.equals("")) {
                        a_updateNSKH.setError("vui lòng nhập năm sinh");
                    } else {
                        a_updateNSKH.setError(null);
                    }
                    if (CCCD.equals("")) {
                        a_updateCCCD.setError("vui lòng nhập căn cước");
                    } else {
                        a_updateCCCD.setError(null);
                    }

                } else {
                    int nams = Integer.parseInt(namsinh);
                    boolean check = dao.update(tv.getMaKH(),tenkhachhang,nams,CCCD);
                    if (check) {
                        loadData();
                        Toast.makeText(context, "update thành công khách hàng", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "update khách hàng thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
