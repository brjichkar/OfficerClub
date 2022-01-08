package com.android.officersclub.ui_section.login_section

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.databinding.ActivityLoginBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.login_section.model.LoginRequest
import com.android.officersclub.ui_section.login_section.model.LoginResponse
import com.android.officersclub.ui_section.login_section.mvp.LoginMVP
import com.android.officersclub.ui_section.login_section.mvp.LoginPresenterImplementer
import com.android.officersclub.ui_section.otp_section.ActivityOtp

class ActivityLogin : BaseActivity(),LoginMVP.LoginView {
    private lateinit var mActivityLoginBinding: ActivityLoginBinding
    private lateinit var mLoginPresenter:LoginMVP.LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding=DataBindingUtil.setContentView(this, R.layout.activity_login)
        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }
        mActivityLoginBinding.tvInfo.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Solpur's own and most prestigious club - <span style=\"color: #FF0000;\">The Officer's Club Solpur</span>. World class sports facility at a premium location</h6>", Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Solpur's own and most prestigious club - <span style=\"color: #FF0000;\">The Officer's Club Solpur</span>. World class sports facility at a premium location</h6>")
        }
        mLoginPresenter= LoginPresenterImplementer(this)
        mActivityLoginBinding.btnLogin.setOnClickListener {
            if(isDataValid()){
                val req=LoginRequest()
                req.jsondata=LoginRequest().Jsondata()
                req.jsondata!!.mobile=mActivityLoginBinding.etPhone.text.toString()
                req.jsondata!!.setAuthCode("auth_code")
                mLoginPresenter.onLoginButtonClicked(req)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mLoginPresenter.attachView(this)
    }

    override fun onDestroy() {
        mLoginPresenter.destroyView()
        super.onDestroy()
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String?) {
    }

    /**
     *  @Function : isDataValid()
     *  @params   : void
     *  @Return   : Boolean
     * 	@Usage	  : check for validation of UI return true when data is valid and false when validation failed.
     */
    private fun isDataValid():Boolean{
        hideKeyboard()
        return if (mActivityLoginBinding.etPhone.text.toString().isNotEmpty() && mActivityLoginBinding.etPhone.text.toString().length==10)
        {
           true
        } else {
            onError(R.string.empty_error)
            false
        }
    }

    override fun onLoginSuccessful(userDetails: LoginResponse.Data.ResponseData) {
        if(userDetails.mobile!=null && userDetails.profile!=null && userDetails.userId !=null){
            val appPreference=AppPreference(this)
            appPreference.userMobile=userDetails.mobile!!
            appPreference.userProfile=userDetails.profile!!
            appPreference.usersId=userDetails.userId!!

            val mainActIntent = Intent(this, ActivityOtp::class.java)
            startActivity(mainActIntent)
            finish()
        }
        else{
            onError("Invalid data received")
        }
    }

    override fun onLoginFailed() {

    }
}