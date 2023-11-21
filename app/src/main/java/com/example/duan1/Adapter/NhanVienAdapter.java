package com.example.duan1.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.NhanVienDAO;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.Model.NhanVienModel;
import com.example.duan1.R;

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
        holder.txtmaNV.setText(String.valueOf(list.get(position).getMaNV()));
        holder.txttenNV.setText(list.get(position).getTenNV());
        holder.txtnamsinhNV.setText(list.get(position).getNamSinh());
        holder.txttaikhoanNV.setText(list.get(position).getTaiKhoan());
        holder.txtmatKhauNV.setText(list.get(position).getMatKhau());
        NhanVienModel tv = list.get(position);
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

    @SuppressLint("MissingInflatedId")
    private void diglogupdateNhanVien(NhanVienModel nhanVienModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_nhanvien, null);
    }


    private void Loadata() {
        list.clear();
        list = dao.getNhanVienModel();
        notifyDataSetChanged();
    }


}
