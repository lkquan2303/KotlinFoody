package com.example.foodykotlin.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.foodykotlin.R
import kotlinx.android.synthetic.main.layout_flashscreen.*

class FlashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_flashscreen)
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        txtVersion.setText("Version : " + packageInfo.versionName);
        val handler = Handler()
        handler.postDelayed(
            {
                val intent = Intent(this, DangNhapActivity ::class.java)
                startActivity(intent)
                finish()
            },1600
        )

    }
}
