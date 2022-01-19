package com.android.officersclub.ui_section.home_section.booking_section.details.mvp

import com.android.officersclub.api_section.ApiClient
import com.android.officersclub.api_section.ApiInterface
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DetailsRequest
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DetailsResponse
import com.android.officersclub.ui_section.home_section.booking_section.details.model.serreq.ServicesDetailsResponse
import com.android.officersclub.ui_section.home_section.booking_section.details.model.serreq.ServicesRequest
import com.android.officersclub.ui_section.home_section.home_section.model.FacilityResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsModelImplementer: ClubDetailsMVP.DetailsModel {
    private var mApiInterfaceService: ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)

    override fun processDetails(
        onFinishedListener: ClubDetailsMVP.DetailsModel.OnDetailsFinishedListener,
        tempRequest: DetailsRequest
    ) {
        try{
            val call=mApiInterfaceService.processDetails(tempRequest)
            call.enqueue(object : Callback<DetailsResponse> {
                override fun onResponse(call: Call<DetailsResponse>, response: Response<DetailsResponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.data!=null){
                            onFinishedListener.onDetailsFinished(response.body()?.data!!.data[0])
                        }else{
                            onFinishedListener.onDetailsFailure("")
                        }
                    }
                    else {
                        onFinishedListener.onDetailsFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {
                    onFinishedListener.onDetailsFailure("")
                }

            })

        }catch (e:Exception){
            onFinishedListener.onDetailsFailure("")
        }
    }

    override fun processServices(
        onServicesDetailsListener: ClubDetailsMVP.DetailsModel.OnServicesDetailsListener,
        tempRequest: ServicesRequest
    ) {
        try{
            val call=mApiInterfaceService.processServicesDetails(tempRequest)
            call.enqueue(object : Callback<ServicesDetailsResponse> {
                override fun onResponse(call: Call<ServicesDetailsResponse>, response: Response<ServicesDetailsResponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.data!=null){
                            onServicesDetailsListener.onServicesFinished(response.body()?.data!!.data[0])
                        }else{
                            onServicesDetailsListener.onServicesFailure("")
                        }
                    }
                    else {
                        onServicesDetailsListener.onServicesFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<ServicesDetailsResponse>, t: Throwable) {
                    onServicesDetailsListener.onServicesFailure("")
                }

            })

        }catch (e:Exception){
            onServicesDetailsListener.onServicesFailure("")
        }
    }


}