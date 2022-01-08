package com.android.officersclub.ui_section.home_section.home_section.mvp

import com.android.officersclub.R
import com.android.officersclub.ui_section.base_section.BasePresenter
import com.android.officersclub.ui_section.base_section.MvpView
import com.android.officersclub.ui_section.home_section.home_section.model.Data
import com.android.officersclub.ui_section.home_section.home_section.model.membership.DataX
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest

class HomePresenterImplementer(tempView: HomeMVP.HomeView): BasePresenter<MvpView>(),
    HomeMVP.HomePresenter,HomeMVP.HomeModel.OnFacilityFinishedListener,
    HomeMVP.HomeModel.OnMembershipFinishedListener{

    private var mView: HomeMVP.HomeView? = tempView
    private var mModel: HomeMVP.HomeModel = HomeModelImplementer()

    override fun attachView(tempView: HomeMVP.HomeView) {
        mView=tempView
    }

    override fun destroyView() {
        mView=null
    }

    override fun onFacilityRequest(tempRequest: ProfileRequest) {
        if(mView!=null)
        {
            if(mView!!.isNetworkConnected){
                mModel.processFacility(this,tempRequest)
            }
            else{
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onMembershipRequest(tempRequest: ProfileRequest) {
        if(mView!=null)
        {
            if(mView!!.isNetworkConnected){
                mModel.processMembership(this,tempRequest)
            }
            else{
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onFacilityFinished(tempResponse: Data) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onFacilititySuccessful(tempResponse)
        }
    }

    override fun onFacilityFailure(warnings: String) {
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }

    override fun onMembershipUpdate(tempResponse: DataX) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onMembershipSuccess(tempResponse)
        }
    }

    override fun onMembershipFailure(warnings: String) {
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }
}