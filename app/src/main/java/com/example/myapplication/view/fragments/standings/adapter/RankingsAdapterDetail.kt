package com.example.myapplication.view.fragments.standings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.leagueInfo.TeamInfo
import com.example.myapplication.model.data.homepage.leagueInfo.TotalStanding
import org.w3c.dom.Text

class RankingsAdapterDetail(var context:Context, var teams:ArrayList<TeamInfo>, var rankings:ArrayList<TotalStanding>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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

        try {
            val myholder=holder as RankingViewHolder
            val team=spewTeam(rankings[position].teamId)
            myholder.ranking_tv.text=rankings[position].rank.toString()
            Glide.with(context)
                .load(team.flag)
                .into(myholder.teamFlag)
            myholder.teamName.text=team.nameEn
            myholder.matchPlayed.text=rankings[position].totalCount.toString()
            myholder.won.text=rankings[position].winCount.toString()
            myholder.draw.text=rankings[position].drawCount.toString()
            myholder.loss.text=rankings[position].loseCount.toString()
            myholder.gf.text=rankings[position].getScore.toString()
            myholder.ga.text=rankings[position].loseScore.toString()
            myholder.pts.text=rankings[position].integral.toString()
            val rank=rankings[position]
            val percentStats="W%="+rank.winRate+"% / L%="+rank.loseRate+
                    "% / AVA="+rank.loseAverage+" D%="+rank.drawRate+"% / AVF="+rank.winAverage

            myholder.percentStats.text=percentStats

            Glide.with(context)
                .load(returnMatchAsset(rank.recentFirstResult))
                .into(myholder.match1)
            Glide.with(context)
                .load(returnMatchAsset(rank.recentSecondResult))
                .into(myholder.match2)
            Glide.with(context)
                .load(returnMatchAsset(rank.recentThirdResult))
                .into(myholder.match3)
            Glide.with(context)
                .load(returnMatchAsset(rank.recentFourthResult))
                .into(myholder.match4)
            Glide.with(context)
                .load(returnMatchAsset(rank.recentFifthResult))
                .into(myholder.match5)
            Glide.with(context)
                .load(returnMatchAsset(rank.recentSixthResult))
                .into(myholder.match6)
        }catch (e:Exception){

        }


    }


    private fun returnMatchAsset(result:String):Int{
        return when(result){
            "0"-> R.drawable.ic_win

            "1"->R.drawable.ic_draw

            "2"->R.drawable.ic_loss

            else-> R.drawable.ic_tbd
        }
    }


    private fun spewTeam(teamId:Int):TeamInfo{
        for (team in teams){
            if (teamId==team.teamId){
                return team
            }
        }
        val notfoundTeam=TeamInfo()
        notfoundTeam.teamId=0
        notfoundTeam.flag=""
        notfoundTeam.conferenceFlg=0
        notfoundTeam.nameChs=""
        notfoundTeam.nameCht=""
        notfoundTeam.nameEn="not found"
        return notfoundTeam
    }

    override fun getItemCount(): Int {
        return rankings.size
    }
}