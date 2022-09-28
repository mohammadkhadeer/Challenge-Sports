package com.example.myapplication.view.fragments.homeFrags.adapter.pastfuture

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.data.news.details.OnPostDetailResponse

class DatesListAdapter(var context: Context,var dates:List<String>,var listener:OnPostDetailResponse<String>):RecyclerView.Adapter<DatesListAdapter.DatesListAdapterViewHolder>() {
    var selectedPosition:Int?=null
    inner class DatesListAdapterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var dateTv=itemView.findViewById<TextView>(R.id.date_tv)
        var dayOfTheWeekTv=itemView.findViewById<TextView>(R.id.day_of_week_tv)
        init {
            itemView.setOnClickListener {
                    selectedPosition=absoluteAdapterPosition
                    listener.onSuccess(dates[absoluteAdapterPosition].substringAfter("|"))
                    notifyDataSetChanged()

            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatesListAdapterViewHolder {
        return DatesListAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.date_item,parent,false))
    }

    override fun onBindViewHolder(holder: DatesListAdapterViewHolder, position: Int) {
        holder.apply {
            if (position==selectedPosition){
                dateTv.setTextColor(Color.WHITE)
                dateTv.setBackgroundResource(R.drawable.primary_rounded_rect)
            }
            else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    dateTv.setTextColor(context.getColor(R.color.grey_text_different))
                }
                dateTv.setBackgroundResource(0)
            }
            dateTv.text=dates[position].substringAfter("|").substringAfterLast("-")
            dayOfTheWeekTv.text=dates[position].substringBefore("|")
        }
    }

    override fun getItemCount(): Int {
       return dates.size
    }

}