package com.example.foodykotlin.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.viewpager.widget.ViewPager
import com.example.foodykotlin.Adapter.Adapter_Viewpager_Trangchu
import com.example.foodykotlin.R
import kotlinx.android.synthetic.main.activity_trang_chu.*

class TrangChuActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when(p1)
        {
            R.id.rdangi -> viewpager_trangchu.setCurrentItem(1)
            R.id.rddiadiem -> viewpager_trangchu.setCurrentItem(0)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> rddiadiem.setChecked(true)
            1 -> rdangi.setChecked(true)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trang_chu)
        val adapterViewPagerTrangChu = Adapter_Viewpager_Trangchu(supportFragmentManager)
        viewpager_trangchu.setAdapter(adapterViewPagerTrangChu)
        viewpager_trangchu.addOnPageChangeListener(this)
        rdgroup_odau_angi.setOnCheckedChangeListener(this)


    }
}
