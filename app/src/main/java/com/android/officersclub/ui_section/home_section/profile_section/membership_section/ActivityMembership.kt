package com.android.officersclub.ui_section.home_section.profile_section.membership_section

import android.app.AlertDialog
import android.os.Bundle
import com.android.officersclub.R
import com.android.officersclub.ui_section.base_section.BaseActivity
import com.android.officersclub.ui_section.home_section.home_section.model.Data
import com.android.officersclub.ui_section.home_section.home_section.model.membership.DataX
import com.android.officersclub.ui_section.home_section.home_section.mvp.HomeMVP
import kotlinx.android.synthetic.main.layout_row_membership.*
import android.widget.ArrayAdapter
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.android.officersclub.app_preferences.AppPreference
import com.android.officersclub.ui_section.home_section.home_section.mvp.HomePresenterImplementer
import com.android.officersclub.ui_section.profile_section.model.ProfileRequest
import kotlinx.android.synthetic.main.latout_list_in_alert.*

class ActivityMembership : BaseActivity() , HomeMVP.HomeView {
    private lateinit var mPresenter: HomeMVP.HomePresenter
    private lateinit var mAppPreference: AppPreference
    private var mList: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        mPresenter= HomePresenterImplementer(this)
        mAppPreference= AppPreference(this)
        val req = ProfileRequest()
        req.jsondata = ProfileRequest().Jsondata()
        req.jsondata!!.userId = mAppPreference.usersId
        mPresenter.onMembershipRequest(req)
        btn_view_more.setOnClickListener {
            val enrolledItems = arrayOfNulls<String>(mList.size)
            for(i in mList.indices){
                enrolledItems[i] = mList[i]
            }
            openDialog(enrolledItems)
        }
    }
    override fun onResume() {
        super.onResume()
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        mPresenter.destroyView()
        super.onDestroy()
    }
    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String?) {
    }

    override fun onFacilititySuccessful(tempResponse: Data) {
    }

    override fun onFacilitityFailed() {
    }

    override fun onMembershipSuccess(tempResponse: DataX) {
        tv_start_end.text = tempResponse.membership_date
        tv_end_date.text = tempResponse.end_date
        tv_enrolled.text = ""+tempResponse.enrolled
        tv_members.text = tempResponse.members
        mList.clear()

        tempResponse.enrolled_facilities.forEachIndexed { index, element ->
            mList.add(element.name)
        }
    }

    override fun onGallerySuccess(tempResponse: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.gallery.DataX>) {

    }

    override fun onVideosSuccess(tempResponse: MutableList<com.android.officersclub.ui_section.home_section.home_section.model.videos.DataX>) {
    }

    fun openDialog(enrolledItems: Array<String?>) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        val rowList: View = layoutInflater.inflate(R.layout.latout_list_in_alert, null)
        val listView: ListView = rowList.findViewById(R.id.listView)
        val btnOk:Button = rowList.findViewById(R.id.btn_ok)
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, enrolledItems)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
        alertDialog.setView(rowList)
        val dialog: AlertDialog = alertDialog.create()
        dialog.setCancelable(true)
        dialog.show()
    }
}