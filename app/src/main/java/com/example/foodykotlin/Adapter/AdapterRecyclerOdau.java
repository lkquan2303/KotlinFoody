package com.example.foodykotlin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodykotlin.Model.QuanAnModel;
import com.example.foodykotlin.R;

import java.util.List;

public class AdapterRecyclerOdau extends RecyclerView.Adapter<AdapterRecyclerOdau.ViewHolder> {
    List<QuanAnModel> quanAnModelList;
    int resource;
    public AdapterRecyclerOdau(List<QuanAnModel> quanAnModelList, int resource )
    {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvtenquananodau;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtenquananodau = itemView.findViewById(R.id.idtenquananodau);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerOdau.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerOdau.ViewHolder holder, int position) {
        QuanAnModel quanAnModel = quanAnModelList.get(position);
        holder.tvtenquananodau.setText(quanAnModel.getTenquanan());

    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }


}
