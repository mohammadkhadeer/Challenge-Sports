package com.challenge.sports.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import score.pro.R
import com.challenge.sports.model.data.homepage.past.future.Match
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference
import java.text.SimpleDateFormat

class ScheduleAdapter(var context: Context, var matches: List<Any>, var adapterType: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class PastFutureAdapterFootball(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.match_date)
        val time = itemView.findViewById<TextView>(R.id.time)
        val leagueNameTv = itemView.findViewById<TextView>(R.id.group_indicator)


        var team_1_logo=itemView.findViewById<ImageView>(R.id.team_1_logo)
        var team_2_logo=itemView.findViewById<ImageView>(R.id.team_2_logo)

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
        var matchTimeTv = itemView.findViewById<TextView>(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (adapterType) {
            MainAdapterCommunicator.BASKETBALL_TYPE -> {
                PastFutureAdapterBasketball(
                    LayoutInflater.from(context)
                        .inflate(R.layout.past_future_item_basketball, parent, false)
                )
            }
            else -> {
                PastFutureAdapterFootball(
                    LayoutInflater.from(context).inflate(R.layout.schedule_adapter, parent, false)
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
                val sdf3 = SimpleDateFormat("MMM dd,yyyy")
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
                date.text = sdf3.format(sdf.parse(match.matchTime).time)
                leagueNameTv.text = league

                if (match.state!=0){

                    if (match.homeLogo.isEmpty())
                        Glide.with(context).load(R.drawable.football).into(holder.team_1_logo)
                    else
                        Glide.with(context).load(match.homeLogo).into(holder.team_1_logo)

                    if (match.awayLogo.isEmpty())
                        Glide.with(context).load(R.drawable.football).into(holder.team_2_logo)
                    else
                        Glide.with(context).load(match.awayLogo).into(holder.team_2_logo)

                }else{

                }

                homeTeam.text =homeTeamT
                awayTeam.text = awayTeamT
            }
        } else {
            val mHolder = holder as PastFutureAdapterBasketball
            mHolder.apply {
                val match =
                    matches[position] as com.challenge.sports.model.data.basketball.homepage.past.future.Match
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
                    //scoreIndicator.text = context.getString(R.string.soon)
                } else {
                    //scoreIndicator.text = match.homeScore + " : " + match.awayScore
                }

            }
        }


    }

    override fun getItemCount(): Int {
        return matches.size
    }
}