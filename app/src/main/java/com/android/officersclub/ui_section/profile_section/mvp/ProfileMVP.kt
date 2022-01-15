/**
 * @Class : LoginMVP
 * @Usage : This class is used for providing MVP functionality to Login Activity
 * @Author : 1276
 */

package com.android.officersclub.ui_section.profile_section.mvp

import com.android.officersclub.ui_section.base_section.MvpView
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateResponse
import com.android.officersclub.ui_section.profile_section.model.photo.Data
import java.io.File


class ProfileMVP {
    interface ProfileView : MvpView {
        fun onProfileSuccessful(userDetails: ProfileResponse.Data.ProfileData)
        fun onProfileFailed()
        fun onProfileUpdateSuccessful(tempResponse: ProfileUpdateResponse.Data)
        fun onProfileImageUploadSuccess(tempResponse: Data)
    }

    interface ProfilePresenter {
        fun attachView(tempView: ProfileView)
        fun destroyView()
        fun onProfileButtonClicked(tempRequest: ProfileRequest)
        fun onProfileUpdate(tempRequest: ProfileUpdateRequest)
        fun onPhotoUpload(userId:String,imageFile: File)
    }

    interface ProfileModel {
        fun processProfile(
            onFinishedListener: OnProfileFinishedListener,
            tempRequest: ProfileRequest
        )

        interface OnProfileFinishedListener {
            fun onProfileFinished(tempResponse: ProfileResponse.Data.ProfileData)
            fun onProfileFailure(warnings: String)
        }

        fun processProfileUpdate(
            onFinishedListener: OnProfileUpdateFinishedListener,
            tempRequest: ProfileUpdateRequest
        )

        interface OnProfileUpdateFinishedListener {
            fun onProfileUpdate(tempResponse: ProfileUpdateResponse.Data)
            fun onProfileUpdateFailure(warnings: String)
        }

        fun processPhotoUpdate(
            onFinishedListener: OnPhotoUpdateFinishedListener,
            userId:String,imageFile: File
        )

        interface OnPhotoUpdateFinishedListener {
            fun onPhotoUpdate(tempResponse: Data)
            fun onPhotoUpdateFailure(warnings: String)
        }
    }

}