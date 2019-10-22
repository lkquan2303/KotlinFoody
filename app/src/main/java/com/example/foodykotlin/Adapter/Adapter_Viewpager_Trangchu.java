package com.example.foodykotlin.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.foodykotlin.View.Flagment.Flagment_angi;
import com.example.foodykotlin.View.Flagment.Flagment_odau;

public class Adapter_Viewpager_Trangchu extends FragmentPagerAdapter {
    Flagment_angi flagment_angi;
    Flagment_odau flagment_odau;

    public Adapter_Viewpager_Trangchu(FragmentManager fm) {
        super(fm);
         flagment_angi = new Flagment_angi();
         flagment_odau = new Flagment_odau();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return flagment_odau;
            case 1:
                return flagment_angi;
                default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
