package com.android.officersclub.ui_section.otp_section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.officersclub.R

class ActivityOtp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }
    }
}