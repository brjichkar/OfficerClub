package com.officersclub.ui_section.home_section.home_section.mvp

import com.officersclub.R
import com.officersclub.ui_section.base_section.BasePresenter
import com.officersclub.ui_section.base_section.MvpView
import com.officersclub.ui_section.home_section.home_section.model.Data
import com.officersclub.ui_section.home_section.home_section.model.membership.DataX
import com.officersclub.ui_section.profile_section.model.ProfileRequest

class HomePresenterImplementer(tempView: HomeMVP.HomeView): BasePresenter<MvpView>(),
    HomeMVP.HomePresenter,HomeMVP.HomeModel.OnFacilityFinishedListener,
    HomeMVP.HomeModel.OnMembershipFinishedListener, HomeMVP.HomeModel.OnGalleryFinishedListener,
    HomeMVP.HomeModel.OnVideoFinishedListener, HomeMVP.HomeModel.OnServicesListener {

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

    override fun onGalleryRequest(tempRequest: ProfileRequest) {
        if(mView!=null)
        {
            if(mView!!.isNetworkConnected){
                mModel.processGallery(this,tempRequest)
            }
            else{
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onVideosRequest(tempRequest: ProfileRequest) {
        if(mView!=null)
        {
            if(mView!!.isNetworkConnected){
                mModel.processVideo(this,tempRequest)
            }
            else{
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onServicesRequest(tempRequest: ProfileRequest) {
        if(mView!=null)
        {
            if(mView!!.isNetworkConnected){
                mModel.processServices(this,tempRequest)
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

    override fun onGallerySuccess(tempResponse: MutableList<com.officersclub.ui_section.home_section.home_section.model.gallery.DataX>) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onGallerySuccess(tempResponse)
        }
    }

    override fun onGalleryFailure(warnings: String) {
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }

    override fun onVideoSuccess(tempResponse: MutableList<com.officersclub.ui_section.home_section.home_section.model.videos.DataX>) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onVideosSuccess(tempResponse)
        }
    }

    override fun onVideoFailure(warnings: String) {
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }

    override fun onServicesSuccess(tempResponse: MutableList<com.officersclub.ui_section.home_section.booking_section.model.services.DataX>) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onServicesSuccess(tempResponse)
        }
    }

    override fun onServicesFailure(warnings: String) {
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }
}