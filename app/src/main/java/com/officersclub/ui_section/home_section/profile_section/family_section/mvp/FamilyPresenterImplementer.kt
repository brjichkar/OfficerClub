package com.officersclub.ui_section.home_section.profile_section.family_section.mvp

import com.officersclub.R
import com.officersclub.ui_section.home_section.profile_section.family_section.model.DataX
import com.officersclub.ui_section.profile_section.model.ProfileRequest
import com.officersclub.ui_section.profile_section.model.photo.Data
import java.io.File

class FamilyPresenterImplementer(tempView: FamilyMVP.FamilyView): FamilyMVP.FamilyPresenter, FamilyMVP.FamilyModel.OnFamilyFinishedListener,
    FamilyMVP.FamilyModel.OnPhotoUpdateFinishedListener {
    private var mView: FamilyMVP.FamilyView? = tempView
    private var mModel: FamilyMVP.FamilyModel = FamilyModelImplementer()

    override fun attachView(tempView: FamilyMVP.FamilyView) {
        mView=tempView
    }

    override fun destroyView() {
        mView=null
    }

    override fun onFamilyRequest(tempRequest: ProfileRequest) {
        if(mView!=null)
        {
            if(mView!!.isNetworkConnected){
                mView!!.showLoading()
                mModel.processFamily(this,tempRequest)
            }
            else{
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onPhotoUpload(userId: String,relativeId: String, imageFile: File) {
        if(mView!=null)
        {
            mView!!.hideKeyboard()
            if(mView!!.isNetworkConnected){
                mView!!.showLoading()
                mModel.processPhotoUpdate(this,userId,relativeId,imageFile)
            }
            else{
                mView!!.hideLoading()
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onFamilyFinished(tempResponse: MutableList<DataX>) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onFamilySuccessful(tempResponse)
        }
    }

    override fun onFamilyFailure(warnings: String) {
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