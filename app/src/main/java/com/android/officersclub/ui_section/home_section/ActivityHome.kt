package com.android.officersclub.ui_section.home_section

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.officersclub.R
import com.android.officersclub.databinding.ActivityHomeBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.home_section.booking_section.FragmentBooking
import com.android.officersclub.ui_section.home_section.home_section.FragmentHome
import com.android.officersclub.ui_section.home_section.profile_section.FragmentProfile
import com.android.officersclub.ui_section.home_section.support_section.FragmentSupport
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import java.util.ArrayList

class ActivityHome : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var mActivityHomeBinding: ActivityHomeBinding
    private var mFragments: ArrayList<Fragment>? = null// used for ViewPager adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        val fragments: ArrayList<androidx.fragment.app.Fragment> =  ArrayList(4)
        fragments!!.add(0, FragmentHome())
        fragments!!.add(1, FragmentBooking())
        fragments!!.add(2, FragmentProfile())
        fragments!!.add(3, FragmentSupport())
        val mHomeAdapter = HomeTabAdapter(supportFragmentManager, fragments!!)
        main_view_pager.adapter = mHomeAdapter
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }


}