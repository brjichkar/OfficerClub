package com.android.officersclub.ui_section.home_section.booking_section.details.mvp

import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DataX
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DetailsRequest

class DetailsPresenterImplementer(tempView: ClubDetailsMVP.DetailsView): ClubDetailsMVP.DetailsPresenter,ClubDetailsMVP.DetailsModel.OnDetailsFinishedListener {

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

    override fun onDetailsFinished(tempResponse: DataX) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onDetailsReceived(tempResponse)
        }
    }

    override fun onDetailsFailure(warnings: String) {
        mView!!.onDetailsFailed()
    }
}