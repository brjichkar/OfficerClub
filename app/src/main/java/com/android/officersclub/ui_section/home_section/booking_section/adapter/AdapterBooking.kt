package com.android.officersclub.ui_section.home_section.booking_section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.booking_section.model.BookingModel
import kotlinx.android.synthetic.main.row_registered_user_history.view.*

class AdapterBooking (private var historyList: MutableList<BookingModel>, private var mContext: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterBooking.HistoryItemAdapter>()
{
    /**
     * @Class : HistoryItemAdapter
     * @Usage : This class is used to provide holder object for row item
     */
    inner class HistoryItemAdapter(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        fun setDataToView(sampleDataModel: BookingModel, receivedContext: Context){
            itemView.tv_title.text=sampleDataModel.title
            itemView.tv_description.text=sampleDataModel.description
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