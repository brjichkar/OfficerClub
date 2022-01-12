package com.android.officersclub.ui_section.home_section.profile_section.family_section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.profile_section.family_section.model.DataX
import kotlinx.android.synthetic.main.row_family_member.view.*

class AdapterFamily (private var tempList: MutableList<DataX>, private var mContext: Context) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AdapterFamily.FamilyItemAdapter>()
{
    inner class FamilyItemAdapter(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        fun setDataToView(sampleDataModel: DataX, receivedContext: Context){
            itemView.tv_name.text=sampleDataModel.name
            itemView.tv_mobile.text=sampleDataModel.mobile
            itemView.tv_relation.text=sampleDataModel.relation
            itemView.tv_dob.text=sampleDataModel.dob
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyItemAdapter {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_family_member, parent, false)
        return FamilyItemAdapter(itemView)
    }

    override fun onBindViewHolder(holder: FamilyItemAdapter, position: Int) {
        holder.setDataToView(tempList[position],mContext)
    }

    override fun getItemCount(): Int {
        return tempList.size
    }
}