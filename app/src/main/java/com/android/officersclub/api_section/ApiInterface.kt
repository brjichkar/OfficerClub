/**
 * Copyright (C) 2019 GoVida
 * @Interface :  ApiInterface
 * @Usage : This interface is used for accumulating all the api calls
 * @Author : 1276
 */

package com.android.officersclub.api_section

import com.android.officersclub.ui_section.home_section.booking_section.details.model.DetailsRequest
import com.android.officersclub.ui_section.home_section.booking_section.details.model.DetailsResponse
import com.android.officersclub.ui_section.home_section.home_section.model.FacilityResponse
import com.android.officersclub.ui_section.home_section.home_section.model.gallery.GalleryResponse
import com.android.officersclub.ui_section.home_section.home_section.model.membership.MembershipModel
import com.android.officersclub.ui_section.home_section.home_section.model.videos.VideosResponse
import com.android.officersclub.ui_section.home_section.profile_section.family_section.model.FamilyDetailsReponse
import com.android.officersclub.ui_section.login_section.model.LoginRequest
import com.android.officersclub.ui_section.login_section.model.LoginResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileResponse
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateRequest
import com.android.officersclub.ui_section.profile_section.model.ProfileUpdateResponse
import com.android.officersclub.ui_section.profile_section.model.photo.PhotoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("Login_check")
    fun processLogin(@Body requests: LoginRequest): Call<LoginResponse>

    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("View_profile")
    fun processProfile(@Body requests: ProfileRequest): Call<ProfileResponse>

    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("Update_profile")
    fun processUpdateProfile(@Body requests: ProfileUpdateRequest): Call<ProfileUpdateResponse>

    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("Facility")
    fun processFacility(@Body requests: ProfileRequest): Call<FacilityResponse>

    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("Membership_Details")
    fun processMembership(@Body requests: ProfileRequest): Call<MembershipModel>

    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("Family_Details")
    fun processFamily(@Body requests: ProfileRequest): Call<FamilyDetailsReponse>


    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("Gallery")
    fun processGallery(@Body requests: ProfileRequest): Call<GalleryResponse>

    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("Video")
    fun processVideos(@Body requests: ProfileRequest): Call<VideosResponse>

    /**
     *  @Function : sendProfilePicTOServer()
     *  @params   : @Header("Authorization") authHeader:String,@Part imgFile: MultipartBody.Part
     *  @Return   : ProfilePictureResponse
     * 	@Usage	  : Upload user profile pic to server
     *  @Author   : 1276
     */
    @Multipart
    @POST("Image/ProfileImage")
    fun sendProfilePicTOServer(@Part profileimage: MultipartBody.Part, @Part("user_id") name: Int): Call<PhotoResponse>


    @Multipart
    @POST("Image/FamilyImage")
    fun sendProfilePicTOServerFamily(@Part profileimage: MultipartBody.Part, @Part("user_id") name:Int,@Part("user_relative_id") user_relative_id:Int): Call<PhotoResponse>

    @Headers(value = [
        "Accept: application/json",
        "Content-type:application/json"]
    )
    @POST("Facility_Detail")
    fun processDetails(@Body requests: DetailsRequest): Call<DetailsResponse>

}


