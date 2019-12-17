package com.example.foodykotlin.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodykotlin.Controller.Interfaces.OdauInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
  boolean giaohang;
  String giodongcua, giomocua, tenquanan, videogioithieu,chinhanhquanans;
  List<String> tienich;
  DatabaseReference noderoot;
  long luotthich;



    public String getChinhanhquanans() {
        return chinhanhquanans;
    }

    public void setChinhanhquanans(String chinhanhquanans) {
        this.chinhanhquanans = chinhanhquanans;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public QuanAnModel()
    {
        noderoot = FirebaseDatabase.getInstance().getReference();
    }
    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }
    public void getDanhSachQuanan(final OdauInterface odauInterface)
    {
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshotQuanans = dataSnapshot.child("quanans");
                for(DataSnapshot valueQuanans : dataSnapshotQuanans.getChildren())
                  {
                   QuanAnModel quanAnModel  = valueQuanans.getValue(QuanAnModel.class);
                   odauInterface.getDanhSachQuanAnModel(quanAnModel);
                  }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        noderoot.addListenerForSingleValueEvent(valueEventListener);

    }
}
