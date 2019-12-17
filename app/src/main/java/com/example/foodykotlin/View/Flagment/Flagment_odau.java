package com.example.foodykotlin.View.Flagment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodykotlin.Controller.OdauControler;
import com.example.foodykotlin.Model.QuanAnModel;
import com.example.foodykotlin.R;

public class Flagment_odau extends Fragment {
    OdauControler odauControler;
    RecyclerView recyodau;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_flagment_odau, container, false);
        recyodau = view.findViewById(R.id.recyodau);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        odauControler = new OdauControler(getContext());
        odauControler.getDanhSachQuanAnControler(recyodau);
    }
}
