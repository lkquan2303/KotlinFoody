package com.example.foodykotlin.View

import android.app.Activity
import com.facebook.FacebookSdk;
import android.app.ProgressDialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.facebook.appevents.AppEventsLogger;
import android.widget.Toast
import com.example.foodykotlin.R
import com.facebook.CallbackManager
import com.facebook.FacebookException
import com.facebook.internal.WebDialog
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_dang_ky.*
import kotlinx.android.synthetic.main.activity_dang_nhap_activity.*
import kotlinx.android.synthetic.main.layout_flashscreen.*
import java.util.*
import com.facebook.FacebookCallback as FacebookCallback1

class DangNhapActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {
    override fun onAuthStateChanged(p0: FirebaseAuth) {
        var user = p0.currentUser
        if(user != null)
        {
            var intent = Intent(this, TrangChuActivity :: class.java)
            startActivity(intent)
//            FirebaseAuth.getInstance().signOut()
        }
        else
        {

        }
    }

    override fun onClick(p0: View?) {
//        pd = ProgressDialog(this)
//        pd.setMessage("Đang xử lý.....")
//        pd.setProgressStyle(ProgressDialog.BUTTON_NEGATIVE)
//        pd.show()
        val id = p0?.id
        when (id) {
            R.id.btdngoogle -> DangNhapGoogle(apiClient)
            R.id.btdnfb -> DangNhapFaceBook()
            R.id.tvdk -> ChuyenManHinhdk()
            R.id.btdangnhap -> DangNhap()
            R.id.tvquenmk -> ChuyenManHinhqmk()
        }
    }
    override fun onConnectionFailed(p0: ConnectionResult) {

    }
    lateinit var pd : ProgressDialog
    var requestDNGoogle :Int = 99
    var checkinputlogin : Int =0
    var firebaseAuth = FirebaseAuth.getInstance()
    var callbackManager :CallbackManager = CallbackManager.Factory.create()
    lateinit var apiClient : GoogleApiClient
    var loginManager = LoginManager.getInstance()
    internal var permissionFacebook = Arrays.asList("email", "public_profile")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(R.layout.activity_dang_nhap_activity)
        pd = ProgressDialog(this)
        btdngoogle.setOnClickListener(this)
        btdnfb.setOnClickListener(this)
        tvdk.setOnClickListener(this)
        btdangnhap.setOnClickListener(this)
        TaoClientDangNhapGoogle()
        tvquenmk.setOnClickListener (this)
    }
    private fun TaoClientDangNhapGoogle() {
        val signInOptions = GoogleSignInOptions.Builder()
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

         apiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
            .build()
    }
    private fun DangNhapGoogle(apiClient: GoogleApiClient)
    {
        checkinputlogin = 1
        val iDNGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient)
        startActivityForResult(iDNGoogle, requestDNGoogle)

    }
    private fun ChungThucDangNhapFireBase(tokenID : String )
    {
        if(checkinputlogin == 1)
        {
            var authCredential = GoogleAuthProvider.getCredential(tokenID, null)
            firebaseAuth.signInWithCredential(authCredential)
        }else if(checkinputlogin == 2)
        {
            var authCredential = FacebookAuthProvider.getCredential(tokenID)
            firebaseAuth.signInWithCredential(authCredential)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == requestDNGoogle)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                var signinResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                var account = signinResult.signInAccount
                var tokenId = account?.idToken
                if (tokenId != null) {
                    ChungThucDangNhapFireBase(tokenId)
                }
            }
        }else
        {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(this)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(this)
    }
    private fun DangNhapFaceBook()
    {
        loginManager = LoginManager.getInstance()
        loginManager.logInWithReadPermissions(this, permissionFacebook)
        loginManager.registerCallback(callbackManager, object : FacebookCallback1<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                checkinputlogin = 2
                var tokenId : String = loginResult.accessToken.userId
                ChungThucDangNhapFireBase(tokenId)
            }
            override fun onCancel() {

            }
            override fun onError(error: FacebookException) {

            }
        })
    }
    private fun ChuyenManHinhdk()
    {
        var intent = Intent(this, DangKyActivity::class.java )
        startActivity(intent)
    }
    private fun ChuyenManHinhqmk()
    {
        var intent = Intent(this, QuenMatKhauActivity::class.java )
        startActivity(intent)
    }
    private fun DangNhap( )
    {
        pd.setMessage("Đang xử lý")
        pd.setProgressStyle(ProgressDialog.BUTTON_NEGATIVE)
        pd.show()
        var tdn = edemaildn.text.toString()
        var mk = edpassdn.text.toString()
        firebaseAuth.signInWithEmailAndPassword(tdn,mk).addOnCompleteListener(OnCompleteListener {
            pd.dismiss()
        })
    }
}



