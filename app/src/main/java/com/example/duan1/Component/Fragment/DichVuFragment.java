package com.example.duan1.Component.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1.Adapter.DichVuAdapter;
import com.example.duan1.Adapter.LoaiPhongAdapter;
import com.example.duan1.DAO.DichVuDAO;
import com.example.duan1.DAO.LoaiPhongDAO;
import com.example.duan1.Model.DichVuModel;
import com.example.duan1.Model.LoaiPhongModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class DichVuFragment extends Fragment {
    ListView lv_dichvu;
    ArrayList<DichVuModel> list;
    static DichVuDAO dao;
    DichVuAdapter adapter;
    DichVuModel item;
    FloatingActionButton fab;
    Dialog dialog;

    TextInputEditText ed_tenDV,ed_giatien,ed_mota,ed_updateTDV,ed_update_giatien,ed_update_mota;
    Button btn_addichvu,btn_updatedv;


    public DichVuFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dich_vu,container,false);

        lv_dichvu = v.findViewById(R.id.rcv_DV);
        fab = v.findViewById(R.id.fab_DV);
        TextInputEditText ed_search = v.findViewById(R.id.ed_search);

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchKeyword = s.toString().trim();
                searchDichVu(searchKeyword);
            }
        });

        dao = new DichVuDAO(getActivity());
        capnhatlenlist();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddialogDV(getActivity());
            }
        });
        lv_dichvu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                updateDV(getActivity());

                return false;
            }
        });


        return v;
    }

    public void capnhatlenlist(){
        list = (ArrayList<DichVuModel>) dao.getAll();
        adapter = new DichVuAdapter(getActivity(),this,list);
        lv_dichvu.setAdapter(adapter);
    }
    private void searchDichVu(String keyword) {
        ArrayList<DichVuModel> searchResults = new ArrayList<>();
        for (DichVuModel dichVu : list) {
            if (dichVu.getTenDV().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(dichVu);
            }
        }

        adapter = new DichVuAdapter(getActivity(), this, searchResults);
        lv_dichvu.setAdapter(adapter);
    }

    protected void adddialogDV(final Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_dichvu);
        ed_tenDV = dialog.findViewById(R.id.b_addTDV);
        ed_giatien = dialog.findViewById(R.id.b_addgia);
        ed_mota = dialog.findViewById(R.id.b_addmota);
        btn_addichvu = dialog.findViewById(R.id.addichvu);

        btn_addichvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new DichVuModel();
                item.setTenDV(ed_tenDV.getText().toString());
                item.setGiatien(Integer.parseInt(ed_giatien.getText().toString()));
                item.setMotaDV(ed_mota.getText().toString());
                if (validate() > 0){
                    if (dao.insertDV(item) > 0){
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
    public void DeleteDV(final String id){
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
    protected void updateDV(final Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.update_dichvu);
        ed_updateTDV = dialog.findViewById(R.id.b_updateTDV);
        ed_update_giatien = dialog.findViewById(R.id.b_updategia);
        ed_update_mota = dialog.findViewById(R.id.b_updatemota);
        btn_updatedv = dialog.findViewById(R.id.updatedv);

        ed_updateTDV.setText(item.getTenDV());
        ed_update_giatien.setText(String.valueOf(item.getGiatien()));
        ed_update_mota.setText(item.getMotaDV());

        btn_updatedv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTenDV(ed_updateTDV.getText().toString());
                item.setGiatien(Integer.parseInt(ed_update_giatien.getText().toString()));
                item.setMotaDV(ed_update_mota.getText().toString());
                if (dao.updateDV(item) > 0){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    capnhatlenlist();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if (ed_tenDV.getText().toString().length() == 0 || ed_giatien.getText().toString().length() == 0 || ed_mota.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Bạn Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}