package com.example.myapplication.view.fragments.homeFrags.adapter.pastfuture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.past.future.Match
import com.example.myapplication.utils.GeneralTools
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import java.text.SimpleDateFormat

class PastFutureAdapter(var context: Context, var matches: List<Any>, var adapterType: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class PastFutureAdapterFootball(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.match_date)
        val time = itemView.findViewById<TextView>(R.id.match_time)
        val leagueNameTv = itemView.findViewById<TextView>(R.id.group_indicator)
        val ht_ratio = itemView.findViewById<TextView>(R.id.ht_ratio)
        val c_ratio = itemView.findViewById<TextView>(R.id.c_ratio)
        val scoreIndicator = itemView.findViewById<TextView>(R.id.score_indicator)
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
                        .inflate(R.layout.past_future_item_basketball, parent, false)
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
                time.text = GeneralTools.return24HrsOnly(GeneralTools.returnTime(match.matchTime))
                date.text = sdf2.format(sdf.parse(match.matchTime).time)
                leagueNameTv.text = match.leagueEn
                ht_ratio.text =
                    match.homeHalfScore.toString() + ":" + match.awayHalfScore.toString()
                c_ratio.text = match.homeCorner.toString() + ":" + match.awayCorner.toString()
                if (match.state == 0) {
                    scoreIndicator.text = context.getString(R.string.soon)
                } else {
                    scoreIndicator.text =
                        match.homeScore.toString() + " : " + match.awayScore.toString()
                }
                homeTeam.text = match.homeEn
                awayTeam.text = match.awayEn
            }
        } else {
            val mHolder = holder as PastFutureAdapterBasketball
            mHolder.apply {
                val match =
                    matches[position] as com.example.myapplication.model.data.basketball.homepage.past.future.Match
                val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
                val sdf2 = SimpleDateFormat("EEE, dd MMM")
                matchTimeTv.text =
                    GeneralTools.return24HrsOnly(GeneralTools.returnTime(match.matchTime))
                stateNdate.text = sdf2.format(sdf.parse(match.matchTime).time)
                leagueNameShort.text = match.leagueEn
                homeNameTv.text = match.homeTeamEn
                awayNameTv.text = match.awayTeamEn
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