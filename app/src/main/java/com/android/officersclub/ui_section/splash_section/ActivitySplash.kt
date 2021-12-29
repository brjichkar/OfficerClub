package com.android.officersclub.ui_section.splash_section

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.officersclub.ui_section.login_section.ActivityLogin

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moveToNextSection()
    }


    /**
     *  @Function : moveToNextSection()
     *  @params   : void
     *  @Return   : void
     * 	@Usage	  : Decide which screen for to show next.
     */
    private fun moveToNextSection(){
        val mainActIntent = Intent(this, ActivityLogin::class.java)
        startActivity(mainActIntent)
        finish()
    }
}