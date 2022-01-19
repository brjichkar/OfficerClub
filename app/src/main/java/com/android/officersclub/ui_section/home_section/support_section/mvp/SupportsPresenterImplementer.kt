package com.android.officersclub.ui_section.home_section.support_section.mvp

import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.support_section.model.req.RatingRequest
import com.android.officersclub.ui_section.home_section.support_section.model.resps.RatingResponses

class SupportsPresenterImplementer(tempView:  SupportsMVP.SupportsView): SupportsMVP.SupportsPresenter,
    SupportsMVP.SupportsModel.OnSupportsinishedListener {

    private var mView: SupportsMVP.SupportsView? = tempView
    private var mModel: SupportsMVP.SupportsModel = SupportsModelImplementer()

    override fun attachView(tempView: SupportsMVP.SupportsView) {
        mView=tempView
    }

    override fun destroyView() {
        mView=null
    }

    override fun onSupportsRequest(tempRequest: RatingRequest) {
        if(mView!=null)
        {
            if(mView!!.isNetworkConnected){
                mView!!.showLoading()
                mModel.processSupports(this,tempRequest)
            }
            else{
                mView!!.onError(R.string.not_connected_to_internet)
            }
        }
    }

    override fun onSupportsFinished(tempResponse: RatingResponses) {
        if(mView!=null){
            mView!!.hideLoading()
            mView!!.onSupportsSuccessful(tempResponse)
        }
    }

    override fun onSupportsFailure(warnings: String) {
        mView!!.hideLoading()
        if(warnings == ""){
            mView!!.onError(R.string.some_error)
        }else{
            mView!!.onError(warnings)
        }
    }
}