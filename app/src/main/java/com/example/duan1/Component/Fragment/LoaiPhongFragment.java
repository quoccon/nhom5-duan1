package com.example.duan1.Component.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1.Adapter.LoaiPhongAdapter;
import com.example.duan1.DAO.LoaiPhongDAO;
import com.example.duan1.Model.LoaiPhongModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class LoaiPhongFragment extends Fragment {
    ListView lv_LP;
    ArrayList<LoaiPhongModel> list;
    static LoaiPhongDAO dao;
    LoaiPhongAdapter adapter;
    LoaiPhongModel item;
    FloatingActionButton fab;
    Dialog dialog;

    TextInputEditText ed_tenLP,ed_updateTLP;

    Button btnaddLP,btn_updateLP;


    public LoaiPhongFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_phong,container,false);
        lv_LP = v.findViewById(R.id.rcv_LP);
        fab = v.findViewById(R.id.fab_LP);
        dao = new LoaiPhongDAO(getActivity());
        capnhatlenlist();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddialog(getActivity());
            }
        });
        lv_LP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                updateLP(getActivity());
                return false;
            }
        });

        return v;
    }

    void capnhatlenlist(){
        list = (ArrayList<LoaiPhongModel>) dao.getAll();
        adapter = new LoaiPhongAdapter(getActivity(),this,list);
        lv_LP.setAdapter(adapter);
    }

    protected void adddialog(final Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_loaiphong);
        ed_tenLP = dialog.findViewById(R.id.b_addLP);
        btnaddLP = dialog.findViewById(R.id.addloaiphong);

        btnaddLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiPhongModel();
                item.setTenLoai(ed_tenLP.getText().toString());
                if (validate() > 0){
                    if (dao.insertLP(item) > 0){
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    capnhatlenlist();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }


    //update đang lỗi

    protected void updateLP(final Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.update_loaiphong);
        ed_updateTLP = dialog.findViewById(R.id.b_updateTLP);
        btn_updateLP = dialog.findViewById(R.id.updateLP);

        ed_updateTLP.setText(item.getTenLoai());
        btn_updateLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTenLoai(ed_updateTLP.getText().toString());

                if (dao.updateLP(item) > 0){
                    Toast.makeText(context, "cập nhật thành công", Toast.LENGTH_SHORT).show();
                    capnhatlenlist();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "cập nhật thất bại ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    public void DeleteLP(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capnhatlenlist();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã Xóa", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    public int validate(){
        int check = 1;
        if (ed_tenLP.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Bạn Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
       return check;
    }
}