package com.example.duan1.Component.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1.R;

import java.util.ArrayList;


public class slide_show1 extends Fragment {

ImageSlider imageSlider;
    public slide_show1() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_slide_show1, container, false);
        imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.anhbanner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.anhbanner3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.anhbanner4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.anhbanner5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.anhbanner6, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        LinearLayout layout = view.findViewById(R.id.layout_id);
        ImageView anh1 = view.findViewById(R.id.anh1);
        ImageView anh2 = view.findViewById(R.id.imageView);
        ImageView anh3 = view.findViewById(R.id.anh5);
        ImageView anh4 = view.findViewById(R.id.image3);
        ImageView anh5 = view.findViewById(R.id.anh2);
        ImageView anh6 = view.findViewById(R.id.image2);

        anh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVienFragment nhanVienFragment = new NhanVienFragment();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container,nhanVienFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        anh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhachHangFragment khachHangFragment = new KhachHangFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container,khachHangFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        anh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DichVuFragment dichVuFragment = new DichVuFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container,dichVuFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        anh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhongFragment phongFragment = new PhongFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container,phongFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        anh5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonFragment hoaDonFragment = new HoaDonFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container,hoaDonFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        anh6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiPhongFragment phongFragment = new LoaiPhongFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container,phongFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}