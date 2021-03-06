package com.officersclub.ui_section.splash_section

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.officersclub.app_preferences.AppPreference
import com.officersclub.ui_section.home_section.ActivityHome
import com.officersclub.ui_section.login_section.ActivityLogin
import com.officersclub.ui_section.profile_section.ActivityProfile

class ActivitySplash : AppCompatActivity() {
    private lateinit var mAppPreference: AppPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAppPreference= AppPreference(this)
        moveToNextSection()
    }


    /**
     *  @Function : moveToNextSection()
     *  @params   : void
     *  @Return   : void
     * 	@Usage	  : Decide which screen for to show next.
     */
    private fun moveToNextSection(){
        if(mAppPreference.isUserLoggedIn){
            if(mAppPreference.userProfile == "1"){
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
            val mainActIntent = Intent(this, ActivityLogin::class.java)
            startActivity(mainActIntent)
            finish()
        }
    }
}