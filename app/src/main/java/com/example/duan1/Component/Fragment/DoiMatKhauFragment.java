package com.example.duan1.Component.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan1.Component.User.LoginActivity;
import com.example.duan1.DAO.AdminDAO;
import com.example.duan1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class DoiMatKhauFragment extends Fragment {

    TextInputLayout a_MKHT, a_MKM, a_NLMK;
    TextInputEditText b_MKHT, b_MKM, b_NLMK;
    Button btn_save;


    public DoiMatKhauFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);

        a_MKHT = view.findViewById(R.id.a_MKHT);
        a_MKM = view.findViewById(R.id.a_MKM);
        a_NLMK = view.findViewById(R.id.a_NLMK);
        b_MKHT = view.findViewById(R.id.b_MKHT);
        b_MKM = view.findViewById(R.id.b_MKM);
        b_NLMK = view.findViewById(R.id.b_NLMK);
        btn_save = view.findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoiMK();
            }
        });
        return view;
    }

    public void DoiMK(){
        String oldPass = b_MKHT.getText().toString();
        String newPass = b_MKM.getText().toString();
        String repass = b_NLMK.getText().toString();
        if(newPass.equals(repass)){
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("User_File",getContext().MODE_PRIVATE);
            String matt = sharedPreferences.getString("Username","");
            String mk = sharedPreferences.getString("Password","");
            //cập nhật
            AdminDAO adminDAO =  new AdminDAO(getContext());
            boolean check = adminDAO.updateMK(matt,oldPass,newPass);
            if(oldPass.equals(mk)){
                if(check){
                    Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
//                    Log.e("TAG", "DoiMK: 32" );
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }else{
                a_MKHT.setError("Mật khẩu hiện tại không đúng");
            }
        }else{
            a_MKM.setError("Mật Khẩu Không Khớp");
            a_NLMK.setError("Mật Khẩu Không Khớp");
        }
    }
}