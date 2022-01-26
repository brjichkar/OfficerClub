package com.android.officersclub.ui_section.home_section.booking_section.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.booking_section.details.ActivityDetails
import com.android.officersclub.ui_section.home_section.booking_section.details.ActivityServiceDetails
import com.android.officersclub.ui_section.home_section.booking_section.model.BookingModel
import com.android.officersclub.ui_section.otp_section.ActivityOtp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.layout_news_row.view.*
import kotlinx.android.synthetic.main.row_registered_user_history.view.*
import kotlinx.android.synthetic.main.row_registered_user_history.view.tv_title

class AdapterBooking (private var historyList:
                      MutableList<com.android.officersclub.ui_section.home_section.booking_section.model.services.DataX> , private var mContext: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterBooking.HistoryItemAdapter>()
{
    /**
     * @Class : HistoryItemAdapter
     * @Usage : This class is used to provide holder object for row item
     */
    inner class HistoryItemAdapter(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        fun setDataToView(sampleDataModel: com.android.officersclub.ui_section.home_section.booking_section.model.services.DataX, receivedContext: Context){
            itemView.tv_title.text=sampleDataModel.name
            //.tv_description.text=sampleDataModel.description
            Glide.with(receivedContext)
                .load(sampleDataModel.image_path)
                .centerCrop()
                .placeholder(R.drawable.oc_icons)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(itemView.iv_room_icon)

            itemView.ll_details.setOnClickListener {
                val mainActIntent = Intent(mContext, ActivityServiceDetails::class.java)
                mainActIntent.putExtra("selectedItem",sampleDataModel.other_facility_id)
                mContext.startActivity(mainActIntent)
            }

            itemView.iv_next.setOnClickListener {
                val mainActIntent = Intent(mContext, ActivityServiceDetails::class.java)
                mainActIntent.putExtra("selectedItem",sampleDataModel.other_facility_id)
                mContext.startActivity(mainActIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemAdapter {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_registered_user_history, parent, false)
        return HistoryItemAdapter(itemView)
    }

    override fun onBindViewHolder(holder: HistoryItemAdapter, position: Int) {
        holder.setDataToView(historyList[position],mContext)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}