package com.corescore.myapplication.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import corescore.myapplication.R
import com.corescore.myapplication.model.data.videos.List
import com.corescore.myapplication.view.adapters.RecyclerViewOnclick

class VideosAdapter(var context: Context,var list: ArrayList<List>, var layout: Int, var onclick: RecyclerViewOnclick,var loadMoreCommunicator: LoadMoreCommunicator): RecyclerView.Adapter<VideosAdapter.viewHolder>() {
    inner class viewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var headline=itemview.findViewById<TextView>(R.id.headline_rv)
        var tag=itemview.findViewById<TextView>(R.id.tag)
        var imageContainer=itemview.findViewById<ImageView>(R.id.background_trending)
        init {
            itemview.setOnClickListener{
                onclick.onClick(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosAdapter.viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(layout,parent,false))
    }

    override fun onBindViewHolder(holder: VideosAdapter.viewHolder, position: Int) {
        holder.headline.text = list?.get(position)?.title
        holder.tag.text =list?.get(position)?.createTime.substringBefore("T")
        Glide.with(context)
            .load(list?.get(position)?.thumbnailPath)
            .into(holder.imageContainer)
        if (position==list.size-1){
            loadMoreCommunicator.loadMore()
        }
    }

    fun updateList(list: kotlin.collections.List<List>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return list.size
    }

}