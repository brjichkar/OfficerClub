package com.android.officersclub.ui_section.login_section

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.databinding.ActivityLoginBinding
import com.android.officersclub.ui_section.otp_section.ActivityOtp

class ActivityLogin : AppCompatActivity() {
    private lateinit var mActivityLoginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding=DataBindingUtil.setContentView(this, R.layout.activity_login)
        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }
        mActivityLoginBinding.tvInfo.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Solpur's own and most prestigious club - <span style=\"color: #FF0000;\">The Officer's Club Solpur</span>. World class sports facility at a premium location</h6>", Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Solpur's own and most prestigious club - <span style=\"color: #FF0000;\">The Officer's Club Solpur</span>. World class sports facility at a premium location</h6>")
        }

        mActivityLoginBinding.btnLogin.setOnClickListener {
            val mainActIntent = Intent(this, ActivityOtp::class.java)
            startActivity(mainActIntent)
            finish()
        }
    }
}