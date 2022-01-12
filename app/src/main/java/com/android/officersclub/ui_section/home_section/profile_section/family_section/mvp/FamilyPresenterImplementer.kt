package com.android.officersclub.ui_section.home_section.profile_section.family_section.mvp

import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.profile_section.family_section.model.DataX
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest

class FamilyPresenterImplementer(tempView: FamilyMVP.FamilyView): FamilyMVP.FamilyPresenter, FamilyMVP.FamilyModel.OnFamilyFinishedListener
{
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
}