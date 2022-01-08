package com.android.officersclub.ui_section.home_section.home_section.mvp

import com.android.officersclub.ui_section.base_section.MvpView
import com.android.officersclub.ui_section.home_section.home_section.model.Data
import com.android.officersclub.ui_section.home_section.home_section.model.membership.DataX
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest

class HomeMVP {
    interface HomeView : MvpView {
        fun onFacilititySuccessful(tempResponse: Data)
        fun onFacilitityFailed()
        fun onMembershipSuccess(tempResponse:DataX)
    }

    interface HomePresenter {
        fun attachView(tempView: HomeView)
        fun destroyView()
        fun onFacilityRequest(tempRequest: ProfileRequest)
        fun onMembershipRequest(tempRequest: ProfileRequest)
    }

    interface HomeModel {
        fun processFacility(
            onFinishedListener: OnFacilityFinishedListener,
            tempRequest: ProfileRequest
        )

        interface OnFacilityFinishedListener {
            fun onFacilityFinished(tempResponse: Data)
            fun onFacilityFailure(warnings: String)
        }

        fun processMembership(
            onMembershipFinishedListener: OnMembershipFinishedListener,
            tempRequest: ProfileRequest
        )

        interface OnMembershipFinishedListener {
            fun onMembershipUpdate(tempResponse: DataX)
            fun onMembershipFailure(warnings: String)
        }
    }

}