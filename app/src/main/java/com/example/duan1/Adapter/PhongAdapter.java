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
                diglogupdatePhong(P);
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
                        phongDAO = new PhongDAO(context);
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

    private void diglogupdatePhong(PhongModel phongModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.update_phong, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextInputLayout a_updateGT = view.findViewById(R.id.a_updateGT);
        TextInputEditText b_updateGT = view.findViewById(R.id.b_updateGT);
        TextInputLayout a_updateNBD = view.findViewById(R.id.a_updateNBD);
        TextInputEditText b_updateNBD = view.findViewById(R.id.b_updateNBD);
        TextInputLayout a_updateNKT = view.findViewById(R.id.a_updateNKT);
        TextInputEditText b_updateNKT = view.findViewById(R.id.b_updateNKT);
        Button btnupdateNV = view.findViewById(R.id.updatephong);

        b_updateGT.setText(String.valueOf(phongModel.getGiaThue()));
        b_updateNBD.setText(phongModel.getTrangThai());
        b_updateNKT.setText(phongModel.getMaLoai());

        btnupdateNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String giaThue = b_updateGT.getText().toString();
                String trangThai = b_updateNBD.getText().toString();
                String maLoai = b_updateNKT.getText().toString();

                if (giaThue.isEmpty()) {
                    a_updateGT.setError("Vui lòng nhập giá thuê");
                    return;
                } else {
                    a_updateGT.setError(null);
                }

                if (trangThai.isEmpty()) {
                    a_updateNBD.setError("Vui lòng nhập trạng thái");
                    return;
                } else {
                    a_updateNBD.setError(null);
                }

                if (maLoai.isEmpty()) {
                    a_updateNKT.setError("Vui lòng nhập mã loại");
                    return;
                } else {
                    a_updateNKT.setError(null);
                }

                phongModel.setGiaThue(Integer.parseInt(giaThue));
                phongModel.setTrangThai(trangThai);
                phongModel.setMaLoai(maLoai);

                phongDAO = new PhongDAO(context);
                boolean check = phongDAO.update(phongModel);

                if (check) {
                    loadData();
                    Toast.makeText(context, "Cập nhật phòng thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Cập nhật phòng thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
