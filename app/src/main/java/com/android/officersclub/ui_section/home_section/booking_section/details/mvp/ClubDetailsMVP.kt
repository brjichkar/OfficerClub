package com.android.officersclub.ui_section.home_section.booking_section.details.mvp

import com.android.officersclub.ui_section.base_section.MvpView
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DataX
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DetailsRequest

class ClubDetailsMVP {

    interface DetailsView : MvpView {
        fun onDetailsReceived(tempResponse: DataX)
        fun onDetailsFailed()
    }

    interface DetailsPresenter {
        fun attachView(tempView: DetailsView)
        fun destroyView()
        fun onDetailsRequest(tempRequest: DetailsRequest)
    }

    interface DetailsModel {
        fun processDetails(
            onFinishedListener: OnDetailsFinishedListener,
            tempRequest: DetailsRequest
        )

        interface OnDetailsFinishedListener {
            fun onDetailsFinished(tempResponse: DataX)
            fun onDetailsFailure(warnings: String)
        }
    }
}