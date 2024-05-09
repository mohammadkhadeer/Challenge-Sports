package com.challenge.sports.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import score.pro.R


class MatchesPopulatingAdapterBasketball(var context: Context, var data: List<Any>) :
RecyclerView.Adapter<MatchesPopulatingAdapterBasketball.MatchesPopulatingAdapterBasketballViewHolder>() {



    inner class MatchesPopulatingAdapterBasketballViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val dateTime=itemView.findViewById<TextView>(R.id.date_time)
        val leagueNameTv=itemView.findViewById<TextView>(R.id.league_name)
        val  total_points_half=itemView.findViewById<TextView>(R.id.total_points_half)
        val scoreIndicator=itemView.findViewById<TextView>(R.id.score_indicator)
        val homeTeam=itemView.findViewById<TextView>(R.id.team_1_name)
        val awayTeam=itemView.findViewById<TextView>(R.id.team_2_name)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchesPopulatingAdapterBasketballViewHolder {
        return MatchesPopulatingAdapterBasketballViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recent_match_item_basketball, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MatchesPopulatingAdapterBasketballViewHolder, position: Int) {

    }


    override fun getItemCount(): Int {
        return data.size
    }



}