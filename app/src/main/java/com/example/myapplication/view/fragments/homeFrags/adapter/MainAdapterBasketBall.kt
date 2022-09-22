package com.example.myapplication.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.data.basketball.homepage.Match
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainAdapterBasketBall(var context:Context,var dataList:ArrayList<Match>):RecyclerView.Adapter<MainAdapterBasketBall.MainAdapterBasketBallViewHolder>() {
    inner class MainAdapterBasketBallViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var leagueNameShort=itemView.findViewById<TextView>(R.id.group_indicator)
        var homeNameTv=itemView.findViewById<TextView>(R.id.team_1_name)
        var awayNameTv=itemView.findViewById<TextView>(R.id.team_2_name)
        var scoreIndicator=itemView.findViewById<TextView>(R.id.score_indicator)
        var stateNdate=itemView.findViewById<TextView>(R.id.match_date)
        var matchTimeTv=itemView.findViewById<TextView>(R.id.match_time)
        var indexC1R1=itemView.findViewById<TextView>(R.id.index_c1_r1)
        var indexC1R2=itemView.findViewById<TextView>(R.id.index_c1_r2)
        var indexC2R1=itemView.findViewById<TextView>(R.id.index_c2_r1)
        var indexC2R2=itemView.findViewById<TextView>(R.id.index_c2_r2)
        var indexC3R1=itemView.findViewById<TextView>(R.id.index_c3_r1)
        var indexC3R2=itemView.findViewById<TextView>(R.id.index_c3_r2)
        var index_btn=itemView.findViewById<View>(R.id.index_bt)
        var analysis_bt=itemView.findViewById<View>(R.id.analysis_bt)
        var event_bt=itemView.findViewById<View>(R.id.event_bt)
        var brief_bt=itemView.findViewById<View>(R.id.briefing_button)
        var league_bt=itemView.findViewById<View>(R.id.league_bt)
        var home_q1=itemView.findViewById<TextView>(R.id.home_q1)
        var home_q2=itemView.findViewById<TextView>(R.id.home_q2)
        var home_q3=itemView.findViewById<TextView>(R.id.home_q3)
        var home_q4=itemView.findViewById<TextView>(R.id.home_q4)
        var home_f=itemView.findViewById<TextView>(R.id.home_f)
        var away_q1=itemView.findViewById<TextView>(R.id.away_q1)
        var away_q2=itemView.findViewById<TextView>(R.id.away_q2)
        var away_q3=itemView.findViewById<TextView>(R.id.away_q3)
        var away_q4=itemView.findViewById<TextView>(R.id.away_q4)
        var away_f=itemView.findViewById<TextView>(R.id.away_f)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapterBasketBallViewHolder {
        return MainAdapterBasketBallViewHolder(LayoutInflater.from(context).inflate(R.layout.home_match_item_basketball,parent,false))
    }

    override fun onBindViewHolder(holder: MainAdapterBasketBallViewHolder, position: Int) {

        val data=dataList[position]
        holder.apply {
            leagueNameShort.text=data.leagueEn
            homeNameTv.text=data.homeTeamEn
            awayNameTv.text=data.awayTeamEn
            if (data.matchState==0){
             scoreIndicator.text=context.getString(R.string.soon)
            }else {
                scoreIndicator.text = data.homeScore + ":" + data.awayScore
            }
            stateNdate.text=returnState(data)
            matchTimeTv.text=return24HrsOnly(returnTime(data))
            try {
                indexC1R1.text= if (data.odds.moneyLineAverage?.liveHomeWinRate==null) "" else data.odds.moneyLineAverage?.liveHomeWinRate.toString()
                indexC1R2.text=if (data.odds.moneyLineAverage?.liveAwayWinRate==null) "" else data.odds.moneyLineAverage?.liveAwayWinRate.toString()
                indexC2R1.text= if(data.odds.spread?.get(9).toString() == "null") "" else data.odds.spread?.get(9).toString()
                indexC2R2.text =if(data.odds.spread?.get(10).toString() == "null") "" else data.odds.spread?.get(10).toString()
                indexC3R1.text=if(data.odds.total?.get(9).toString() == "null") "" else data.odds.total?.get(9).toString()
                indexC3R2.text=if(data.odds.total?.get(10).toString() == "null") "" else data.odds.total?.get(10).toString()
            }catch (e:Exception){

            }

            home_q1.text=data.home1
            home_q2.text=data.home2
            home_q3.text=data.home3
            home_q4.text=data.home4
            home_f.text=data.homeScore
            away_q1.text=data.away1
            away_q2.text=data.away2
            away_q3.text=data.away3
            away_q4.text=data.away4
            away_f.text=data.awayScore

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    fun returnState(data: Match):String {
        val sdf= SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        val sdf2= SimpleDateFormat("EEE, dd MMM")
        return when(data.matchState){
            0->{
                sdf2.format(sdf.parse(data.matchTime).time)
            }
            1->{
                "Q1"
            }
            2->{
                "Q2"
            }
            3->{
                "Q3"
            }
            4->{
                "Q4"
            }
            5->{
                "OT 1"
            }
            6->{
                "OT 2"
            }
            7->{
                "OT 3"
            }
            50->{
                "HT"
            }
            -1->{
                "FT"
            }
            -2->{
                "TBD"
            }
            -3->{
                "INTR"
            }
            -4->{
                "C"
            }
            -5->{
                "DEL"
            }
            else->{
                ""
            }
        }
    }

    private fun return24HrsOnly(matchTime:String):String{
        val splitDateTime=matchTime.split(" ")[1].split(":")
        return splitDateTime[0]+":"+splitDateTime[1]
    }


    private fun returnTime(dataObject:Match):String{
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        val time=returnGMTToCurrentTimezone(returnGMTTimeInMs(dataObject.matchTime))
        return sdf.format(time)
    }

    private fun returnMinutes(dataObject: com.example.myapplication.model.data.homepage.new2.Match):String
    {
        val startTimeMs=returnGMTTimeInMs(dataObject.startTime)
        val matchTimeMs=returnGMTTimeInMs(dataObject.matchTime)
        var minutesElapsed=returnTimeInGmtCurrentLocale()-startTimeMs
        minutesElapsed /= 60000
        return minutesElapsed.toString()
    }

    private fun returnTimeInGmtCurrentLocale():Long{
        val timeZone= TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val time=timeZone.substringAfter("+")
        var hours=time.substringBefore(":").toLong()
        var minutes=time.substringAfter(":").toLong()
        hours *= MainAdapter.HOUR_CONSTANT
        minutes *= MainAdapter.MINS_CONSTANT
        val timeFinal= if (timeZone.contains("+")){
            System.currentTimeMillis()-(hours+minutes)
        }else{
            System.currentTimeMillis()+(hours+minutes)
        }
        return timeFinal
    }
    private fun returnGMTToCurrentTimezone(timeMs:Long):Long{

        val timeZone= TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val time=timeZone.substringAfter("+")
        var hours=time.substringBefore(":").toLong()
        var minutes=time.substringAfter(":").toLong()
        hours *= MainAdapter.HOUR_CONSTANT
        minutes *= MainAdapter.MINS_CONSTANT
        val timeFinal= if (timeZone.contains("+")){
            timeMs+(hours+minutes)
        }else{
            timeMs-(hours+minutes)
        }
        return timeFinal
    }

    private fun returnGMTTimeInMs(time: String): Long {
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        return sdf.parse(time).time - MainAdapter.GMT_OFFSET_IN_MS
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