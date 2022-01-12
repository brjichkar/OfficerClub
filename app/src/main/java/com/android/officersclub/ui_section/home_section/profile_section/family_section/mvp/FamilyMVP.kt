package com.android.officersclub.ui_section.home_section.profile_section.family_section.mvp

import com.android.officersclub.ui_section.base_section.MvpView
import com.android.officersclub.ui_section.home_section.profile_section.family_section.model.DataX
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest

class FamilyMVP {
    interface FamilyView : MvpView {
        fun onFamilySuccessful(tempResponse: MutableList<DataX>)
        fun onFamilyFailed()
    }

    interface FamilyPresenter {
        fun attachView(tempView: FamilyView)
        fun destroyView()
        fun onFamilyRequest(tempRequest: ProfileRequest)
    }

    interface FamilyModel {
        fun processFamily(
            onFinishedListener: OnFamilyFinishedListener,
            tempRequest: ProfileRequest
        )

        interface OnFamilyFinishedListener {
            fun onFamilyFinished(tempResponse: MutableList<DataX>)
            fun onFamilyFailure(warnings: String)
        }

    }

}