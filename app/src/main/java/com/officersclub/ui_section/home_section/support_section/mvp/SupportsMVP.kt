package com.officersclub.ui_section.home_section.support_section.mvp

import com.officersclub.ui_section.base_section.MvpView
import com.officersclub.ui_section.home_section.support_section.model.req.RatingRequest
import com.officersclub.ui_section.home_section.support_section.model.resps.RatingResponses

class SupportsMVP {
    interface SupportsView : MvpView {
        fun onSupportsSuccessful(tempResponse: RatingResponses)
    }

    interface SupportsPresenter {
        fun attachView(tempView: SupportsView)
        fun destroyView()
        fun onSupportsRequest(tempRequest: RatingRequest)
    }

    interface SupportsModel {
        fun processSupports(
            onSupportsinishedListener: OnSupportsinishedListener,
            tempRequest: RatingRequest
        )

        interface OnSupportsinishedListener {
            fun onSupportsFinished(tempResponse:RatingResponses)
            fun onSupportsFailure(warnings: String)
        }

    }

}