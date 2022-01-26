package com.android.officersclub.ui_section.login_section.mvp

import com.android.officersclub.R
import com.android.officersclub.ui_section.login_section.model.LoginRequest
import com.android.officersclub.ui_section.login_section.model.LoginResponse

class LoginPresenterImplementer(loginView: LoginMVP.LoginView) : LoginMVP.LoginPresenter,LoginMVP.LoginModel.OnFinishedListener {
    private var mloginView:LoginMVP.LoginView? = loginView
    private var mloginModel:LoginMVP.LoginModel = LoginModelImplementer()

    override fun attachView(loginView: LoginMVP.LoginView) {
        mloginView=loginView
    }

    override fun destroyView() {
        mloginView=null
    }

    override fun onLoginButtonClicked(loginRequest: LoginRequest) {
        if(mloginView!=null)
        {
            mloginView!!.hideKeyboard()
            if(mloginView!!.isNetworkConnected){
                mloginView!!.showLoading()
                mloginModel.processLogin(this,loginRequest)
            }
            else{
                mloginView!!.hideLoading()
                mloginView!!.onError(R.string.not_connected_to_internet)
            }

        }
    }

    override fun onFinished(receivedUserDetails: LoginResponse.ResponseData) {
        if(mloginView!=null){
            mloginView!!.hideLoading()
            mloginView!!.onLoginSuccessful(receivedUserDetails)
        }
    }

    override fun onFailure(warnings: String) {
        mloginView!!.hideLoading()
        if(warnings == ""){
            mloginView!!.onError(R.string.some_error)
        }else{
            mloginView!!.onError(warnings)
        }
    }
}