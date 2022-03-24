package com.officersclub.ui_section.home_section.home_section.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.officersclub.R
import com.officersclub.ui_section.home_section.booking_section.details.ActivityDetails
import com.bumptech.glide.Glide
import com.officersclub.ui_section.home_section.home_section.model.DataX
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.layout_news_row.view.*

class AdapterFacilities (private var facilityList: MutableList<DataX>, private var mContext: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterFacilities.FacilityAdapter>()
{

    inner class FacilityAdapter(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        fun setDataToView(sampleDataModel: DataX, receivedContext: Context){
            itemView.tv_title.text=sampleDataModel.name
            Glide.with(receivedContext)
                .load(sampleDataModel.image_path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.profile_icon)
                .into(itemView.iv_news_icon)

            itemView.rl_details.setOnClickListener {
                val mainActIntent = Intent(mContext, ActivityDetails::class.java)
                mainActIntent.putExtra("selectedItem",sampleDataModel.facility_code)
                mContext.startActivity(mainActIntent)
            }
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