package com.officersclub.ui_section.login_section

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.databinding.DataBindingUtil
import com.officersclub.R
import com.officersclub.app_preferences.AppPreference
import com.officersclub.databinding.ActivityLoginBinding

import com.officersclub.ui_section.base_section.BaseActivity
import com.officersclub.ui_section.home_section.ActivityHome
import com.officersclub.ui_section.login_section.model.LoginRequest
import com.officersclub.ui_section.login_section.model.LoginResponse
import com.officersclub.ui_section.login_section.mvp.LoginMVP
import com.officersclub.ui_section.login_section.mvp.LoginPresenterImplementer
import com.officersclub.ui_section.profile_section.ActivityProfile

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
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Solapur's own and most prestigious club - <span style=\"color: #FF0000;\">The Officer's Club Solapur</span>. World class sports facility at a premium location</h6>", Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml("<h7 style=\"color: #FFFFFF;\">Solapur's own and most prestigious club - <span style=\"color: #FF0000;\">The Officer's Club Solapur</span>. World class sports facility at a premium location</h6>")
        }
        mLoginPresenter= LoginPresenterImplementer(this)
        mActivityLoginBinding.btnLogin.setOnClickListener {
            //throw RuntimeException("Test Crash"); // Force a crash
            if(isDataValid()){
                val req=LoginRequest()
                req.jsondata=LoginRequest().Jsondata()
                req.jsondata!!.mobile=mActivityLoginBinding.etPhone.text.toString()
                req.jsondata!!.setAuthCode(mActivityLoginBinding.etPassword.text.toString())
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
        return if (mActivityLoginBinding.etPhone.text.toString().isNotEmpty() && mActivityLoginBinding.etPhone.text.toString().length==10) {
            if(mActivityLoginBinding.etPassword.text.toString().isNotEmpty()){
                true
            } else{
                onError("Please enter password")
                false
            }
        } else {
            onError(R.string.empty_error)
            false
        }
    }

    override fun onLoginSuccessful(userDetails: LoginResponse.ResponseData) {
        if(userDetails.mobile!=null && userDetails.profile!=null && userDetails.userId !=null){
            val appPreference=AppPreference(this)
            appPreference.userMobile=userDetails.mobile!!
            appPreference.userProfile=userDetails.profile!!
            appPreference.usersId=userDetails.userId!!

            appPreference.isUserLoggedIn=true
            if(appPreference.userProfile == "1"){
                val mainActIntent = Intent(this, ActivityHome::class.java)
                startActivity(mainActIntent)
                finish()
            }
            else{
                val mainActIntent = Intent(this, ActivityProfile::class.java)
                mainActIntent.putExtra("is_profile_complete",false)
                startActivity(mainActIntent)
                finish()
            }
        }
        else{
            onError("Invalid data received")
        }
    }

    override fun onLoginFailed() {

    }
}