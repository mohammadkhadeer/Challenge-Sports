package com.example.myapplication.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.event.formatted.EventKind
import com.example.myapplication.model.data.homepage.event.formatted.FormattedEventG_S_F
import com.example.myapplication.utils.Formatters

class EventsAdapter(var context: Context, var data: List<FormattedEventG_S_F>) :
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
        val element = data[position].eventDetail

        when (data[position].eventType) {
            EventKind.GOAL -> {
                if (element.isHome) {
                    holder.homeView.visibility = VISIBLE
                    Glide.with(context)
                        .load(R.drawable.ic_goal)
                        .into(holder.iconHome)
                    holder.textHome.text = Formatters.returnKind(element.kind.toString())+": "+element.nameEn
                    holder.timeHome.text = element.time
                } else {
                    holder.awayView.visibility = VISIBLE
                    Glide.with(context)
                        .load(R.drawable.ic_goal)
                        .into(holder.iconAway)
                    holder.textAway.text = Formatters.returnKind(element.kind.toString())+": "+element.nameEn
                    holder.timeAway.text = element.time
                }
            }
            EventKind.GOAL_HEADING -> {
                holder.headingView.visibility= VISIBLE
                Glide.with(context)
                    .load(R.drawable.ic_goal_heading)
                    .into(holder.iconHeading)
                holder.textHeading.text=context.getString(R.string.goals_and_assists)
            }
            EventKind.FOUL -> {
                if (element.isHome) {
                    holder.homeView.visibility = VISIBLE
                    Glide.with(context)
                        .load(R.drawable.ic_foul)
                        .into(holder.iconHome)
                    holder.textHome.text = Formatters.returnKind(element.kind.toString())+" : "+element.nameEn
                    holder.timeHome.text = element.time
                } else {
                    holder.awayView.visibility = VISIBLE
                    Glide.with(context)
                        .load(R.drawable.ic_foul)
                        .into(holder.iconAway)
                    holder.textAway.text = Formatters.returnKind(element.kind.toString())+" : "+element.nameEn
                    holder.timeAway.text = element.time
                }
            }
            EventKind.FOUL_HEADING -> {
                holder.headingView.visibility= VISIBLE
                Glide.with(context)
                    .load(R.drawable.ic_foul)
                    .into(holder.iconHeading)
                holder.textHeading.text=context.getString(R.string.fouls)
            }
            EventKind.SUBSTITUTION_HEADING -> {
                holder.headingView.visibility= VISIBLE
                Glide.with(context)
                    .load(R.drawable.ic_subs)
                    .into(holder.iconHeading)
                holder.textHeading.text=context.getString(R.string.subs)
            }
            EventKind.SUBSTITUTION -> {
                if (element.isHome) {
                    holder.homeView.visibility = VISIBLE
                    Glide.with(context)
                        .load(R.drawable.ic_subs)
                        .into(holder.iconHome)
                    holder.textHome.text = element.nameEn
                    holder.timeHome.text = element.time
                } else {
                    holder.awayView.visibility = VISIBLE
                    Glide.with(context)
                        .load(R.drawable.ic_subs)
                        .into(holder.iconAway)
                    holder.textAway.text = element.nameEn
                    holder.timeAway.text = element.time
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}