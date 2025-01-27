package com.challenge.sports.view.fragments.homeFrags.adapter.pastfuture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import score.pro.R
import com.challenge.sports.model.data.news.details.OnPostDetailResponse

class DatesListAdapter(var context: Context,var dates:List<String>
,var listener:OnPostDetailResponse<String>):RecyclerView.Adapter<DatesListAdapter.DatesListAdapterViewHolder>() {
    var selectedPosition:Int?=null
    var selected_position =0;

    inner class DatesListAdapterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var dayOfTheWeekTv=itemView.findViewById<TextView>(R.id.day_of_week_tv)
        init {
            itemView.setOnClickListener {
                selected_position =absoluteAdapterPosition
                for (i in 0 until dates.size) {
                    if (i==selectedPosition)
                    {
                        dayOfTheWeekTv.setBackground(ContextCompat.getDrawable(context, R.drawable.s_time_tab_new));
                    } else{
                        dayOfTheWeekTv.setBackground(ContextCompat.getDrawable(context, R.drawable.s_not_selected_time_tab))
                    }
                }
                selectedPosition=absoluteAdapterPosition
                listener.onSuccess(dates[absoluteAdapterPosition])
                notifyDataSetChanged()
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatesListAdapterViewHolder {
        return DatesListAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.date_item,parent,false))
    }

    override fun onBindViewHolder(holder: DatesListAdapterViewHolder, position: Int) {
        holder.apply {
//            if (position==selectedPosition){
//                dateTv.setTextColor(Color.WHITE)
//                dateTv.setBackgroundResource(R.drawable.primary_rounded_rect)
//            }
//            else{
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    dateTv.setTextColor(context.getColor(R.color.grey_text_different))
//                }
//                dateTv.setBackgroundResource(0)
//            }
//            dateTv.text=dates[position]

            dayOfTheWeekTv.text=dates[position]
            if (position ==selected_position)
            {
                holder.dayOfTheWeekTv.setBackground(ContextCompat.getDrawable(context, R.drawable.s_time_tab_new))
                holder.dayOfTheWeekTv.setTextColor(context.getResources().getColor(R.color.brand_color));
            } else{
                holder.dayOfTheWeekTv.setBackground(ContextCompat.getDrawable(context, R.drawable.s_not_selected_time_tab))
                holder.dayOfTheWeekTv.setTextColor(context.getResources().getColor(R.color.bottom_tab_not_selected_color));

            }

        }




    }

    override fun getItemCount(): Int {
       return dates.size
    }

}