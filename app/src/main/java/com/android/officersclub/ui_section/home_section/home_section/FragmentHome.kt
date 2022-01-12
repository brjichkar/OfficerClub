package com.android.officersclub.ui_section.home_section.home_section

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.home_section.adapter.AdapterNews
import com.android.officersclub.ui_section.home_section.home_section.model.NewsModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.ui_section.base_section.BaseFragment
import com.android.officersclub.ui_section.home_section.home_section.model.Data
import com.android.officersclub.ui_section.home_section.home_section.model.DataX
import com.android.officersclub.ui_section.home_section.home_section.mvp.HomeMVP
import com.android.officersclub.ui_section.home_section.home_section.mvp.HomePresenterImplementer
import com.android.officersclub.ui_section.home_section.profile_section.membership_section.ActivityMembership
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateResponse
import com.android.officersclub.ui_section.profile_section.mvp.ProfileMVP
import com.android.officersclub.ui_section.profile_section.mvp.ProfilePresenterImplementer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_row_membership.*


class FragmentHome : BaseFragment(),HomeMVP.HomeView, ProfileMVP.ProfileView {
    private lateinit var mAdapter: AdapterNews
    //private lateinit var rvNews: RecyclerView
    private lateinit var rvFacility: RecyclerView
    //private var mNewsList: MutableList<DataX> = mutableListOf()
    private var mFacilitiesList: MutableList<DataX> = mutableListOf()
    private lateinit var mFacilityAdapter: AdapterNews
    private lateinit var mPresenterProfile: ProfileMVP.ProfilePresenter
    private lateinit var mPresenter: HomeMVP.HomePresenter
    private lateinit var mAppPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tempView = inflater.inflate(R.layout.fragment_home, container, false)
        mFacilityAdapter= AdapterNews(mFacilitiesList,requireContext())
        mPresenter=HomePresenterImplementer(this)
        mAppPreference= AppPreference(requireContext())
        val mLayoutManager = LinearLayoutManager(requireContext())
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        
        val mLayoutManager1 = LinearLayoutManager(requireContext())
        mLayoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        rvFacility = tempView.findViewById(R.id.rv_facility)
        rvFacility.layoutManager = mLayoutManager1
        rvFacility.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rvFacility.adapter = mFacilityAdapter

        val req = ProfileRequest()
        req.jsondata = ProfileRequest().Jsondata()
        req.jsondata!!.userId = mAppPreference.usersId
        mPresenterProfile = ProfilePresenterImplementer(this)
        mPresenterProfile.onProfileButtonClicked(req)

        return tempView
    }

    override fun onResume() {
        super.onResume()
        mPresenter.attachView(this)
        mPresenterProfile.attachView(this)
    }

    override fun onDestroy() {
        mPresenter.destroyView()
        mPresenterProfile.destroyView()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val req = ProfileRequest()
        req.jsondata = ProfileRequest().Jsondata()
        req.jsondata!!.userId = mAppPreference.usersId
        mPresenter.onFacilityRequest(req)
        mPresenter.onMembershipRequest(req)
        btn_view_more.setOnClickListener {
            val mainActIntent = Intent(requireContext(), ActivityMembership::class.java)
            startActivity(mainActIntent)
        }

    }

    override fun onFacilititySuccessful(tempResponse: Data) {
        mFacilitiesList.clear()
        mFacilitiesList.addAll(tempResponse.data)
        mFacilityAdapter.notifyDataSetChanged()
    }

    override fun onFacilitityFailed() {
        onError("Something went wrong")
    }

    override fun onMembershipSuccess(tempResponse: com.android.officersclub.ui_section.home_section.home_section.model.membership.DataX) {
        tv_start_end.text = tempResponse.membership_date
        tv_end_date.text = tempResponse.end_date
        tv_enrolled.text = ""+tempResponse.enrolled
        tv_members.text = tempResponse.members
    }

    override fun onProfileSuccessful(userDetails: ProfileResponse.Data.ProfileData) {
        mAppPreference.userName=userDetails.fname!!+ " " + userDetails.mname!!+ " " + userDetails.lname!!
        mAppPreference.userEmail=userDetails.email!!

        val list: List<String> = mAppPreference.userName.trim().split("\\s+".toRegex())
        val fname = "Congratulations "+list[0]+"!"
        tv_name.text =fname
    }

    override fun onProfileFailed() {
    }

    override fun onProfileUpdateSuccessful(tempResponse: ProfileUpdateResponse.Data) {
    }
}