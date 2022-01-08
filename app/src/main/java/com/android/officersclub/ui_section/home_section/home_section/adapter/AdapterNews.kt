package com.android.officersclub.ui_section.home_section.home_section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.home_section.model.Data
import com.android.officersclub.ui_section.home_section.home_section.model.DataX
import com.android.officersclub.ui_section.home_section.home_section.model.NewsModel
import kotlinx.android.synthetic.main.row_registered_user_history.view.*

class AdapterNews (private var historyList: MutableList<DataX>, private var mContext: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterNews.NewsItemAdapter>()
{
    /**
     * @Class : HistoryItemAdapter
     * @Usage : This class is used to provide holder object for row item
     */
    inner class NewsItemAdapter(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        fun setDataToView(sampleDataModel: DataX, receivedContext: Context){
            itemView.tv_title.text=sampleDataModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemAdapter {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_news_row, parent, false)
        return NewsItemAdapter(itemView)
    }

    override fun onBindViewHolder(holder: NewsItemAdapter, position: Int) {
        holder.setDataToView(historyList[position],mContext)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}