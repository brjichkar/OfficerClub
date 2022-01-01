package com.android.officersclub.ui_section.otp_section

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.databinding.ActivityOtpBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.profile_section.ActivityProfile

class ActivityOtp : BaseActivity() {
    private lateinit var mActivityOtpBinding: ActivityOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityOtpBinding = DataBindingUtil.setContentView(this,R.layout.activity_otp)
        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }
        mActivityOtpBinding.tvResend.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Didn't receive the OTP? - <span style=\"color: #FF0000;\">Resend</span></h6>", Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Didn't receive the OTP? - <span style=\"color: #FF0000;\">Resend</span></h6>")
        }
        mActivityOtpBinding.btnVerify.setOnClickListener {
            val mainActIntent = Intent(this, ActivityProfile::class.java)
            startActivity(mainActIntent)
            finish()
        }
    }

   }