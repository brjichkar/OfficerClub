package com.android.officersclub.ui_section.profile_section.mvp

import com.android.officersclub.api_section.ApiClient
import com.android.officersclub.api_section.ApiInterface
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileModelImplementer : ProfileMVP.ProfileModel {
    private var mApiInterfaceService: ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)
    override fun processProfile(
        onFinishedListener: ProfileMVP.ProfileModel.OnProfileFinishedListener,
        tempRequest: ProfileRequest
    ) {
        try{
            val call=mApiInterfaceService.processProfile(tempRequest)
            call.enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.Data()!=null){
                            onFinishedListener.onProfileFinished(response.body()?.data!!.data!!)
                        }else{
                            onFinishedListener.onProfileFailure("")
                        }
                    }
                    else {
                        onFinishedListener.onProfileFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    onFinishedListener.onProfileFailure("")
                }

            })

        }catch (e:Exception){
            onFinishedListener.onProfileFailure("")
        }
    }

    override fun processProfileUpdate(
        onFinishedListener: ProfileMVP.ProfileModel.OnProfileUpdateFinishedListener,
        tempRequest: ProfileUpdateRequest
    ) {
        try{
            val call=mApiInterfaceService.processUpdateProfile(tempRequest)
            call.enqueue(object : Callback<ProfileUpdateResponse> {
                override fun onResponse(call: Call<ProfileUpdateResponse>, response: Response<ProfileUpdateResponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.Data()!=null){
                            onFinishedListener.onProfileUpdate(response.body()?.data!!)
                        }else{
                            onFinishedListener.onProfileUpdateFailure("")
                        }
                    }
                    else {
                        onFinishedListener.onProfileUpdateFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<ProfileUpdateResponse>, t: Throwable) {
                    onFinishedListener.onProfileUpdateFailure("")
                }

            })

        }catch (e:Exception){
            onFinishedListener.onProfileUpdateFailure("")
        }
    }
}