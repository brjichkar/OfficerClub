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
import com.android.officersclub.ui_section.otp_section.ActivityOtp
import com.android.officersclub.ui_section.profile_section.ActivityProfile
import kotlinx.android.synthetic.main.fragment_profile.*

class FragmentProfile : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tempView= inflater.inflate(R.layout.fragment_profile, container, false)
        val rlProfile:RelativeLayout= tempView.findViewById(R.id.rl_profile)
        rlProfile.setOnClickListener {
            val mainActIntent = Intent(context, ActivityProfile::class.java)
            startActivity(mainActIntent)
        }

        return tempView
    }
}