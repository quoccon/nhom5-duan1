package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.viewpager.widget.PagerAdapter;

import com.example.duan1.Model.slideModel;
import com.example.duan1.R;

import java.util.List;

public class slideAdapter extends PagerAdapter {

    private Context context;
    private List<slideModel> list;

    public slideAdapter(Context context, List<slideModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide,container,false);

        ImageView slide = view.findViewById(R.id.slide_show);

        slideModel slideModel= list.get(position);
        if (slideModel!= null){

        }
        return view;
    }

    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
