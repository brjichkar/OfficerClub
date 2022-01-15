package com.android.officersclub.ui_section.home_section.profile_section.family_section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.profile_section.family_section.model.DataX
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.row_family_member.view.*

class AdapterFamily (private var tempList: MutableList<DataX>, private var mContext: Context,private var mListener:OnItemClickListener) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AdapterFamily.FamilyItemAdapter>()
{
    interface OnItemClickListener {
        fun onItemClick(item: DataX?)
    }

    inner class FamilyItemAdapter(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        fun setDataToView(sampleDataModel: DataX, receivedContext: Context){
            itemView.tv_name.text=sampleDataModel.name

            if(sampleDataModel.mobile.isEmpty()){
                itemView.tv_mobile.visibility=View.GONE
            }
            else{
                itemView.tv_mobile.visibility=View.VISIBLE
                itemView.tv_mobile.text=sampleDataModel.mobile
            }

            if(sampleDataModel.relation.isEmpty()){
                itemView.tv_relation.visibility=View.GONE
            }
            else{
                itemView.tv_relation.visibility=View.VISIBLE
                itemView.tv_relation.text=sampleDataModel.relation
            }

            if(sampleDataModel.dob.isEmpty()){
                itemView.tv_dob.visibility=View.GONE
            }
            else{
                itemView.tv_dob.visibility=View.VISIBLE
                itemView.tv_dob.text=sampleDataModel.dob
            }

            itemView.iv_room_icon.setOnClickListener {
                mListener.onItemClick(sampleDataModel)
            }

            Glide.with(mContext)
                .load(sampleDataModel.image_path)
                .centerCrop()
                .placeholder(R.drawable.profile_icon)
                .into(itemView.iv_room_icon)
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