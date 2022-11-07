package com.football.live.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import score.pro.R
import com.football.live.model.data.homepage.event.formatted.FormattedTecnicEvent
import com.football.live.utils.Formatters

class TechnicAdapter(var context: Context,var dataList:List<FormattedTecnicEvent>) :RecyclerView.Adapter<TechnicAdapter.TechnicAdapterViewHolder>(){
    inner class TechnicAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var technicName=itemView.findViewById<TextView>(R.id.technic_name)
        var homeValue=itemView.findViewById<TextView>(R.id.home_technic_value)
        var awayValue=itemView.findViewById<TextView>(R.id.away_technic_value)
        var homeIndicator=itemView.findViewById<View>(R.id.home_indicator)
        var awayIndicator=itemView.findViewById<View>(R.id.away_indicator)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechnicAdapterViewHolder {
        return TechnicAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.technic_detail_item,parent,false))
    }

    override fun onBindViewHolder(holder: TechnicAdapterViewHolder, position: Int) {

        holder.technicName.text=Formatters.returnTechnicName(dataList[position].tecnicName,context)
        holder.homeValue.text=dataList[position].homeCount
        holder.awayValue.text=dataList[position].awayCount

        if (dataList[position].homeCount.contains("%"))
        {
            val homeCountSplit=dataList[position].homeCount.substringBefore("%")
            val width=homeCountSplit.toDouble()/100
            (holder.homeIndicator.layoutParams as ConstraintLayout.LayoutParams)
                .matchConstraintPercentWidth=width.toFloat()
        }
        else{
            try {
                (holder.homeIndicator.layoutParams as ConstraintLayout.LayoutParams)
                    .matchConstraintPercentWidth=
                    returnHomePercentage(dataList[position].homeCount.toDouble(),dataList[position].awayCount.toDouble()).toFloat()
            }catch (e:Exception){

            }

        }
    }

    private fun returnHomePercentage(homeScore: Double, awayScore: Double): Double {
        val total = homeScore + awayScore
         return homeScore / total
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

}