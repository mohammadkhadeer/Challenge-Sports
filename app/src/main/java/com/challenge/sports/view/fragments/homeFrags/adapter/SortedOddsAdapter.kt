package com.challenge.sports.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import score.pro.R

class SortedOddsAdapter(var context:Context):RecyclerView.Adapter<SortedOddsAdapter.SortedOddsAdapterViewHolder>() {
    inner class SortedOddsAdapterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var typeOddsTv=itemView.findViewById<TextView>(R.id.type_odd_tv)
        var roundTv=itemView.findViewById<TextView>(R.id.round_tv)
        var w_tv=itemView.findViewById<TextView>(R.id.w_tv)
        var d_tv=itemView.findViewById<TextView>(R.id.d_tv)
        var l_tv=itemView.findViewById<TextView>(R.id.l_tv)
        var percent_tv=itemView.findViewById<TextView>(R.id.percent_tv)
        var over_tv=itemView.findViewById<TextView>(R.id.over_tv)
        var under_tv=itemView.findViewById<TextView>(R.id.under_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortedOddsAdapterViewHolder {
        return SortedOddsAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.analysis_odds_item,parent,false))
    }

    override fun onBindViewHolder(holder: SortedOddsAdapterViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 9
    }

}