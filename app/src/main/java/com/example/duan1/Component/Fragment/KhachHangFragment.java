package com.example.duan1.Component.Fragment;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1.Adapter.DichVuAdapter;
import com.example.duan1.Adapter.KhachHangAdapter;
import com.example.duan1.DAO.KhachHangDAO;
import com.example.duan1.Model.DichVuModel;
import com.example.duan1.Model.KhachHangModel;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class KhachHangFragment extends Fragment {

    FloatingActionButton add_KH;
    ListView rcv_KH;
    KhachHangDAO dao;
    ArrayList<KhachHangModel> list;
    KhachHangAdapter adapter;
    KhachHangModel item;

    TextInputEditText b_addKH,b_addNSKH,b_addCCCD;
    public KhachHangFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        rcv_KH = view.findViewById(R.id.rcv_KH);
        add_KH = view.findViewById(R.id.add_KH);
        dao = new KhachHangDAO(getContext());
        TextInputEditText ed_search = view.findViewById(R.id.ed_search);
        loadData();

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
                searchKhachHang(searchKeyword);
            }
        });

        add_KH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogaddKH();

            }
        });
        rcv_KH.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                updateKH(getActivity());
                return false;
            }
        });
        return view;
    }


    private void loadData() {
        list = (ArrayList<KhachHangModel>) dao.getAll();
        adapter = new KhachHangAdapter(getActivity(),this,list);
        rcv_KH.setAdapter(adapter);
    }

    private void searchKhachHang(String keyword) {
        ArrayList<KhachHangModel> searchResults = new ArrayList<>();
        for (KhachHangModel khachHangModel : list) {
            if (khachHangModel.getTenKhachHang().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(khachHangModel);
            }
        }

        adapter = new KhachHangAdapter(getActivity(), this, searchResults);
        rcv_KH.setAdapter(adapter);
    }

    private void dialogaddKH() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_khachhang, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

         b_addKH = view.findViewById(R.id.b_addKH);
         b_addNSKH = view.findViewById(R.id.b_addNSKH);
         b_addCCCD = view.findViewById(R.id.b_addCCCD);

        Button btnaddKH = view.findViewById(R.id.addkhachhang);

        btnaddKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new KhachHangModel();
                item.setTenKhachHang(b_addKH.getText().toString());
                item.setNamSinh(b_addNSKH.getText().toString());
                item.setCCCD(b_addCCCD.getText().toString());
                if (validate() >0){
                    if (dao.insertKH(item) >0){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    loadData();
                    dialog.dismiss();
                }

            }
        });


    }
    public void DeleteKH(final String id){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                loadData();
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
        android.app.AlertDialog alertDialog = builder.create();
        builder.show();
    }

    protected void updateKH(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.update_khachhang, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText update_TKH = view.findViewById(R.id.b_updateKH);
        TextInputEditText update_NKH = view.findViewById(R.id.b_updateNSKH);
        TextInputEditText update_CCCD = view.findViewById(R.id.b_updateCCCD);

        Button btnupdateKhachHang = view.findViewById(R.id.updatekhachhang);

        update_TKH.setText(item.getTenKhachHang());
        update_NKH.setText(item.getNamSinh());
        update_CCCD.setText(item.getCCCD());

        btnupdateKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setTenKhachHang(update_TKH.getText().toString());
                item.setNamSinh(update_NKH.getText().toString());
                item.setCCCD(update_CCCD.getText().toString());
                if (dao.updateKH(item) > 0){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public int validate(){
        int check = 1;
        if (b_addKH.getText().toString().length() == 0 || b_addNSKH.getText().toString().length() == 0 || b_addCCCD.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Bạn Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}