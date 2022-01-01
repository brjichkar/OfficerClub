package com.android.officersclub.ui_section.profile_section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.officersclub.R
import com.android.officersclub.databinding.ActivityProfileBinding
import com.android.officersclub.ui_section.base_section.BaseActivity
import java.util.*
import com.android.officersclub.MainActivity
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog


class ActivityProfile : BaseActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var mActivityProfileBinding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityProfileBinding=DataBindingUtil.setContentView(this,R.layout.activity_profile)
        if(supportActionBar !=null){
            supportActionBar!!.hide()
        }
        mActivityProfileBinding.etBday.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                this,
                now[Calendar.YEAR],  // Initial year selection
                now[Calendar.MONTH],  // Initial month selection
                now[Calendar.DAY_OF_MONTH] // Inital day selection
            )
            dpd.show(supportFragmentManager, "Datepickerdialog")
        }

        mActivityProfileBinding.ivUserImg.setOnClickListener {
            
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        mActivityProfileBinding.etBday.setText(""+dayOfMonth+"/"+(monthOfYear+1)+"/"+year)
        //showMessage("You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year)
    }
}