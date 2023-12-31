package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1.Component.Fragment.DichVuFragment;
import com.example.duan1.Component.Fragment.DoanhThuFragment;
import com.example.duan1.Component.Fragment.DoiMatKhauFragment;
import com.example.duan1.Component.Fragment.HoaDonFragment;
import com.example.duan1.Component.Fragment.KhachHangFragment;
import com.example.duan1.Component.Fragment.LoaiPhongFragment;
import com.example.duan1.Component.Fragment.NhanVienFragment;
import com.example.duan1.Component.Fragment.PhongFragment;
import com.example.duan1.Component.Fragment.slide_show1;
import com.example.duan1.Component.User.RegisterActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    Toolbar toolbar;
    Context context = this;
    ImageSlider imageSlider;
    NavigationView navigationView;

    View header;


    CardView slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        slide = findViewById(R.id.slide);
//        imageSlider = findViewById(R.id.imageSlider);
//        ArrayList<SlideModel> slideModels = new ArrayList<>();
//
//        slideModels.add(new SlideModel(R.drawable.image, ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.delete, ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.baseline_search_24, ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.iconhd, ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.iconlogout, ScaleTypes.FIT));
//
//        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.fragment_container, new slide_show1());
////        header = navigationView.getHeaderView(0);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("slide");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        openFragment(new slide_show1());
        navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.slide) {
            openFragment(new slide_show1());
        } else if (itemId == R.id.menuQLKH) {
            openFragment(new KhachHangFragment());
        } else if (itemId == R.id.menuQLNV) {
            openFragment(new NhanVienFragment());
        } else if (itemId == R.id.menuQLLP) {
            openFragment(new LoaiPhongFragment());
        } else if (itemId == R.id.menuQLP) {
            openFragment(new PhongFragment());
        } else if (itemId == R.id.menuQLDV) {
            openFragment(new DichVuFragment());
        } else if (itemId == R.id.menuQLHD) {
            openFragment(new HoaDonFragment());
        } else if (itemId == R.id.menuDT) {
            openFragment(new DoanhThuFragment());
        } else if (itemId == R.id.menuDoiMK) {
            openFragment(new DoiMatKhauFragment());
        } else if (itemId == R.id.menuDX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Đăng Xuất");
            builder.setMessage("Bạn chắc chắn muốn đăng xuất chứ!");
            builder.setPositiveButton("Đăng Xuất", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("hủy", null);
            builder.create().show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}