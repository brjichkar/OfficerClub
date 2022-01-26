package com.android.officersclub.ui_section.home_section.home_section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.home_section.model.videos.DataX
import com.bumptech.glide.Glide
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.layout_gallery_row.view.*


class AdapterVideos (private var historyList: MutableList<DataX>, private var mContext: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterVideos.NewsItemAdapter>()
{

    inner class NewsItemAdapter(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        fun setDataToView(sampleDataModel: DataX, receivedContext: Context){
            itemView.tv_title.text=sampleDataModel.name
            Glide.with(receivedContext)
                .load(sampleDataModel.image_path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.profile_icon)
                .into(itemView.iv_news_icon)
            itemView.iv_news_icon.setOnClickListener {

                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(sampleDataModel.link)
                )
                try {
                    mContext.startActivity(webIntent)
                } catch (ex: ActivityNotFoundException) {
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemAdapter {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_gallery_row, parent, false)
        return NewsItemAdapter(itemView)
    }

    override fun onBindViewHolder(holder: NewsItemAdapter, position: Int) {
        holder.setDataToView(historyList[position],mContext)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}