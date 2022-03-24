package com.officersclub.ui_section.home_section.home_section.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.officersclub.R
import com.officersclub.ui_section.home_section.home_section.model.gallery.DataX
import com.bumptech.glide.Glide
import android.graphics.drawable.ColorDrawable
import android.app.Dialog
import android.graphics.Color
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.layout_gallery_row.view.*

class AdapterGallery (private var historyList: MutableList<DataX>, private var mContext: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterGallery.NewsItemAdapter>()
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
                showDialog(sampleDataModel.image_path)
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

    private fun showDialog(imagePath:String) {
        // custom dialog
        val dialog = Dialog(mContext)
        dialog.setContentView(R.layout.layout_custome_image_popup)

        // set the custom dialog components - text, image and button
        val bigImg=dialog.findViewById(R.id.iv_big_img) as ImageView
        val close = dialog.findViewById(R.id.btnClose) as ImageButton
        // Close Button
        close.setOnClickListener {
            dialog.dismiss()
            //TODO Close button action
        }

        Glide.with(mContext)
            .load(imagePath)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(R.drawable.oc_icons)
            .into(bigImg)

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}