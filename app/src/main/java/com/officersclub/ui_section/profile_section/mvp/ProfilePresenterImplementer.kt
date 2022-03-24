package com.officersclub.ui_section.profile_section.mvp

import com.officersclub.R
import com.officersclub.ui_section.profile_section.model.ProfileRequest
import com.officersclub.ui_section.profile_section.model.ProfileResponse
import com.officersclub.ui_section.profile_section.model.ProfileUpdateRequest
import com.officersclub.ui_section.profile_section.model.ProfileUpdateResponse
import com.officersclub.ui_section.profile_section.model.photo.Data
import java.io.File

class ProfilePresenterImplementer(tempView: ProfileMVP.ProfileView): ProfileMVP.ProfilePresenter,
    ProfileMVP.ProfileModel.OnProfileFinishedListener, ProfileMVP.ProfileModel.OnProfileUpdateFinishedListener,
    ProfileMVP.ProfileModel.OnPhotoUpdateFinishedListener {
    private var mView: ProfileMVP.ProfileView? = tempView
    private var mModel: ProfileMVP.ProfileModel = ProfileModelImplementer()

    override fun attachView(tempView: ProfileMVP.ProfileView) {
        mView=tempView
    }

    override fun destroyView() {
        mView=null
    }

    override fun onProfileButtonClicked(tempRequest: ProfileRequest) {
        if(mView!=null)
        {
            mView!!.hideKeyboard()
            if(mView!!.isNetworkConnected){
                mView!!.showLoading()
                mModel.processProfile(this,tempRequest)
            }
            else{
                mView!!.hideLoading()
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onProfileUpdate(tempRequest: ProfileUpdateRequest) {
        if(mView!=null)
        {
            mView!!.hideKeyboard()
            if(mView!!.isNetworkConnected){
                mView!!.showLoading()
                mModel.processProfileUpdate(this,tempRequest)
            }
            else{
                mView!!.hideLoading()
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onPhotoUpload(userId: String, imageFile: File) {
        if(mView!=null)
        {
            mView!!.hideKeyboard()
            if(mView!!.isNetworkConnected){
                mView!!.showLoading()
                mModel.processPhotoUpdate(this,userId,imageFile)
            }
            else{
                mView!!.hideLoading()
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onProfileFinished(tempResponse: ProfileResponse.Data.ProfileData) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onProfileSuccessful(tempResponse)
        }
    }

    override fun onProfileFailure(warnings: String) {
        mView!!.hideLoading()
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }

    override fun onProfileUpdate(tempResponse: ProfileUpdateResponse.Data) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onProfileUpdateSuccessful(tempResponse)
        }
    }

    override fun onProfileUpdateFailure(warnings: String) {
        mView!!.hideLoading()
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }

    override fun onPhotoUpdate(tempResponse: Data) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onProfileImageUploadSuccess(tempResponse)
        }
    }

    override fun onPhotoUpdateFailure(warnings: String) {
        mView!!.hideLoading()
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }
}