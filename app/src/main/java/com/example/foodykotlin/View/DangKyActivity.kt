package com.example.foodykotlin.View

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodykotlin.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dang_ky.*

class DangKyActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        var progressDialog : ProgressDialog = ProgressDialog(this)
        progressDialog.setMessage("Đang xử lý")
        val email = edemaildk.getText().toString()
        val pass = edpassdk.getText().toString()
        val repass = edrepassdk.getText().toString()
        //     boolean checkdkmail = checkmail(email);
        if (email.trim({ it <= ' ' }).length == 0) {
            Toast.makeText(this, " Chưa Nhập Email Kìa", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()
        } else if (pass.trim({ it <= ' ' }).length == 0) {
            Toast.makeText(this, " Chưa Nhập Pass Kìa", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()
        } else if (pass.trim({ it <= ' ' }).length < 6) {
            Toast.makeText(this, " Mật Khẩu Phải Lớn Hơn 6 Ký Tự Bạn Ơi", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()
        } else if (pass != repass) {
            Toast.makeText(this, " Mật Khẩu Không Trùng Nhau Kìa", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()
        }
         else {
            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, " Đăng ký thành công", Toast.LENGTH_SHORT)
                            .show()
                        progressDialog.dismiss()
                        val intent = Intent(this, DangNhapActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        progressDialog.dismiss()
                        Log.w("Tag", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this, "Tài Khoản Bị Trùng",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                })
        }
    }
    var firebaseAuth :FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky)
        btdangky.setOnClickListener(this)
    }
}
