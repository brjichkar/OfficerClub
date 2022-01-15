package com.android.officersclub.ui_section.profile_section

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.databinding.ActivityProfileBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.home_section.ActivityHome
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateResponse
import com.android.officersclub.ui_section.profile_section.model.photo.Data
import com.android.officersclub.ui_section.profile_section.mvp.ProfileMVP
import com.android.officersclub.ui_section.profile_section.mvp.ProfilePresenterImplementer
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File
import java.util.*


class ActivityProfile : BaseActivity(), DatePickerDialog.OnDateSetListener, ProfileMVP.ProfileView {
    private lateinit var mActivityProfileBinding: ActivityProfileBinding
    private lateinit var mPresenter: ProfileMVP.ProfilePresenter
    private var mNextEnabled = false
    private lateinit var mAppPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        mPresenter = ProfilePresenterImplementer(this)
        mAppPreference= AppPreference(this)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        val data = intent.extras
        if (data != null) {
            if (data.containsKey("is_profile_complete")) {
                mNextEnabled = data.getBoolean("is_profile_complete")!!
            }
        }
        Glide.with(this)
            .load(mAppPreference.userImage)
            .centerCrop()
            .placeholder(R.drawable.profile_icon)
            .into(mActivityProfileBinding.ivUserImg)

        mActivityProfileBinding.etBday.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                this,
                now[Calendar.YEAR],  // Initial year selection
                now[Calendar.MONTH],  // Initial month selection
                now[Calendar.DAY_OF_MONTH] // Inital day selection
            )
            dpd.maxDate = now
            dpd.show(supportFragmentManager, "Select Date")
        }

        mActivityProfileBinding.ivUserImg.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
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
            val tempRequest = ProfileUpdateRequest()
            tempRequest.jsondata = ProfileUpdateRequest().ProfileData()
            tempRequest.jsondata!!.userId=mAppPreference.usersId
            tempRequest.jsondata!!.fname = mActivityProfileBinding.etName.text.toString()
            tempRequest.jsondata!!.mname = ""
            tempRequest.jsondata!!.lname = ""
            tempRequest.jsondata!!.mobile = mActivityProfileBinding.etAddress.text.toString()
            tempRequest.jsondata!!.email = mActivityProfileBinding.etEmail.text.toString()
            tempRequest.jsondata!!.dob = mActivityProfileBinding.etBday.text.toString()
            if(mActivityProfileBinding.rbMale.isChecked){
                tempRequest.jsondata!!.gender="Male"
            }
            if(mActivityProfileBinding.rbFemale.isChecked){
                tempRequest.jsondata!!.gender="Female"
            }
            mPresenter.onProfileUpdate(tempRequest)
        }

        val req = ProfileRequest()
        req.jsondata = ProfileRequest().Jsondata()
        req.jsondata!!.userId = mAppPreference.usersId
        mPresenter.onProfileButtonClicked(req)
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        mActivityProfileBinding.etBday.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val uri: Uri = data?.data!!
                mActivityProfileBinding.ivUserImg.setImageURI(uri)
                val imageFile: File = File(uri.path)
                mPresenter.onPhotoUpload( mAppPreference.usersId,imageFile)
            }
            ImagePicker.RESULT_ERROR -> {
                onError(ImagePicker.getError(data))
            }
            else -> {
                onError("Task Cancelled")
            }
        }
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String?) {
    }

    override fun onProfileSuccessful(userDetails: ProfileResponse.Data.ProfileData) {
        mActivityProfileBinding.apply {
            mAppPreference.userName=userDetails.fname!!+ " " + userDetails.mname!!+ " " + userDetails.lname!!
            mAppPreference.userEmail=userDetails.email!!
            mAppPreference.userImage=userDetails.imagePath!!

            Glide.with(this@ActivityProfile)
                .load(mAppPreference.userImage)
                .centerCrop()
                .placeholder(R.drawable.profile_icon)
                .into(mActivityProfileBinding.ivUserImg)

            etName.setText(mAppPreference.userName.trim())
            etEmail.setText(userDetails.email!!.trim())
            etBday.setText(userDetails.dob!!.trim())
            etAddress.setText(userDetails.mobile!!.trim())
            if (userDetails.gender == "Male") {
                rbMale.isChecked = true
            } else if (userDetails.gender == "Female") {
                rbFemale.isChecked = true
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
    override fun onProfileFailed() {
        onError("Something went wrong")
    }

    override fun onProfileUpdateSuccessful(tempResponse: ProfileUpdateResponse.Data) {
        onError("Profile updated successfully.")
        if (mNextEnabled) {
            val mainActIntent = Intent(this, ActivityHome::class.java)
            startActivity(mainActIntent)
            finish()
        }
        else{
            val req = ProfileRequest()
            req.jsondata = ProfileRequest().Jsondata()
            req.jsondata!!.userId = mAppPreference.usersId
            mPresenter.onProfileButtonClicked(req)
        }
    }

    override fun onProfileImageUploadSuccess(tempResponse: Data) {
        val req = ProfileRequest()
        req.jsondata = ProfileRequest().Jsondata()
        req.jsondata!!.userId = mAppPreference.usersId
        mPresenter.onProfileButtonClicked(req)
        //onError("Image Updated successfully.")
    }
}