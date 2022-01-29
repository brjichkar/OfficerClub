package com.android.officersclub.ui_section.home_section

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_home.*
import java.util.ArrayList
import android.view.MotionEvent

import android.view.View.OnTouchListener
import android.widget.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class ActivityHome : BaseActivity(), NavigationBarView.OnItemSelectedListener ,
    androidx.viewpager.widget.ViewPager.OnPageChangeListener
{
    private lateinit var mActivityHomeBinding: ActivityHomeBinding
    private var mFragments: ArrayList<Fragment>? = null
    private var previousPosition=-1
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
        main_view_pager.addOnPageChangeListener(this)

        main_view_pager.setOnTouchListener(OnTouchListener { v, event -> true })
        mActivityHomeBinding.fab.setOnClickListener {
            openDialog()
        }

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false
        bottomNavigationView.setOnItemSelectedListener(this)
    }

    override fun onFragmentAttached() {
        
    }

    override fun onFragmentDetached(tag: String?) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
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
            main_view_pager.setCurrentItem(position, false)
            previousPosition=position
        }
        return true
    }

    fun openDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        val rowList: View = layoutInflater.inflate(R.layout.layout_shortcut, null)
        val ivfacility: ImageView = rowList.findViewById(R.id.iv_facility)
        val ivContact: ImageView = rowList.findViewById(R.id.iv_contact)
        val ivReview: ImageView = rowList.findViewById(R.id.iv_review)

        alertDialog.setView(rowList)
        val dialog: AlertDialog = alertDialog.create()

        ivfacility.setOnClickListener {
            onPageSelected(0)
            dialog.dismiss()
        }

        ivContact.setOnClickListener {
            dialog.dismiss()
            Dexter.withContext(this)
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8087027127"))
                        startActivity(intent)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        onError("Please enable call permission.")
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) { /* ... */
                    }
                }).check()
        }

        ivReview.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=com.android.officersclub")
            startActivity(intent)
        }


        dialog.setCancelable(true)
        dialog.show()
    }

    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        /*var positionItem=position
        if(position>=2){
            positionItem=position+1
        }*/
        main_view_pager.setCurrentItem(position, false)
    }

}