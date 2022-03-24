package com.officersclub.ui_section.login_section.mvp

import com.officersclub.api_section.ApiClient
import com.officersclub.api_section.ApiInterface
import com.officersclub.ui_section.login_section.model.LoginRequest
import com.officersclub.ui_section.login_section.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModelImplementer : LoginMVP.LoginModel {
    private var mApiInterfaceService: ApiInterface = ApiClient().getClient().create(ApiInterface::class.java)
    override fun processLogin(
        onFinishedListener: LoginMVP.LoginModel.OnFinishedListener,
        loginRequest: LoginRequest
    ) {
        try{
            val call=mApiInterfaceService.processLogin(loginRequest)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>)
                {
                    if(response.code()==200){
                        if(response.body()?.status=="Success" && response.body()!=null){
                            onFinishedListener.onFinished(response.body()?.data!!)
                        }else{
                            onFinishedListener.onFailure("")
                        }
                    }
                    else {
                        onFinishedListener.onFailure(response.errorBody()!!.string())
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onFinishedListener.onFailure("")
                }
            })

        }catch (e:Exception){
            onFinishedListener.onFailure("")
        }
    }
}