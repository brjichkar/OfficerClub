package com.android.officersclub.ui_section.home_section.profile_section.family_section.mvp

import com.android.officersclub.ui_section.base_section.MvpView
import com.android.officersclub.ui_section.home_section.profile_section.family_section.model.DataX
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import com.android.officersclub.ui_section.profile_section.model.photo.Data
import java.io.File

class FamilyMVP {
    interface FamilyView : MvpView {
        fun onFamilySuccessful(tempResponse: MutableList<DataX>)
        fun onFamilyFailed()
        fun onProfileImageUploadSuccess(tempResponse: Data)
    }

    interface FamilyPresenter {
        fun attachView(tempView: FamilyView)
        fun destroyView()
        fun onFamilyRequest(tempRequest: ProfileRequest)
        fun onPhotoUpload(userId:String,relativeId: String,imageFile: File)
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

        fun processPhotoUpdate(
            onFinishedListener: OnPhotoUpdateFinishedListener,
            userId:String,relativeId: String,imageFile: File
        )

        interface OnPhotoUpdateFinishedListener {
            fun onPhotoUpdate(tempResponse: Data)
            fun onPhotoUpdateFailure(warnings: String)
        }
    }

}