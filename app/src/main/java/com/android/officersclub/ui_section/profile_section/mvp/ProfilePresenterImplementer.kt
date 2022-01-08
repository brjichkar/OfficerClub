package com.android.officersclub.ui_section.profile_section.mvp

import com.android.officersclub.R
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateResponse

class ProfilePresenterImplementer(tempView: ProfileMVP.ProfileView): ProfileMVP.ProfilePresenter,
    ProfileMVP.ProfileModel.OnProfileFinishedListener, ProfileMVP.ProfileModel.OnProfileUpdateFinishedListener {
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
}