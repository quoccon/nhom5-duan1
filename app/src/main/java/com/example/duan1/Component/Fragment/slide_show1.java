package com.example.duan1.Component.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        slideModels.add(new SlideModel(R.drawable.image, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.delete, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.baseline_search_24, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.iconhd, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.iconlogout, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        return view;
    }

}