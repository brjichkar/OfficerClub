package com.android.officersclub.ui_section.home_section.profile_section.family_section

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import android.widget.Toast
import com.android.officersclub.ui_section.profile_section.model.photo.Data
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File
import java.security.AccessController.getContext


class ActivityFamilyDetails : BaseActivity(), FamilyMVP.FamilyView {
    private var mList: MutableList<DataX> = mutableListOf()
    private lateinit var mAdapter: AdapterFamily
    private lateinit var mPresenter: FamilyMVP.FamilyPresenter
    private lateinit var mAppPreference:AppPreference
    private var tempImagePath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_details)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mAdapter= AdapterFamily(mList,this,object : AdapterFamily.OnItemClickListener {
            override fun onItemClick(item: DataX?) {
                ImagePicker.with(this@ActivityFamilyDetails)
                    .crop().compress(1024)
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
        })
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val uri: Uri = data?.data!!
                val imageFile: File = File(uri.path)
                mPresenter.onPhotoUpload(mAppPreference.usersId,imageFile)
            }
            ImagePicker.RESULT_ERROR -> {
                onError(ImagePicker.getError(data))
            }
            else -> {
                onError("Task Cancelled")
            }
        }
    }

    override fun onFamilySuccessful(tempResponse: MutableList<DataX>) {
        mList.clear()
        mList.addAll(tempResponse)
        mAdapter.notifyDataSetChanged()
    }

    override fun onFamilyFailed() {

    }

    override fun onProfileImageUploadSuccess(tempResponse: Data) {
        onError("Image upload success")
    }
}