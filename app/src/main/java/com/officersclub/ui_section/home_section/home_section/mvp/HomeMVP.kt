package com.officersclub.ui_section.home_section.home_section.mvp

import com.officersclub.ui_section.base_section.MvpView
import com.officersclub.ui_section.home_section.home_section.model.Data
import com.officersclub.ui_section.home_section.home_section.model.membership.DataX
import com.officersclub.ui_section.profile_section.model.ProfileRequest

class HomeMVP {
    interface HomeView : MvpView {
        fun onFacilititySuccessful(tempResponse: Data)
        fun onFacilitityFailed()
        fun onMembershipSuccess(tempResponse:DataX)
        fun onGallerySuccess(tempResponse:MutableList<com.officersclub.ui_section.home_section.home_section.model.gallery.DataX>)
        fun onVideosSuccess(tempResponse:MutableList<com.officersclub.ui_section.home_section.home_section.model.videos.DataX>)
        fun onServicesSuccess(tempResponse:MutableList<com.officersclub.ui_section.home_section.booking_section.model.services.DataX>)
    }

    interface HomePresenter {
        fun attachView(tempView: HomeView)
        fun destroyView()
        fun onFacilityRequest(tempRequest: ProfileRequest)
        fun onMembershipRequest(tempRequest: ProfileRequest)
        fun onGalleryRequest(tempRequest: ProfileRequest)
        fun onVideosRequest(tempRequest: ProfileRequest)
        fun onServicesRequest(tempRequest: ProfileRequest)
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

        fun processGallery(
            onGalleryFinishedListener: OnGalleryFinishedListener,
            tempRequest: ProfileRequest
        )

        interface OnGalleryFinishedListener {
            fun onGallerySuccess(tempResponse: MutableList<com.officersclub.ui_section.home_section.home_section.model.gallery.DataX>)
            fun onGalleryFailure(warnings: String)
        }

        fun processVideo(
            onVideoFinishedListener: OnVideoFinishedListener,
            tempRequest: ProfileRequest
        )

        interface OnVideoFinishedListener {
            fun onVideoSuccess(tempResponse:MutableList<com.officersclub.ui_section.home_section.home_section.model.videos.DataX>)
            fun onVideoFailure(warnings: String)
        }

        fun processServices(
            onServiceListener: OnServicesListener,
            tempRequest: ProfileRequest
        )

        interface OnServicesListener {
            fun onServicesSuccess(tempResponse:MutableList<com.officersclub.ui_section.home_section.booking_section.model.services.DataX>)
            fun onServicesFailure(warnings: String)
        }
    }

}