package com.officersclub.ui_section.home_section.booking_section.details.mvp

import com.officersclub.R
import com.officersclub.ui_section.home_section.booking_section.details.model.DataX
import com.officersclub.ui_section.home_section.booking_section.details.model.DetailsRequest
import com.officersclub.ui_section.home_section.booking_section.details.model.serreq.ServicesRequest

class DetailsPresenterImplementer(tempView: ClubDetailsMVP.DetailsView): ClubDetailsMVP.DetailsPresenter,ClubDetailsMVP.DetailsModel.OnDetailsFinishedListener,
    ClubDetailsMVP.DetailsModel.OnServicesDetailsListener {

    private var mView: ClubDetailsMVP.DetailsView? = tempView
    private var mModel: ClubDetailsMVP.DetailsModel = DetailsModelImplementer()

    override fun attachView(tempView: ClubDetailsMVP.DetailsView) {
        mView=tempView
    }

    override fun destroyView() {
        mView=null
    }

    override fun onDetailsRequest(tempRequest: DetailsRequest) {
        if(mView!=null)
        {
            if(mView!!.isNetworkConnected){
                mModel.processDetails(this,tempRequest)
            }
            else{
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onServicesRequest(tempRequest: ServicesRequest) {
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

    override fun onDetailsFinished(tempResponse: DataX) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onDetailsReceived(tempResponse)
        }
    }

    override fun onDetailsFailure(warnings: String) {
        mView!!.onDetailsFailed()
    }

    override fun onServicesFinished(tempResponse: com.officersclub.ui_section.home_section.booking_section.details.model.serreq.DataX) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onServicesReceived(tempResponse)
        }
    }

    override fun onServicesFailure(warnings: String) {
        mView!!.onDetailsFailed()
      }
}