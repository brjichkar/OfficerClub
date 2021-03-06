package com.officersclub.ui_section.otp_section

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.databinding.DataBindingUtil
import com.officersclub.R
import com.officersclub.app_preferences.AppPreference
import com.officersclub.databinding.ActivityOtpBinding
import com.officersclub.ui_section.base_section.BaseActivity
import com.officersclub.ui_section.home_section.ActivityHome
import com.officersclub.ui_section.profile_section.ActivityProfile

class ActivityOtp : BaseActivity() {
    private lateinit var mActivityOtpBinding: ActivityOtpBinding
    private var mNextIsProfile=""
    private lateinit var mAppPreference:AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityOtpBinding = DataBindingUtil.setContentView(this,R.layout.activity_otp)
        mAppPreference= AppPreference(this)
        val data=intent.extras
        if(data!=null){
            if(data.containsKey("profile_status")){
                mNextIsProfile=data.getString("profile_status")!!
            }
        }
        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }
        mActivityOtpBinding.tvResend.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Didn't receive the OTP? - <span style=\"color: #FF0000;\">Resend</span></h6>", Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Didn't receive the OTP? - <span style=\"color: #FF0000;\">Resend</span></h6>")
        }
        mActivityOtpBinding.btnVerify.setOnClickListener {
            mAppPreference.isUserLoggedIn=true
            if(mAppPreference.userProfile == "1"){
                val mainActIntent = Intent(this, ActivityHome::class.java)
                startActivity(mainActIntent)
                finish()
            }
            else{
                val mainActIntent = Intent(this, ActivityProfile::class.java)
                mainActIntent.putExtra("is_profile_complete",false)
                startActivity(mainActIntent)
                finish()
            }
        }
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String?) {
    }

}