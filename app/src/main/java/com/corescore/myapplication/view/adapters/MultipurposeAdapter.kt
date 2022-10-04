package com.corescore.myapplication.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import corescore.myapplication.R

abstract class MultipurposeAdapter(var context:Context,var layout: Int,var onclick: RecyclerViewOnclick): RecyclerView.Adapter<MultipurposeAdapter.viewHolder>() {
    inner class viewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        var headline=itemview.findViewById<TextView>(R.id.headline_rv)
        var tag=itemview.findViewById<TextView>(R.id.tag)
        var imageContainer=itemview.findViewById<ImageView>(R.id.background_trending)
        init {
            itemview.setOnClickListener{
                onclick.onClick(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(layout,parent,false))
    }

}