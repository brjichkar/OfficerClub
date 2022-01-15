package com.android.officersclub.ui_section.home_section.home_section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.officersclub.R
import com.bumptech.glide.Glide
import com.android.officersclub.ui_section.home_section.home_section.model.DataX
import kotlinx.android.synthetic.main.layout_news_row.view.*

class AdapterFacilities (private var facilityList: MutableList<DataX>, private var mContext: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterFacilities.FacilityAdapter>()
{

    inner class FacilityAdapter(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        fun setDataToView(sampleDataModel: DataX, receivedContext: Context){
            itemView.tv_title.text=sampleDataModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityAdapter {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_news_row, parent, false)
        return FacilityAdapter(itemView)
    }

    override fun onBindViewHolder(holder: FacilityAdapter, position: Int) {
        holder.setDataToView(facilityList[position],mContext)
    }

    override fun getItemCount(): Int {
        return facilityList.size
    }
}