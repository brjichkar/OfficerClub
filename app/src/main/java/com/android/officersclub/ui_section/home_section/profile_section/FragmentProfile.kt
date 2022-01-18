package com.android.officersclub.ui_section.home_section.profile_section

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.android.officersclub.R
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.ui_section.home_section.profile_section.family_section.ActivityFamilyDetails
import com.android.officersclub.ui_section.home_section.profile_section.membership_section.ActivityMembership
import com.android.officersclub.ui_section.login_section.ActivityLogin
import com.android.officersclub.ui_section.otp_section.ActivityOtp
import com.android.officersclub.ui_section.profile_section.ActivityProfile
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*

class FragmentProfile : Fragment() {
    private lateinit var mAppPreference: AppPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tempView= inflater.inflate(R.layout.fragment_profile, container, false)
        mAppPreference= AppPreference(requireContext())
        return tempView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_logout.setOnClickListener {
            mAppPreference.clearAllPreferences()
            val mainActIntent = Intent(requireContext(), ActivityLogin::class.java)
            mainActIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            mainActIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            mainActIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(mainActIntent)
            requireActivity().finish()
        }
        tv_title.text = mAppPreference.userName
        tv_mobile.text = mAppPreference.userMobile

        rl_family.setOnClickListener {
            val mainActIntent = Intent(requireContext(), ActivityFamilyDetails::class.java)
            startActivity(mainActIntent)
        }

        rl_membership.setOnClickListener {
            val mainActIntent = Intent(requireContext(), ActivityMembership::class.java)
            startActivity(mainActIntent)
        }

        rl_profile.setOnClickListener {
            val mainActIntent = Intent(context, ActivityProfile::class.java)
            startActivity(mainActIntent)
        }

        Glide.with(requireActivity())
            .load(mAppPreference.userImage)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(R.drawable.profile_icon)
            .into(iv_room_icon)
    }
}