package com.android.officersclub.ui_section.home_section.home_section

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.home_section.adapter.AdapterGallery
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.ui_section.base_section.BaseFragment
import com.android.officersclub.ui_section.home_section.home_section.adapter.AdapterFacilities
import com.android.officersclub.ui_section.home_section.home_section.adapter.AdapterVideos
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
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_row_membership.*


class FragmentHome : BaseFragment(),HomeMVP.HomeView, ProfileMVP.ProfileView {
    private lateinit var rvGallery: RecyclerView
    private lateinit var rvFacility: RecyclerView
    private lateinit var rvVideo : RecyclerView

    private var mGalleryList: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.gallery.DataX> = mutableListOf()
    private var mFacilitiesList: MutableList<DataX> = mutableListOf()
    private var mVideoList: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.videos.DataX> = mutableListOf()

    private lateinit var mGalleryAdapter: AdapterGallery
    private lateinit var mFacilitiesAdapter: AdapterFacilities
    private lateinit var mVideosAdapter: AdapterVideos

    private lateinit var mPresenterProfile: ProfileMVP.ProfilePresenter
    private lateinit var mPresenter: HomeMVP.HomePresenter
    private lateinit var mAppPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tempView = inflater.inflate(R.layout.fragment_home, container, false)

        mGalleryAdapter= AdapterGallery(mGalleryList,requireContext())
        mFacilitiesAdapter= AdapterFacilities(mFacilitiesList,requireContext())
        mVideosAdapter= AdapterVideos(mVideoList,requireContext())

        mPresenter=HomePresenterImplementer(this)
        mAppPreference= AppPreference(requireContext())

        val mLayoutManager = LinearLayoutManager(requireContext())
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvFacility = tempView.findViewById(R.id.rv_facility)
        rvFacility.layoutManager = mLayoutManager
        rvFacility.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rvFacility.adapter = mFacilitiesAdapter

        val mLayoutManager1 = LinearLayoutManager(requireContext())
        mLayoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        rvGallery = tempView.findViewById(R.id.rv_gallery)
        rvGallery.layoutManager = mLayoutManager1
        rvGallery.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rvGallery.adapter = mGalleryAdapter

        val mLayoutManager2 = LinearLayoutManager(requireContext())
        mLayoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        rvVideo = tempView.findViewById(R.id.rv_video)
        rvVideo.layoutManager = mLayoutManager2
        rvVideo.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rvVideo.adapter = mVideosAdapter

        val req = ProfileRequest()
        req.jsondata = ProfileRequest().Jsondata()
        req.jsondata!!.userId = mAppPreference.usersId
        mPresenterProfile = ProfilePresenterImplementer(this)
        mPresenterProfile.onProfileButtonClicked(req)
        mPresenter.onFacilityRequest(req)
        mPresenter.onGalleryRequest(req)
        mPresenter.onVideosRequest(req)
        mPresenter.onMembershipRequest(req)
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
        btn_view_more.setOnClickListener {
            val mainActIntent = Intent(requireContext(), ActivityMembership::class.java)
            startActivity(mainActIntent)
        }

    }

    override fun onFacilititySuccessful(tempResponse: Data) {
        mFacilitiesList.clear()
        mFacilitiesList.addAll(tempResponse.data)
        mFacilitiesAdapter.notifyDataSetChanged()
    }

    override fun onFacilitityFailed() {
        onError("Something went wrong")
    }

    override fun onMembershipSuccess(tempResponse: com.android.officersclub.ui_section.home_section.home_section.model.membership.DataX) {
        tv_start_end.text = tempResponse.membership_date
        tv_end_date.text = tempResponse.end_date
        tv_enrolled.text = ""+tempResponse.enrolled
        tv_members.text = tempResponse.members
        tv_short_info.text ="You have enrolled in ${tempResponse.enrolled} facilities this month"
    }

    override fun onGallerySuccess(tempResponse: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.gallery.DataX>) {
        mGalleryList.clear()
        mGalleryList.addAll(tempResponse)
        mGalleryAdapter.notifyDataSetChanged()
    }

    override fun onVideosSuccess(tempResponse: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.videos.DataX>) {
        mVideoList.clear()
        mVideoList.addAll(tempResponse)
        mVideosAdapter.notifyDataSetChanged()
    }

    override fun onProfileSuccessful(userDetails: ProfileResponse.Data.ProfileData) {
        mAppPreference.userName=userDetails.fname!!+ " " + userDetails.mname!!+ " " + userDetails.lname!!
        mAppPreference.userEmail=userDetails.email!!
        mAppPreference.userImage=userDetails.imagePath!!
        val list: List<String> = mAppPreference.userName.trim().split("\\s+".toRegex())
        val fname = "Congratulations "+list[0]+"!"
        tv_name.text =fname
        Glide.with(requireActivity())
            .load(userDetails.imagePath)
            .centerCrop()
            .placeholder(R.drawable.profile_icon)
            .into(iv_user_img)

        tv_full_name.text="Name-"+mAppPreference.userName
        tv_dob.text="DOB-"+userDetails.dob
        tv_email.text="Email-"+userDetails.email
        tv_mobile_home.text="Mobile-"+userDetails.mobile
    }

    override fun onProfileFailed() {
    }

    override fun onProfileUpdateSuccessful(tempResponse: ProfileUpdateResponse.Data) {
    }

    override fun onProfileImageUploadSuccess(tempResponse: com.android.officersclub.ui_section.profile_section.model.photo.Data) {

    }
}