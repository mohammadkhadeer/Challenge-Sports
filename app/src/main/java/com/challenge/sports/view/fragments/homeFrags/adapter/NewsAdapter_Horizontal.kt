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

class NewsAdapter_Horizontal (var context: Context, var  newsList:ArrayList<com.challenge.sports.model.data.news.List>
, var onclick: RecyclerViewOnclick):RecyclerView.Adapter<NewsAdapter_Horizontal.viewHolder>(){

    inner class viewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var headline=itemview.findViewById<TextView>(R.id.headline)
        var tag=itemview.findViewById<TextView>(R.id.detail)
        var imageContainer=itemview.findViewById<ImageView>(R.id.image_view_news)
        var timeAgo=itemview.findViewById<TextView>(R.id.time)
        init {
            itemview.setOnClickListener{
                onclick.onClick(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(R.layout.news_rv_element_horizontal,parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.headline.text= newsList[position].title
        holder.tag.text= newsList?.get(position)?.description
        holder.timeAgo.text=newsList?.get(position).createTime.substringBefore("T")
        Glide.with(context).load(newsList?.get(position)?.path).into(holder.imageContainer)

        if (position == (newsList?.size?.minus(1) ?: false)){
            //loadMoreCommunicator.loadMore()
        }
    }

    fun updateList(list:List<com.challenge.sports.model.data.news.List>){
        newsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

}

