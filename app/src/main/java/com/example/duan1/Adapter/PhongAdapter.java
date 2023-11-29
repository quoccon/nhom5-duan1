package com.example.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Component.Fragment.HoaDonFragment;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.DAO.PhongDAO;
import com.example.duan1.Model.HoaDonModel;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.Model.PhongModel;
import com.example.duan1.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.ViewHolder> {
private Context context;
private ArrayList<PhongModel>list;

    public PhongAdapter(Context context, ArrayList<PhongModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phong,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhongModel phongModel = list.get(position);
        holder.txtLoai.setText(String.valueOf(list.get(position).getMaLoai()));
        holder.txtMaPhong.setText(String.valueOf(list.get(position).getMaPhong()));
        holder.txtGia.setText(String.valueOf(list.get(position).getGiaThue()));
        holder.txtTrangThai.setText(list.get(position).getTrangThai());
        if (phongModel.getTrangThai() != null){
            if(phongModel.getTrangThai().equals("1")){
                holder.txtTrangThai.setText("Đang cho thuê");
                holder.txtTrangThai.setTextColor(ContextCompat.getColor(context,R.color.red));
                holder.txtTrangThai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showTraPhong(phongModel);
                    }
                });
            }else {
                holder.txtTrangThai.setText("Còn trống");
                holder.txtTrangThai.setTextColor(ContextCompat.getColor(context,R.color.green));
                holder.txtTrangThai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showThuePhong(phongModel);
                    }
                });
            }
        }

        holder.imgDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Bạn có thực sự muốn xóa?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PhongDAO dao = new PhongDAO(context);
                        boolean check = dao.deletePhong(list.get(holder.getAdapterPosition()).getMaPhong());
                        if(check){
                            list.clear();
                            list = (ArrayList<PhongModel>) dao.getAllPhong();
                        }
                    }
                });
                builder.setNegativeButton("Hủy",null);
                builder.create().show();
            }
        });
    }

    private void showThuePhong(PhongModel phongModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Trả phòng");
        builder.setMessage("Bạn muốn thuê phòng này?");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PhongDAO dao = new PhongDAO(context);
                phongModel.setTrangThai(String.valueOf(1));
                String giobatdau = getCurrentDate();
                phongModel.setGioBatDau(giobatdau);
                dao.updatePhong(phongModel);

                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveKhachHang() {

    }

    public void showTraPhong(PhongModel phongModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Trả phòng");
        builder.setMessage("Bạn muốn trả phòng này?");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PhongDAO dao = new PhongDAO(context);
                phongModel.setTrangThai(String.valueOf(0));
                String giokt = getCurrentDate();
                phongModel.setGioKetThuc(giokt);
                dao.updatePhong(phongModel);
                String gioBatDau = phongModel.getGioBatDau();

                Bundle bundle = new Bundle();
                bundle.putParcelable("Phong", (Parcelable) phongModel);
                bundle.putString("gioKetThuc",giokt);

                HoaDonFragment hoaDonFragment = new HoaDonFragment();
                hoaDonFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,hoaDonFragment)
                                        .addToBackStack(null).commit();


                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
    TextView txtLoai,txtMaPhong, txtGia, txtTrangThai;
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

    public static String getCurrentDate(){
        Calendar calendar =Calendar.getInstance();
        Date currentDate = calendar.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        return dateFormat.format(currentDate);
    }
}
