package com.example.foodykotlin.Controller;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodykotlin.Adapter.AdapterRecyclerOdau;
import com.example.foodykotlin.Controller.Interfaces.OdauInterface;
import com.example.foodykotlin.Model.QuanAnModel;
import com.example.foodykotlin.R;

import java.util.ArrayList;
import java.util.List;

public class OdauControler {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecyclerOdau adapterRecyclerOdau;
    public OdauControler(Context context)
    {
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAnControler(RecyclerView recyclerViewOdau)
    {
        final List<QuanAnModel> quanAnModelList  = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewOdau.setLayoutManager(layoutManager);
        adapterRecyclerOdau = new AdapterRecyclerOdau(quanAnModelList, R.layout.custom_layout_recyleview_odau);
        recyclerViewOdau.setAdapter(adapterRecyclerOdau);
        OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                Log.d("kiemtra" , quanAnModel.getTenquanan() + "");
                quanAnModelList.add(quanAnModel);
                adapterRecyclerOdau.notifyDataSetChanged();

            }
        };
        quanAnModel.getDanhSachQuanan(odauInterface);
    }
}
