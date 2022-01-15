package com.android.officersclub.ui_section.home_section.home_section.mvp

import com.android.officersclub.api_section.ApiClient
import com.android.officersclub.api_section.ApiInterface
import com.android.officersclub.ui_section.home_section.home_section.model.FacilityResponse
import com.android.officersclub.ui_section.home_section.home_section.model.gallery.GalleryResponse
import com.android.officersclub.ui_section.home_section.home_section.model.membership.MembershipModel
import com.android.officersclub.ui_section.home_section.home_section.model.videos.VideosResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModelImplementer: HomeMVP.HomeModel {
    private var mApiInterfaceService: ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)

    override fun processFacility(
        onFinishedListener: HomeMVP.HomeModel.OnFacilityFinishedListener,
        tempRequest: ProfileRequest
    ) {
        try{
            val call=mApiInterfaceService.processFacility(tempRequest)
            call.enqueue(object : Callback<FacilityResponse> {
                override fun onResponse(call: Call<FacilityResponse>, response: Response<FacilityResponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.data!=null){
                            onFinishedListener.onFacilityFinished(response.body()?.data!!)
                        }else{
                            onFinishedListener.onFacilityFailure("")
                        }
                    }
                    else {
                        onFinishedListener.onFacilityFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<FacilityResponse>, t: Throwable) {
                    onFinishedListener.onFacilityFailure("")
                }

            })

        }catch (e:Exception){
            onFinishedListener.onFacilityFailure("")
        }
    }

    override fun processMembership(
        onMembershipFinishedListener: HomeMVP.HomeModel.OnMembershipFinishedListener,
        tempRequest: ProfileRequest
    ) {
        try{
            val call=mApiInterfaceService.processMembership(tempRequest)
            call.enqueue(object : Callback<MembershipModel> {
                override fun onResponse(call: Call<MembershipModel>, response: Response<MembershipModel>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.data!=null){
                            onMembershipFinishedListener.onMembershipUpdate(response.body()?.data!!.data)
                        }else{
                            onMembershipFinishedListener.onMembershipFailure("")
                        }
                    }
                    else {
                        onMembershipFinishedListener.onMembershipFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<MembershipModel>, t: Throwable) {
                    onMembershipFinishedListener.onMembershipFailure("")
                }

            })

        }catch (e:Exception){
            onMembershipFinishedListener.onMembershipFailure("")
        }
    }

    override fun processGallery(
        onGalleryFinishedListener: HomeMVP.HomeModel.OnGalleryFinishedListener,
        tempRequest: ProfileRequest
    ) {
        try{
            val call=mApiInterfaceService.processGallery(tempRequest)
            call.enqueue(object : Callback<GalleryResponse> {
                override fun onResponse(call: Call<GalleryResponse>, response: Response<GalleryResponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.data!=null){
                            onGalleryFinishedListener.onGallerySuccess(response.body()?.data!!.data)
                        }else{
                            onGalleryFinishedListener.onGalleryFailure("")
                        }
                    }
                    else {
                        onGalleryFinishedListener.onGalleryFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<GalleryResponse>, t: Throwable) {
                    onGalleryFinishedListener.onGalleryFailure("")
                }

            })

        }catch (e:Exception){
            onGalleryFinishedListener.onGalleryFailure("")
        }
    }

    override fun processVideo(
        onVideoFinishedListener: HomeMVP.HomeModel.OnVideoFinishedListener,
        tempRequest: ProfileRequest
    ) {
        try{
            val call=mApiInterfaceService.processVideos(tempRequest)
            call.enqueue(object : Callback<VideosResponse> {
                override fun onResponse(call: Call<VideosResponse>, response: Response<VideosResponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()?.data!=null){
                            onVideoFinishedListener.onVideoSuccess(response.body()?.data!!.data)
                        }else{
                            onVideoFinishedListener.onVideoFailure("")
                        }
                    }
                    else {
                        onVideoFinishedListener.onVideoFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<VideosResponse>, t: Throwable) {
                    onVideoFinishedListener.onVideoFailure("")
                }

            })

        }catch (e:Exception){
            onVideoFinishedListener.onVideoFailure("")
        }
    }
}