package com.android.officersclub.ui_section.home_section

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.databinding.ActivityHomeBinding
import com.android.officersclub.ui_section.base_section.BaseActivity

class ActivityHome : BaseActivity() {
    private lateinit var mActivityHomeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding=DataBindingUtil.setContentView(this,R.layout.activity_home)


    }
}