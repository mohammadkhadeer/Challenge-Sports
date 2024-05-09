package com.challenge.sports.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import score.pro.R

import com.challenge.sports.view.adapters.RecyclerViewOnclick

class VideosAdapter(var context: Context, var layout: Int, var onclick: RecyclerViewOnclick,var loadMoreCommunicator: LoadMoreCommunicator): RecyclerView.Adapter<VideosAdapter.viewHolder>() {
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

    }

    override fun getItemCount(): Int {
        return 5
    }

}