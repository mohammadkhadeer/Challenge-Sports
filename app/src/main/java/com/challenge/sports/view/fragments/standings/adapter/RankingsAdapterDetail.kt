package com.challenge.sports.view.fragments.standings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import score.pro.R
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference

class RankingsAdapterDetail(var context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class RankingViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var ranking_tv=itemView.findViewById<TextView>(R.id.rank_text)
        var rankingIndicator=itemView.findViewById<ImageView>(R.id.rank_indicator)
        var teamName=itemView.findViewById<TextView>(R.id.team_name)
        var teamFlag=itemView.findViewById<ImageView>(R.id.team_flag)
        var matchPlayed=itemView.findViewById<TextView>(R.id.mp_tv)
        var won=itemView.findViewById<TextView>(R.id.w_tv)
        var draw=itemView.findViewById<TextView>(R.id.d_tv)
        var loss=itemView.findViewById<TextView>(R.id.l_tv)
        var gf=itemView.findViewById<TextView>(R.id.gf_tv)
        var ga=itemView.findViewById<TextView>(R.id.ga_tv)
        var pts=itemView.findViewById<TextView>(R.id.pts_tv)
        var moreBt=itemView.findViewById<ImageView>(R.id.more_bt)
        var moreLayout=itemView.findViewById<View>(R.id.more_layout)
        var percentStats=itemView.findViewById<TextView>(R.id.percent_stats)
        var match1=itemView.findViewById<ImageView>(R.id.firstMatch)
        var match2=itemView.findViewById<ImageView>(R.id.secondMatch)
        var match3=itemView.findViewById<ImageView>(R.id.thirdMatch)
        var match4=itemView.findViewById<ImageView>(R.id.fourthMatch)
        var match5=itemView.findViewById<ImageView>(R.id.fifthMatch)
        var match6=itemView.findViewById<ImageView>(R.id.sixthMatch)
        init {
            moreBt.setOnClickListener{
                if (moreLayout.visibility==View.VISIBLE){
                    moreLayout.visibility=View.GONE
                }else{
                    moreLayout.visibility=View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RankingViewHolder(LayoutInflater.from(context).inflate(R.layout.standings_item,parent,false))


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


    }


    private fun returnMatchAsset(result:String):Int{
        return when(result){
            "0"-> R.drawable.ic_win

            "1"->R.drawable.ic_win

            "2"->R.drawable.ic_loss

            else-> R.drawable.ic_tbd
        }
    }


    override fun getItemCount(): Int {
        return 7
    }
}