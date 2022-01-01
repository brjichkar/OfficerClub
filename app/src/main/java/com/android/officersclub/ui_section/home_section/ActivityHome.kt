package com.android.officersclub.ui_section.home_section

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.databinding.ActivityHomeBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityHome : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var mActivityHomeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding=DataBindingUtil.setContentView(this,R.layout.activity_home)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return true
    }
}