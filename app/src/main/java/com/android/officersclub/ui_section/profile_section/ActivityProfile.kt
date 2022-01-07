package com.android.officersclub.ui_section.profile_section

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.databinding.ActivityProfileBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.home_section.ActivityHome
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileResponse
import com.android.officersclub.ui_section.profile_section.mvp.ProfileMVP
import com.android.officersclub.ui_section.profile_section.mvp.ProfilePresenterImplementer
import com.github.dhaval2404.imagepicker.ImagePicker
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*


class ActivityProfile : BaseActivity(), DatePickerDialog.OnDateSetListener,ProfileMVP.ProfileView {
    private lateinit var mActivityProfileBinding: ActivityProfileBinding
    private lateinit var mPresenter: ProfileMVP.ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityProfileBinding=DataBindingUtil.setContentView(this,R.layout.activity_profile)
        mPresenter= ProfilePresenterImplementer(this)
        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }


        mActivityProfileBinding.etBday.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                this,
                now[Calendar.YEAR],  // Initial year selection
                now[Calendar.MONTH],  // Initial month selection
                now[Calendar.DAY_OF_MONTH] // Inital day selection
            )
            dpd.maxDate=now
            dpd.show(supportFragmentManager, "Select Date")
        }

        mActivityProfileBinding.ivUserImg.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .galleryMimeTypes(  //Exclude gif images
                    mimeTypes = arrayOf(
                        "image/png",
                        "image/jpg",
                        "image/jpeg"
                    )
                )
                .start()
        }

        mActivityProfileBinding.btnSubmit.setOnClickListener {
            val mainActIntent = Intent(this, ActivityHome::class.java)
            startActivity(mainActIntent)
            finish()
        }

        val req= ProfileRequest()
        req.jsondata= ProfileRequest().Jsondata()
        req.jsondata!!.userId="1"
        mPresenter.onProfileButtonClicked(req)
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        mActivityProfileBinding.etBday.setText(""+dayOfMonth+"/"+(monthOfYear+1)+"/"+year)
        //showMessage("You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            // Use Uri object instead of File to avoid storage permissions
            mActivityProfileBinding.ivUserImg.setImageURI(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            onError(ImagePicker.getError(data))
        } else {
            onError("Task Cancelled")
        }
    }

    override fun onProfileSuccessful(userDetails: ProfileResponse.Data.ProfileData) {
        mActivityProfileBinding.apply {
            etName.setText(userDetails.fname+" "+userDetails.mname+" "+userDetails.lname)
            etEmail.setText(userDetails.email)
            etBday.setText(userDetails.dob)
            etAddress.setText(userDetails.mobile)
            if(userDetails.gender=="Male"){
                rbMale.isChecked=true
            }
            else if(userDetails.gender=="Female"){
                rbFemale.isChecked=true
            }
        }

    }

    override fun onProfileFailed() {
        onError("Something went wrong")
    }
}