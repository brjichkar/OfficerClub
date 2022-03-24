package com.officersclub.ui_section.home_section.booking_section.details.mvp

import com.officersclub.ui_section.base_section.MvpView
import com.officersclub.ui_section.home_section.booking_section.details.model.DataX
import com.officersclub.ui_section.home_section.booking_section.details.model.DetailsRequest
import com.officersclub.ui_section.home_section.booking_section.details.model.serreq.ServicesRequest

class ClubDetailsMVP {

    interface DetailsView : MvpView {
        fun onDetailsReceived(tempResponse: DataX)
        fun onDetailsFailed()

        fun onServicesReceived(tempResponse: com.officersclub.ui_section.home_section.booking_section.details.model.serreq.DataX)
        fun onServicesFailed()
    }

    interface DetailsPresenter {
        fun attachView(tempView: DetailsView)
        fun destroyView()
        fun onDetailsRequest(tempRequest: DetailsRequest)
        fun onServicesRequest(tempRequest: ServicesRequest)
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

        fun processServices(
            onServicesDetailsListener: OnServicesDetailsListener,
            tempRequest: ServicesRequest
        )

        interface OnServicesDetailsListener {
            fun onServicesFinished(tempResponse: com.officersclub.ui_section.home_section.booking_section.details.model.serreq.DataX)
            fun onServicesFailure(warnings: String)
        }
    }
}