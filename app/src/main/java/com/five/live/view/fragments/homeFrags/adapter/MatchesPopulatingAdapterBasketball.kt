package com.five.live.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import corescore.myapplication.R
import com.five.live.model.data.basketball.analysis.AwayLastMatche
import com.five.live.model.data.basketball.analysis.HeadToHead
import com.five.live.model.data.basketball.analysis.HomeLastMatche

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
        when(data[position]){
            is HeadToHead->{
                val data=data[position] as HeadToHead
                holder.apply {
                    dateTime.text=data.matchTime
                    leagueNameTv.text=data.leagueEn
                    total_points_half.text=context.getString(R.string.total_points)+": "+data.total+" "+context.getString(R.string.half)+": "+data.homeHalfScore+":"+data.awayHalfScore
                    scoreIndicator.text=data.homeScore.toString()+":"+data.awayScore.toString()
                    homeTeam.text=data.homeTeamEn
                    awayTeam.text=data.awayTeamEn
                }
            }
            is HomeLastMatche->{
                val data=data[position] as HomeLastMatche
                holder.apply {
                    dateTime.text=data.matchTime
                    leagueNameTv.text=data.leagueEn
                    total_points_half.text=context.getString(R.string.total_points)+": "+data.total+" "+context.getString(R.string.half)+": "+data.homeHalfScore+":"+data.awayHalfScore
                    scoreIndicator.text=data.homeScore.toString()+":"+data.awayScore.toString()
                    homeTeam.text=data.homeTeamEn
                    awayTeam.text=data.awayTeamEn
                }
            }
            is AwayLastMatche->{
                val data=data[position] as AwayLastMatche
                holder.apply {
                    dateTime.text=data.matchTime
                    leagueNameTv.text=data.leagueEn
                    total_points_half.text=context.getString(R.string.total_points)+": "+data.total+" "+context.getString(R.string.half)+": "+data.homeHalfScore+":"+data.awayHalfScore
                    scoreIndicator.text=data.homeScore.toString()+":"+data.awayScore.toString()
                    homeTeam.text=data.homeTeamEn
                    awayTeam.text=data.awayTeamEn
                }
            }
            else->{

            }
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }



}