package com.score.pro.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import score.pro.R

class MatchesPopulatingAdapter(var context: Context, var data: List<List<String>>) :
    RecyclerView.Adapter<MatchesPopulatingAdapter.MatchesPopulatingAdapterViewHolder>() {



    inner class MatchesPopulatingAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dateTime=itemView.findViewById<TextView>(R.id.date_time)
        val leagueNameTv=itemView.findViewById<TextView>(R.id.league_name)
        val  c_ht_tv=itemView.findViewById<TextView>(R.id.c_ht)
        val scoreIndicator=itemView.findViewById<TextView>(R.id.score_indicator)
        val homeTeam=itemView.findViewById<TextView>(R.id.team_1_name)
        val awayTeam=itemView.findViewById<TextView>(R.id.team_2_name)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchesPopulatingAdapterViewHolder {
        return MatchesPopulatingAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recent_match_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MatchesPopulatingAdapterViewHolder, position: Int) {
        val str=data[position][0].split(",")
        val scoreString=str[9].split("^")
        val dateSt=str[3].split("^")
        holder.dateTime.text=dateSt[1]
        holder.leagueNameTv.text=str[2]
        holder.homeTeam.text=str[5]
        holder.awayTeam.text=str[8]
        holder.scoreIndicator.text=scoreString[1]+" : "+scoreString[2]
        //C: 3:11  HT: 0:11
        holder.c_ht_tv.text="C: "+scoreString[7]+":"+scoreString[8]+"  HT: "+scoreString[3]+":"+scoreString[4]
        println(scoreString.toString())
    }

    override fun getItemCount(): Int {
        return data.size
    }



}