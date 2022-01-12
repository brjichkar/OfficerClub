package com.android.officersclub.ui_section.home_section.profile_section.family_section.mvp

import com.android.officersclub.api_section.ApiClient
import com.android.officersclub.api_section.ApiInterface
import com.android.officersclub.ui_section.home_section.profile_section.family_section.model.FamilyDetailsReponse
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FamilyModelImplementer: FamilyMVP.FamilyModel {
    private var mApiInterfaceService: ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)
    override fun processFamily(
        onFinishedListener: FamilyMVP.FamilyModel.OnFamilyFinishedListener,
        tempRequest: ProfileRequest
    ) {
        try{
            val call=mApiInterfaceService.processFamily(tempRequest)
            call.enqueue(object : Callback<FamilyDetailsReponse> {
                override fun onResponse(call: Call<FamilyDetailsReponse>, response: Response<FamilyDetailsReponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.data!=null){
                            onFinishedListener.onFamilyFinished(response.body()?.data!!.data)
                        }else{
                            onFinishedListener.onFamilyFailure("")
                        }
                    }
                    else {
                        onFinishedListener.onFamilyFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<FamilyDetailsReponse>, t: Throwable) {
                    onFinishedListener.onFamilyFailure("")
                }

            })

        }catch (e:Exception){
            onFinishedListener.onFamilyFailure("")
        }
    }
}