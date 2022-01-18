package com.android.officersclub.ui_section.home_section.booking_section

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.officersclub.R
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.ui_section.base_section.BaseFragment
import com.android.officersclub.ui_section.home_section.booking_section.adapter.AdapterBooking
import com.android.officersclub.ui_section.home_section.booking_section.model.BookingModel
import com.android.officersclub.ui_section.home_section.home_section.adapter.AdapterFacilities
import com.android.officersclub.ui_section.home_section.home_section.model.Data
import com.android.officersclub.ui_section.home_section.home_section.model.membership.DataX
import com.android.officersclub.ui_section.home_section.home_section.mvp.HomeMVP
import com.android.officersclub.ui_section.home_section.home_section.mvp.HomePresenterImplementer
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest

class FragmentBooking : BaseFragment(), HomeMVP.HomeView {
    private var mHistoryList: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.DataX> = mutableListOf()
    private lateinit var mAdapter: AdapterBooking
    private lateinit var rvHistory:RecyclerView
    private lateinit var mPresenter: HomeMVP.HomePresenter
    private lateinit var mAppPreference: AppPreference


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tempView = inflater.inflate(R.layout.fragment_booking, container, false)
        //sampleData()
        mPresenter= HomePresenterImplementer(this)
        mAdapter= AdapterBooking(mHistoryList,requireContext())
        val mLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        rvHistory= tempView.findViewById(R.id.rv_history)
        rvHistory.layoutManager = mLayoutManager
        rvHistory.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rvHistory.adapter = mAdapter
        mAppPreference= AppPreference(requireContext())
        val req = ProfileRequest()
        req.jsondata = ProfileRequest().Jsondata()
        req.jsondata!!.userId = mAppPreference.usersId
        mPresenter.onFacilityRequest(req)
        return tempView
    }

    override fun onResume() {
        super.onResume()
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        mPresenter.destroyView()
        super.onDestroy()
    }


    override fun onFacilititySuccessful(tempResponse: Data) {
        mHistoryList.clear()
        mHistoryList.addAll(tempResponse.data)
        mAdapter.notifyDataSetChanged()
    }

    override fun onFacilitityFailed() {
        onError("Something went wrong")
    }

    override fun onMembershipSuccess(tempResponse: DataX) {
    }

    override fun onGallerySuccess(tempResponse: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.gallery.DataX>) {
    }

    override fun onVideosSuccess(tempResponse: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.videos.DataX>) {
    }

}