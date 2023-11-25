package com.example.duan1.Component.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.duan1.DAO.AdminDAO;
import com.example.duan1.MainActivity;
import com.example.duan1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextInputLayout a_tenDN, a_MK;
    TextInputEditText b_tenDN, b_MK;
    CheckBox chkSave;

    AdminDAO adminDAO = new AdminDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        a_tenDN = findViewById(R.id.a_tenDN);
        a_MK = findViewById(R.id.a_MK);
        b_tenDN = findViewById(R.id.b_tenDN);
        b_MK = findViewById(R.id.b_MK);
        chkSave = findViewById(R.id.chkSave);

        SharedPreferences pref = getSharedPreferences("User_File", MODE_PRIVATE);
        b_tenDN.setText(pref.getString("Username", ""));
        b_MK.setText(pref.getString("Password", ""));
        chkSave.setChecked(pref.getBoolean("Remember", false));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    public void login() {
        String user = b_tenDN.getText().toString();
        String pass = b_MK.getText().toString();
        if (user.isEmpty() || pass.isEmpty()) {
            if (user.equals("")) {
                a_tenDN.setError("Không được để trông tên đăng nhập");
            } else {
                a_tenDN.setError(null);
            }

            if (pass.equals("")) {
                a_MK.setError("Không được để trông mật khẩu");
            } else {
                a_MK.setError(null);
            }
        } else {
            if (adminDAO.checkLogin(user, pass)) {
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(user, pass, chkSave.isChecked());
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("MaAM", user);
                startActivity(i);

            } else {
                a_MK.setError("Tên đăng nhập hoặc mật khẩu không đúng");
                a_MK.setError("Tên đăng nhập hoặc mật khẩu không đúng");
            }
        }
    }
    private void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("User_File", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            //xóa trắng dữ liệu trước đó
            edit.clear();
        } else {
            //lưu dữ liệu
            edit.putString("Username", u);
            edit.putString("Password", p);
            edit.putBoolean("Remember", status);
        }
        //lưu lại toàn bộ
        edit.commit();
    }
}