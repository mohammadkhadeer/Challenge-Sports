package com.football.live.view.fragments.standings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import score.pro.R
import com.football.live.model.data.standings.player.List
import com.football.live.utils.GeneralTools
import com.football.live.utils.SharedPreference

class PlayerStandingAdapter(var context:Context,var playerList:ArrayList<List>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class PlayerStandingAdapterViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var ranking_tv=itemView.findViewById<TextView>(R.id.rank_text)
        var teamNameTv=itemView.findViewById<TextView>(R.id.team_name)
        var playerNameTv=itemView.findViewById<TextView>(R.id.player_name)
        var goalsTv=itemView.findViewById<TextView>(R.id.goals)
        var goalsHomeTv=itemView.findViewById<TextView>(R.id.home_goals)
        var goalsAwayTv=itemView.findViewById<TextView>(R.id.away_goals)
        var moreBt=itemView.findViewById<ImageView>(R.id.more_bt)
        var moreLayout=itemView.findViewById<View>(R.id.more_layout)
        var home_penalties=itemView.findViewById<TextView>(R.id.home_penalties)
        var away_penalties=itemView.findViewById<TextView>(R.id.away_penalties)
        var matches_played=itemView.findViewById<TextView>(R.id.matches_played)
        var substituted=itemView.findViewById<TextView>(R.id.substituted)
        init {
            moreBt.setOnClickListener {
                if (moreLayout.visibility==View.VISIBLE){
                    moreLayout.visibility=View.GONE
                }else{
                    moreLayout.visibility=View.VISIBLE
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return PlayerStandingAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.player_standing_recyclerview,parent,false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myholder=holder as PlayerStandingAdapterViewHolder
        val player=playerList[position]
        val rank=(position+1).toString()
        var playerName=""
        val teamName=when(GeneralTools.getLocale(context)){
            SharedPreference.ENGLISH->{
                playerName=player.playerNameEn
                player.teamNameEn
            }
            SharedPreference.CHINESE->{
                playerName=player.playerNameChs
                player.teamNameChs
            }
            else->{
                playerName=player.playerNameEn
                player.teamNameEn
            }
        }
        myholder.ranking_tv.text= rank
        myholder.teamNameTv.text=teamName
        myholder.playerNameTv.text=playerName
        myholder.goalsTv.text=player.goals.toString()
        myholder.goalsHomeTv.text=player.homeGoals.toString()
        myholder.goalsAwayTv.text=player.awayGoals.toString()
        myholder.home_penalties.text=player.homePenalty.toString()
        myholder.away_penalties.text=player.awayPenalty.toString()
        myholder.matches_played.text=player.matchNum.toString()
        myholder.substituted.text=player.subNum.toString()
    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}