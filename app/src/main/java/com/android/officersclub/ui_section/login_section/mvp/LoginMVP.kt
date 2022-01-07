/**
 * @Class : LoginMVP
 * @Usage : This class is used for providing MVP functionality to Login Activity
 * @Author : 1276
 */

package com.android.officersclub.ui_section.login_section.mvp

import com.android.officersclub.api_section.ApiInterface
import com.android.officersclub.ui_section.base_section.MvpView
import com.android.officersclub.ui_section.login_section.model.LoginRequest
import com.android.officersclub.ui_section.login_section.model.LoginResponse


class LoginMVP {
    /**
     * @Interface : LoginView
     * @Usage : This interface is used for managing UI related functionality
     * @Author : 1276
     */

    interface LoginView : MvpView {
        // usage : function will be used when user login is successful.
        fun onLoginSuccessful(userDetails: LoginResponse.Data.ResponseData)

        // usage : function will be used when user registration is failed.
        fun onLoginFailed()
    }

    /**
     * @Interface : LoginPresenter
     * @Usage : This interface is used for managing communication between model and view
     * @Author : 1276
     */
    interface LoginPresenter {
        fun attachView(loginView: LoginView)
        fun destroyView()

        // usage : initiate process of user login
        fun onLoginButtonClicked(loginRequest: LoginRequest)
    }

    /**
     * @Interface : LoginModel
     * @Usage : This interface is used for managing data for Activity either from api or db
     * @Author : 1276
     */
    interface LoginModel {
        // usage : validate user with server
        fun processLogin(
            onFinishedListener: OnFinishedListener,
            loginRequest: LoginRequest
        )

        interface OnFinishedListener {
            fun onFinished(receivedUserDetails: LoginResponse.Data.ResponseData)
            fun onFailure(warnings: String)
        }
    }

}