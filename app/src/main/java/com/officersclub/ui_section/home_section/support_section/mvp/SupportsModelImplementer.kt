package com.officersclub.ui_section.home_section.support_section.mvp

import com.officersclub.api_section.ApiClient
import com.officersclub.api_section.ApiInterface
import com.officersclub.ui_section.home_section.support_section.model.req.RatingRequest
import com.officersclub.ui_section.home_section.support_section.model.resps.RatingResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupportsModelImplementer: SupportsMVP.SupportsModel {
    private var mApiInterfaceService: ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)

    override fun processSupports(
        onSupportsinishedListener: SupportsMVP.SupportsModel.OnSupportsinishedListener,
        tempRequest: RatingRequest
    ) {
        try{
            val call=mApiInterfaceService.processRatingsSubmit(tempRequest)
            call.enqueue(object : Callback<RatingResponses> {
                override fun onResponse(call: Call<RatingResponses>, response: Response<RatingResponses>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.data!=null){
                            onSupportsinishedListener.onSupportsFinished(response.body()!!)
                        }else{
                            onSupportsinishedListener.onSupportsFailure("")
                        }
                    }
                    else {
                        onSupportsinishedListener.onSupportsFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<RatingResponses>, t: Throwable) {
                    onSupportsinishedListener.onSupportsFailure("")
                }

            })

        }catch (e:Exception){
            onSupportsinishedListener.onSupportsFailure("")
        }
    }
}