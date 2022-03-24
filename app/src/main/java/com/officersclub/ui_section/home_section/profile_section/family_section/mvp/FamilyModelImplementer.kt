package com.officersclub.ui_section.home_section.profile_section.family_section.mvp

import com.officersclub.api_section.ApiClient
import com.officersclub.api_section.ApiInterface
import com.officersclub.ui_section.home_section.profile_section.family_section.model.FamilyDetailsReponse
import com.officersclub.ui_section.profile_section.model.ProfileRequest
import com.officersclub.ui_section.profile_section.model.photo.PhotoResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

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

    override fun processPhotoUpdate(
        onFinishedListener: FamilyMVP.FamilyModel.OnPhotoUpdateFinishedListener,
        userId: String,
        relativeId: String,
        imageFile: File
    ) {
        val imageRequestBody= RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
        val part= MultipartBody.Part.createFormData("profileimage",imageFile.name,imageRequestBody)
        try {
            val call = mApiInterfaceService.sendProfilePicTOServerFamily(part, userId.toInt(),relativeId.toInt())
            call.enqueue(object: Callback<PhotoResponse> {

                override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
                    if(response.code()==200){
                        if(response.body()?.status=="Success"){
                            onFinishedListener.onPhotoUpdate(response.body()?.data!!)
                        }else{
                            onFinishedListener.onPhotoUpdateFailure("")
                        }
                    }
                    else {
                        onFinishedListener.onPhotoUpdateFailure(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                    onFinishedListener.onPhotoUpdateFailure("")
                }

            })
        } catch (e: Exception) {
            onFinishedListener.onPhotoUpdateFailure("")
        }
    }

}