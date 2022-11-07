package com.football.live.view.fragments.homeFrags.adapter.pastfuture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import score.pro.R
import com.football.live.model.data.homepage.past.future.Match
import com.football.live.utils.GeneralTools
import com.football.live.utils.SharedPreference
import com.football.live.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import java.text.SimpleDateFormat

class PastFutureAdapter(var context: Context, var matches: List<Any>, var adapterType: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class PastFutureAdapterFootball(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.match_date)
        val time = itemView.findViewById<TextView>(R.id.match_time)
        val leagueNameTv = itemView.findViewById<TextView>(R.id.group_indicator)
        val ht_ratio = itemView.findViewById<TextView>(R.id.ht_ratio)
        val c_ratio = itemView.findViewById<TextView>(R.id.c_ratio)

        var score_team1=itemView.findViewById<TextView>(R.id.score_team1)
        var score_team2=itemView.findViewById<TextView>(R.id.score_team2)
        var team_1_logo=itemView.findViewById<ImageView>(R.id.team_1_logo)
        var team_2_logo=itemView.findViewById<ImageView>(R.id.team_2_logo)

        var yallow_card_Home=itemView.findViewById<TextView>(R.id.yallow_card_Home)
        var red_card_Home=itemView.findViewById<TextView>(R.id.red_card_Home)
        var yallow_card_Away=itemView.findViewById<TextView>(R.id.yallow_card_Away)
        var red_card_Away=itemView.findViewById<TextView>(R.id.red_card_Away)

        var cards_con=itemView.findViewById<LinearLayout>(R.id.cards_con)



        //val scoreIndicator = itemView.findViewById<TextView>(R.id.score_indicator)
        val homeTeam = itemView.findViewById<TextView>(R.id.team_1_name)
        val awayTeam = itemView.findViewById<TextView>(R.id.team_2_name)
    }

    inner class PastFutureAdapterBasketball(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var leagueNameShort = itemView.findViewById<TextView>(R.id.group_indicator)
        var homeNameTv = itemView.findViewById<TextView>(R.id.team_1_name)
        var awayNameTv = itemView.findViewById<TextView>(R.id.team_2_name)
        var scoreIndicator = itemView.findViewById<TextView>(R.id.score_indicator)
        var stateNdate = itemView.findViewById<TextView>(R.id.match_date)
        var matchTimeTv = itemView.findViewById<TextView>(R.id.match_time)
        var home_q1 = itemView.findViewById<TextView>(R.id.home_q1)
        var home_q2 = itemView.findViewById<TextView>(R.id.home_q2)
        var home_q3 = itemView.findViewById<TextView>(R.id.home_q3)
        var home_q4 = itemView.findViewById<TextView>(R.id.home_q4)
        var home_f = itemView.findViewById<TextView>(R.id.home_f)
        var away_q1 = itemView.findViewById<TextView>(R.id.away_q1)
        var away_q2 = itemView.findViewById<TextView>(R.id.away_q2)
        var away_q3 = itemView.findViewById<TextView>(R.id.away_q3)
        var away_q4 = itemView.findViewById<TextView>(R.id.away_q4)
        var away_f = itemView.findViewById<TextView>(R.id.away_f)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (adapterType) {
            MainAdapterCommunicator.BASKETBALL_TYPE -> {
                PastFutureAdapterBasketball(
                    LayoutInflater.from(context)
                        .inflate(R.layout.home_match_item_basketball_new, parent, false)
                )
            }
            else -> {
                PastFutureAdapterFootball(
                    LayoutInflater.from(context).inflate(R.layout.past_future_item, parent, false)
                )

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (adapterType != MainAdapterCommunicator.BASKETBALL_TYPE) {
            val mHolder = holder as PastFutureAdapterFootball
            mHolder.apply {
                val match = matches[position] as Match
                val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
                val sdf2 = SimpleDateFormat("EEE, dd MMM")
                var league=""
                var homeTeamT=""
                var awayTeamT=""
                when(GeneralTools.getLocale(context)){
                    SharedPreference.CHINESE->{
                        league=match.leagueCnShort
                        homeTeamT=match.homeChs
                        awayTeamT=match.awayChs
                    }
                    SharedPreference.INDONESIAN->{
                        league=match.leagueNameShortId
                        homeTeamT=match.homeNameId
                        awayTeamT=match.awayNameId
                    }
                    SharedPreference.VIETNAMESE->{
                        league=match.leagueNameShortTh
                        homeTeamT=match.homeNameVi
                        awayTeamT=match.awayNameVi
                    }
                    SharedPreference.THAI->{
                        league=match.leagueNameShortVi
                        homeTeamT=match.homeNameTh
                        awayTeamT=match.awayNameTh
                    }
                    else->{
                        league=match.leagueEnShort
                        homeTeamT=match.homeEn
                        awayTeamT=match.awayEn
                    }
                }

                time.text = GeneralTools.return24HrsOnly(GeneralTools.returnTime(match.matchTime))
                date.text = sdf2.format(sdf.parse(match.matchTime).time)

                leagueNameTv.text = league
                ht_ratio.text =
                    match.homeHalfScore.toString() + ":" + match.awayHalfScore.toString()
                c_ratio.text = match.homeCorner.toString() + ":" + match.awayCorner.toString()

                if (match.state!=0){
                    holder.score_team1.text=match.homeScore.toString()
                    holder.score_team2.text=match.awayScore.toString()

                    holder.yallow_card_Home.text=match.homeYellow.toString()
                    holder.red_card_Home.text=match.awayRed.toString()

                    holder.yallow_card_Away.text=match.awayYellow.toString()
                    holder.red_card_Away.text=match.awayRed.toString()

                    if (match.homeLogo.isEmpty())
                        Glide.with(context).load(R.drawable.football).into(holder.team_1_logo)
                    else
                        Glide.with(context).load(match.homeLogo).into(holder.team_1_logo)

                    if (match.awayLogo.isEmpty())
                        Glide.with(context).load(R.drawable.football).into(holder.team_2_logo)
                    else
                        Glide.with(context).load(match.awayLogo).into(holder.team_2_logo)

                }else{
                    holder.cards_con.visibility=View.GONE
                }

                homeTeam.text =homeTeamT
                awayTeam.text = awayTeamT
            }
        }

        else {
            val mHolder = holder as PastFutureAdapterBasketball
            mHolder.apply {
                val match =
                    matches[position] as com.football.live.model.data.basketball.homepage.past.future.Match
                val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
                val sdf2 = SimpleDateFormat("EEE, dd MMM")
                matchTimeTv.text =
                    GeneralTools.return24HrsOnly(GeneralTools.returnTime(match.matchTime))
                stateNdate.text = sdf2.format(sdf.parse(match.matchTime).time)

                var league=""
                var homeTeam=""
                var awayTeam=""
                when(GeneralTools.getLocale(context)){
                    SharedPreference.CHINESE->{
                        league=match.leagueChs
                        homeTeam=match.homeTeamChs
                        awayTeam=match.awayTeamChs
                    }
                    SharedPreference.INDONESIAN->{
                        league=match.leagueNameId
                        homeTeam=match.homeTeamId
                        awayTeam=match.awayTeamId
                    }
                    SharedPreference.VIETNAMESE->{
                        league=match.leagueNameVi
                        homeTeam=match.homeTeamVi
                        awayTeam=match.awayTeamVi
                    }
                    SharedPreference.THAI->{
                        league=match.leagueNameTh
                        homeTeam=match.homeTeamTh
                        awayTeam=match.awayTeamTh
                    }
                    else->{
                        league=match.leagueEn
                        homeTeam=match.homeTeamEn
                        awayTeam=match.awayTeamEn
                    }
                }

                leagueNameShort.text = league
                homeNameTv.text = homeTeam
                awayNameTv.text = awayTeam

                if (match.matchState == 0) {
                    scoreIndicator.text = context.getString(R.string.soon)
                } else {
                    scoreIndicator.text = match.homeScore + " : " + match.awayScore
                }
                home_q1.text = match.home1
                home_q2.text = match.home2
                home_q3.text = match.home3
                home_q4.text = match.home4
                home_f.text=match.homeScore

                away_q1.text=match.away1
                away_q2.text=match.away2
                away_q3.text=match.away3
                away_q4.text=match.away4
                away_f.text=match.awayScore

            }
        }


    }

    override fun getItemCount(): Int {
        return matches.size
    }
}