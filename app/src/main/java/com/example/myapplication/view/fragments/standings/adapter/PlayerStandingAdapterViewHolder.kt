package com.example.myapplication.view.fragments.standings.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.data.standings.player.List

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

    override fun getItemViewType(position: Int): Int {
        return if (position==0)
            1
        else
            0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1->{
               object: RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.player_standing_first_item,parent,false)){

               }
            }else->{
                PlayerStandingAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.player_standing_recyclerview,parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position==0){
            return
        }
        val myholder=holder as PlayerStandingAdapterViewHolder
        val player=playerList[position]
        val rank=(position).toString()
        myholder.ranking_tv.text= rank
        myholder.teamNameTv.text=player.teamNameEn
        myholder.playerNameTv.text=player.playerNameEn
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