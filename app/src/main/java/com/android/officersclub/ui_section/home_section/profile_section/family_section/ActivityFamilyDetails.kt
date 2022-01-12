package com.android.officersclub.ui_section.home_section.profile_section.family_section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.officersclub.R
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.home_section.booking_section.adapter.AdapterBooking
import com.android.officersclub.ui_section.home_section.booking_section.model.BookingModel
import com.android.officersclub.ui_section.home_section.profile_section.family_section.adapter.AdapterFamily
import com.android.officersclub.ui_section.home_section.profile_section.family_section.model.DataX
import com.android.officersclub.ui_section.home_section.profile_section.family_section.mvp.FamilyMVP
import com.android.officersclub.ui_section.home_section.profile_section.family_section.mvp.FamilyPresenterImplementer
import com.android.officersclub.ui_section.login_section.mvp.LoginMVP
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import kotlinx.android.synthetic.main.activity_family_details.*

class ActivityFamilyDetails : BaseActivity(), FamilyMVP.FamilyView {
    private var mList: MutableList<DataX> = mutableListOf()
    private lateinit var mAdapter: AdapterFamily
    private lateinit var mPresenter: FamilyMVP.FamilyPresenter
    private lateinit var mAppPreference:AppPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_details)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        mAdapter= AdapterFamily(mList,this)
        val mLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rv_family.layoutManager = mLayoutManager
        rv_family.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_family.adapter = mAdapter
        mAppPreference=AppPreference(this)
        mPresenter=FamilyPresenterImplementer(this)
        val req = ProfileRequest()
        req.jsondata = ProfileRequest().Jsondata()
        req.jsondata!!.userId = mAppPreference.usersId
        mPresenter.onFamilyRequest(req)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        mPresenter.destroyView()
        super.onDestroy()
    }
    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String?) {
    }

    override fun onFamilySuccessful(tempResponse: MutableList<DataX>) {
        mList.clear()
        mList.addAll(tempResponse)
        mAdapter.notifyDataSetChanged()
    }

    override fun onFamilyFailed() {

    }
}