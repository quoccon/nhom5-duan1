package com.example.duan1.Component.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duan1.Adapter.HoaDonAdapter;
import com.example.duan1.DAO.HoaDonDAO;
import com.example.duan1.Model.HoaDonModel;
import com.example.duan1.R;

import java.util.ArrayList;


public class DoanhThuFragment extends Fragment {
    private TextView tvTotalRevenue;
    private ListView lvViewRevenue;
    private HoaDonDAO hoaDonDAO;
    private HoaDonAdapter adapter;


    public DoanhThuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        tvTotalRevenue = view.findViewById(R.id.tvTotalRevenue);
        lvViewRevenue = view.findViewById(R.id.listViewRevenue);

        hoaDonDAO = new HoaDonDAO(getContext());

        ArrayList<HoaDonModel> hoaDonList = hoaDonDAO.getHoaDonModel();

        double totalRevenue = calculateTotalRevenue(hoaDonList);

        adapter = new HoaDonAdapter(getContext(), hoaDonList);


        tvTotalRevenue.setText("Total Revenue: $" + totalRevenue);

        return view;
    }

    private double calculateTotalRevenue(ArrayList<HoaDonModel> hoaDonList) {
        double totalRevenue = 0;
        for (HoaDonModel hoaDonModel : hoaDonList) {
            totalRevenue += hoaDonModel.getTongTien();
        }
        return totalRevenue;
    }
}