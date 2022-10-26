package com.score.pro.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.score.pro.model.data.LegaDetails
import com.score.pro.view.adapters.RecyclerViewOnclick
import sports.myapplication.R

class LegasAdapter (var context: Context, var  legaList:ArrayList<com.score.pro.model.data.LegaDetails>
                    , var onclick: RecyclerViewOnclick):RecyclerView.Adapter<LegasAdapter.viewHolder>(){

    inner class viewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        var headline=itemview.findViewById<TextView>(R.id.text_view_lega_name)
        var imageContainer=itemview.findViewById<ImageView>(R.id.image_view_lega_image)

        init {
            itemview.setOnClickListener{
                onclick.onClick(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(R.layout.lega_rv,parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.headline.text= legaList[position].lega_name
        //Glide.with(context).load(legaList?.get(position)?.image).into(holder.imageContainer)

    }

    override fun getItemCount(): Int {
        return legaList.size
    }

    fun filterList(filterdNames: java.util.ArrayList<LegaDetails>) {
        this.legaList = filterdNames

        notifyDataSetChanged()
    }
}

