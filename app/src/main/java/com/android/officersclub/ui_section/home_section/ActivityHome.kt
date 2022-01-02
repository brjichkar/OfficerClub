package com.android.officersclub.ui_section.home_section

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.officersclub.R
import com.android.officersclub.databinding.ActivityHomeBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.home_section.booking_section.FragmentBooking
import com.android.officersclub.ui_section.home_section.home_section.FragmentHome
import com.android.officersclub.ui_section.home_section.profile_section.FragmentProfile
import com.android.officersclub.ui_section.home_section.support_section.FragmentSupport
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class ActivityHome : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    androidx.viewpager.widget.ViewPager.OnPageChangeListener,View.OnClickListener
{
    private lateinit var mActivityHomeBinding: ActivityHomeBinding
    private var previousPosition=-1
    private var fragments: ArrayList<Fragment>? = null// used for ViewPager adapter
    private lateinit var mHomeAdapter:HomeTabAdapter

    private lateinit var mPagertabs: androidx.viewpager.widget.ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }
        mPagertabs=findViewById(R.id.home_pager_tabs)
        fragments = ArrayList(4)
        fragments!!.add(0, FragmentSupport())
        fragments!!.add(1, FragmentHome())
        fragments!!.add(2, FragmentProfile())
        fragments!!.add(3, FragmentSupport())
        mHomeAdapter= HomeTabAdapter(supportFragmentManager, fragments!!)
        //mActivityHomeBinding.homePagerTabs.adapter=mHomeAdapter
        //mActivityHomeBinding.homePagerTabs.addOnPageChangeListener(this)
        mPagertabs.adapter=mHomeAdapter
        mPagertabs.addOnPageChangeListener(this)
        mActivityHomeBinding.homeBtmNav.onNavigationItemSelectedListener = this
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var position=0
        when(item.itemId){
            R.id.i_home -> {
                position = 0
            }
            R.id.i_booking -> {
                position = 1
            }
            R.id.i_profile -> {
                position = 2
            }
            R.id.i_support -> {
                position = 3
            }
            R.id.i_add -> {
                return false
            }
        }
        if(previousPosition!=position){
            mPagertabs.setCurrentItem(position, false)
            previousPosition=position
        }
        return true
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        var positionItem=position
        if(position>=2){
            positionItem=position+1
        }
        mActivityHomeBinding.homeBtmNav.currentItem=positionItem
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onClick(p0: View?) {

    }
}