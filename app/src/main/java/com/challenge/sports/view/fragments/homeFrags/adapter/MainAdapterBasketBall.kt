package com.challenge.sports.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import score.pro.R

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainAdapterBasketBall(var context:Context
,var communicator: MainAdapterCommunicator):RecyclerView.Adapter<MainAdapterBasketBall.MainAdapterBasketBallViewHolder>(),
    Filterable {



    init {

    }

    inner class MainAdapterBasketBallViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var leagueNameShort = itemView.findViewById<TextView>(R.id.group_indicator)
        var homeNameTv = itemView.findViewById<TextView>(R.id.team_1_name)
        var awayNameTv = itemView.findViewById<TextView>(R.id.team_2_name)
        var scoreIndicator = itemView.findViewById<TextView>(R.id.score_indicator)
        var score_indicator_s = itemView.findViewById<TextView>(R.id.score_indicator_s)
        var point_cont = itemView.findViewById<LinearLayout>(R.id.point_cont)

        var stateNdate = itemView.findViewById<TextView>(R.id.match_date)
        var matchTimeTv = itemView.findViewById<TextView>(R.id.match_time)
        var indexC1R1 = itemView.findViewById<TextView>(R.id.index_c1_r1)
        var indexC1R2 = itemView.findViewById<TextView>(R.id.index_c1_r2)
        var indexC2R1 = itemView.findViewById<TextView>(R.id.index_c2_r1)
        var indexC2R2 = itemView.findViewById<TextView>(R.id.index_c2_r2)
        var indexC3R1 = itemView.findViewById<TextView>(R.id.index_c3_r1)
        var indexC3R2 = itemView.findViewById<TextView>(R.id.index_c3_r2)
        //var index_btn = itemView.findViewById<View>(R.id.index_bt)
        //var analysis_bt = itemView.findViewById<View>(R.id.analysis_bt)
        //var event_bt = itemView.findViewById<View>(R.id.event_bt)
        //var brief_bt = itemView.findViewById<View>(R.id.briefing_button)
        //var league_bt = itemView.findViewById<View>(R.id.league_bt)
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

//        init {
//            index_btn.setOnClickListener {
//                communicator.onMessageFromAdapter(
//                    MainAdapterMessages.OPEN_INDEX,
//                    absoluteAdapterPosition,
//                    MainAdapterCommunicator.BASKETBALL_TYPE
//                )
//            }
//            analysis_bt.setOnClickListener {
//                communicator.onMessageFromAdapter(
//                    MainAdapterMessages.OPEN_ANALYSIS,
//                    absoluteAdapterPosition,
//                    MainAdapterCommunicator.BASKETBALL_TYPE
//                )
//            }
//            league_bt.setOnClickListener {
//                communicator.onMessageFromAdapter(
//                    MainAdapterMessages.OPEN_LEAGUE,
//                    absoluteAdapterPosition,
//                    MainAdapterCommunicator.BASKETBALL_TYPE
//                )
//            }
//            brief_bt.setOnClickListener {
//                communicator.onMessageFromAdapter(
//                    MainAdapterMessages.OPEN_BRIEF,
//                    absoluteAdapterPosition,
//                    MainAdapterCommunicator.BASKETBALL_TYPE
//                )
//            }
//        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapterBasketBallViewHolder {
        return MainAdapterBasketBallViewHolder(
            LayoutInflater.from(context).inflate(R.layout.home_match_item_basketball_new, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainAdapterBasketBallViewHolder, position: Int) {




    }

    override fun getItemCount(): Int {
        return 5
    }




    private fun return24HrsOnly(matchTime: String): String {
        val splitDateTime = matchTime.split(" ")[1].split(":")
        return splitDateTime[0] + ":" + splitDateTime[1]
    }




    private fun returnTimeInGmtCurrentLocale(): Long {
        val timeZone = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val time = timeZone.substringAfter("+")
        var hours = time.substringBefore(":").toLong()
        var minutes = time.substringAfter(":").toLong()
        hours *= MainAdapter.HOUR_CONSTANT
        minutes *= MainAdapter.MINS_CONSTANT
        val timeFinal = if (timeZone.contains("+")) {
            System.currentTimeMillis() - (hours + minutes)
        } else {
            System.currentTimeMillis() + (hours + minutes)
        }
        return timeFinal
    }

    private fun returnGMTToCurrentTimezone(timeMs: Long): Long {

        val timeZone = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val time = timeZone.substringAfter("+")
        var hours = time.substringBefore(":").toLong()
        var minutes = time.substringAfter(":").toLong()
        hours *= MainAdapter.HOUR_CONSTANT
        minutes *= MainAdapter.MINS_CONSTANT
        val timeFinal = if (timeZone.contains("+")) {
            timeMs + (hours + minutes)
        } else {
            timeMs - (hours + minutes)
        }
        return timeFinal
    }

    private fun returnGMTTimeInMs(time: String): Long {
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        return sdf.parse(time).time - MainAdapter.GMT_OFFSET_IN_MS
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


    /*
        *
        0 - Upcoming
        1 - first quarter
        2 - second quarter
        3 - third quarter
        4 - fourth quarter
        5 - 1'OT
        6 - 2'OT
        7 - 3'OT
        50 - half time
        -1 - full time
        -2 - to be determined
        -3 - interrupt
        -4 = cancel
        -5 - delay
        *
        * */







    }