package com.android.officersclub.ui_section.home_section.booking_section.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.databinding.ActivityDetailsBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DataX
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DetailsRequest
import com.android.officersclub.ui_section.home_section.booking_section.details.model.Jsondata
import com.android.officersclub.ui_section.home_section.booking_section.details.mvp.ClubDetailsMVP
import com.android.officersclub.ui_section.home_section.booking_section.details.mvp.DetailsPresenterImplementer
import com.denzcoskun.imageslider.models.SlideModel

class ActivityDetails : BaseActivity(),ClubDetailsMVP.DetailsView {
    private lateinit var mActivityDetailsBinding: ActivityDetailsBinding
    private lateinit var mPresenter: ClubDetailsMVP.DetailsPresenter
    private lateinit var mAppPreference: AppPreference
    private var mLink=""
    private var facilityCode="1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityDetailsBinding=DataBindingUtil.setContentView(this,R.layout.activity_details)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        mPresenter=DetailsPresenterImplementer(this)
        mAppPreference= AppPreference(this)

        val data = intent.extras
        if (data != null) {
            if (data.containsKey("selectedItem")) {
                facilityCode = data.getString("selectedItem")!!
            }
        }

        val tempjson= Jsondata(facilityCode,mAppPreference.usersId)
        val req = DetailsRequest(tempjson)
        mPresenter.onDetailsRequest(req)

        mActivityDetailsBinding.tvVideo.setOnClickListener {
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(mLink)
            )
            try {
                startActivity(webIntent)
            } catch (ex: ActivityNotFoundException) {
            }
        }

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

    override fun onDetailsReceived(tempResponse: DataX) {
        mActivityDetailsBinding.tvName.text =tempResponse.name
        mActivityDetailsBinding.tvVideo.text = ""+tempResponse.video
        mLink=tempResponse.video
        if (tempResponse.description.contains("<html")) {
                    mActivityDetailsBinding.webview.loadDataWithBaseURL(
                        null,
                        tempResponse.description,
                        "text/html; charset=UTF-8",
                        "utf-8",
                        null
                    )
                } else {
                    mActivityDetailsBinding.webview.loadData(
                        tempResponse.description,
                        "text/html; charset=UTF-8",
                        null
                    )
                }


        val imageList = ArrayList<SlideModel>() // Create image list

        tempResponse.slider_images
        for(item in tempResponse.slider_images)
        {
            imageList.add(SlideModel(item.image_path))
        }
        mActivityDetailsBinding.imageSlider.setImageList(imageList)
    }

    override fun onDetailsFailed() {
        finish()
    }

    override fun onServicesReceived(tempResponse: com.android.officersclub.ui_section.home_section.booking_section.details.model.serreq.DataX) {

    }

    override fun onServicesFailed() {
    }
}