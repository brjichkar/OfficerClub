package com.android.officersclub.ui_section.home_section.booking_section.details

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.databinding.ActivityServiceDetailsBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DataX
import com.android.officersclub.ui_section.home_section.booking_section.details.model.serreq.Jsondata
import com.android.officersclub.ui_section.home_section.booking_section.details.model.serreq.ServicesRequest
import com.android.officersclub.ui_section.home_section.booking_section.details.mvp.ClubDetailsMVP
import com.android.officersclub.ui_section.home_section.booking_section.details.mvp.DetailsPresenterImplementer
import com.denzcoskun.imageslider.models.SlideModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class ActivityServiceDetails : BaseActivity(), ClubDetailsMVP.DetailsView {
    private lateinit var mActivityServiceDetailsBinding: ActivityServiceDetailsBinding
    private lateinit var mAppPreference: AppPreference
    private var mLink=""
    private var facilityCode="1"
    private lateinit var mPresenter: ClubDetailsMVP.DetailsPresenter
    private var calls=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityServiceDetailsBinding=DataBindingUtil.setContentView(this,R.layout.activity_service_details)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mAppPreference= AppPreference(this)
        mPresenter= DetailsPresenterImplementer(this)
        val data = intent.extras
        if (data != null) {
            if (data.containsKey("selectedItem")) {
                facilityCode = data.getString("selectedItem")!!
            }
        }

        mActivityServiceDetailsBinding.tvVideo.setOnClickListener {
            if(calls.isEmpty()){
                onError("Phone no not present")
            }
            else {
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

        mActivityServiceDetailsBinding.tvContact.setOnClickListener {
            if(calls.isEmpty()){
                onError("Phone no not present")
            }
            else{
                Dexter.withContext(this)
                    .withPermission(Manifest.permission.CALL_PHONE)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(response: PermissionGrantedResponse) {
                            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + calls))
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
        }

        val tempjson= Jsondata(facilityCode,mAppPreference.usersId)
        val req = ServicesRequest(tempjson)
        mPresenter.onServicesRequest(req)
    }



    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String?) {
    }

    override fun onDetailsReceived(tempResponse: DataX) {

    }

    override fun onDetailsFailed() {
    }

    override fun onServicesReceived(tempResponse: com.android.officersclub.ui_section.home_section.booking_section.details.model.serreq.DataX) {
        mActivityServiceDetailsBinding.tvName.text=tempResponse.name

        calls=tempResponse.mobile
        mLink=tempResponse.video
        if (tempResponse.description.contains("<html")) {
            mActivityServiceDetailsBinding.webview.loadDataWithBaseURL(
                null,
                tempResponse.description,
                "text/html; charset=UTF-8",
                "utf-8",
                null
            )
        } else {
            mActivityServiceDetailsBinding.webview.loadData(
                tempResponse.description,
                "text/html; charset=UTF-8",
                null
            )
        }


        if(tempResponse.amount.isEmpty()){
            mActivityServiceDetailsBinding.cvAmount.visibility=View.GONE
        }
        else{
            mActivityServiceDetailsBinding.cvAmount.visibility=View.VISIBLE
            mActivityServiceDetailsBinding.tvAmount.text=tempResponse.amount+" for "+tempResponse.time
        }

        /*if(tempResponse.mobile.isEmpty()){
            mActivityServiceDetailsBinding.tvContact.visibility=View.GONE
        }
        else{
            mActivityServiceDetailsBinding.tvContact.visibility=View.VISIBLE
        }

        if(tempResponse.video.isEmpty()){
            mActivityServiceDetailsBinding.tvVideo.visibility=View.GONE
        }
        else{
            mActivityServiceDetailsBinding.tvVideo.visibility=View.VISIBLE
        }*/

        val imageList = ArrayList<SlideModel>() // Create image list

        tempResponse.slider_images
        for(item in tempResponse.slider_images)
        {
            imageList.add(SlideModel(item.image_path))
        }
        mActivityServiceDetailsBinding.imageSlider.setImageList(imageList)

    }

    override fun onServicesFailed() {

    }
}