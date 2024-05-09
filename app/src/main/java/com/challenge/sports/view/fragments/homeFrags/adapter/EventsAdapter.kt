package com.challenge.sports.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import score.pro.R
import com.challenge.sports.utils.Formatters

class EventsAdapter(var context: Context) :
    RecyclerView.Adapter<EventsAdapter.EventsAdapterViewHolder>() {
    inner class EventsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var headingView = itemView.findViewById<View>(R.id.heading_view)
        var iconHeading = itemView.findViewById<ImageView>(R.id.icon_heading)
        var textHeading = itemView.findViewById<TextView>(R.id.text_heading)
        var homeView = itemView.findViewById<View>(R.id.body_view_home)
        var awayView = itemView.findViewById<View>(R.id.body_view_away)
        var timeHome = itemView.findViewById<TextView>(R.id.time_body)
        var iconHome = itemView.findViewById<ImageView>(R.id.icon_body)
        var textHome = itemView.findViewById<TextView>(R.id.text_body)
        var timeAway = itemView.findViewById<TextView>(R.id.time_body_away)
        var iconAway = itemView.findViewById<ImageView>(R.id.icon_body_away)
        var textAway = itemView.findViewById<TextView>(R.id.text_body_away)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapterViewHolder {
        return EventsAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.events_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventsAdapterViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }

}