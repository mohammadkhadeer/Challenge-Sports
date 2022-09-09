package com.example.myapplication.view.fragments.homeFrags.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.new2.Match
import com.example.myapplication.view.fragments.homeFrags.IndexDisplayFragmnet
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

 class MainAdapter(var context:Context,var dataList:ArrayList<Match>,var communicator: MainAdapterCommunicator) : RecyclerView.Adapter<MainAdapter.MainPageAdapterViewHolder>() {
     companion object{
         val GMT_OFFSET_IN_MS:Long=28800000
         val HOUR_CONSTANT:Long=3600000
         val MINS_CONSTANT:Long=60000
    }



    val displayFragment:Fragment?=null

    inner class MainPageAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
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
        var cornerRatio=itemView.findViewById<TextView>(R.id.c_ratio)
        var halfRatio=itemView.findViewById<TextView>(R.id.ht_ratio)
        var index_btn=itemView.findViewById<View>(R.id.index_bt)
        var analysis_bt=itemView.findViewById<View>(R.id.analysis_bt)
        var event_bt=itemView.findViewById<View>(R.id.event_bt)
        var fragment_container=itemView.findViewById<FrameLayout>(R.id.fragment_container)
        init {
            index_btn.setOnClickListener {
                communicator.onMessageFromAdapter(MainAdapterMessages.OPEN_INDEX,layoutPosition,fragment_container.id)
            /* if (displayFragment != null && displayFragment is IndexDisplayFragmnet) {

                 communicator.onMessageFromAdapter(MainAdapterMessages.CLOSE_INDEX,absoluteAdapterPosition,fragment_container.id)
             }else{
             }*/
            }
            analysis_bt.setOnClickListener {
                communicator.onMessageFromAdapter(MainAdapterMessages.OPEN_ANALYSIS,layoutPosition,fragment_container.id)
            }
            event_bt.setOnClickListener {
                communicator.onMessageFromAdapter(MainAdapterMessages.OPEN_EVENT,layoutPosition,fragment_container.id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageAdapterViewHolder {
        return MainPageAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_item,parent,false))
    }

    override fun onBindViewHolder(holder: MainPageAdapterViewHolder, position: Int) {

        val dataObject=dataList[position]
        holder.leagueNameShort.text=dataObject.leagueNameShort

        holder.homeNameTv.text=dataObject.homeName
        holder.awayNameTv.text=dataObject.awayName
        if (dataObject.state==0){
            holder.scoreIndicator.text=context.getString(R.string.soon)
        }else{
            holder.scoreIndicator.text=dataObject.homeScore.toString() + " : "+dataObject.awayScore.toString()
        }


        if (!dataObject.startTime.isNullOrEmpty()){
            // "matchTime": "2022/8/25 16:30:00",
            try {
                holder.stateNdate.text= returnStateDate(dataObject)
            }
            catch (e:Exception){
                Log.d("EXCEPTION!!!",e.toString())

            }
        }
        else{
            val sdf=SimpleDateFormat("yyyy/M/dd HH:mm:ss")
            val sdf2=SimpleDateFormat("EEE, dd MMM")
            holder.stateNdate.text= sdf2.format(sdf.parse(dataObject.matchTime).time)
        }
        holder.matchTimeTv.text=return24HrsOnly(returnTime(dataObject))

        if (dataObject.havOdds){
            try {
                holder.indexC1R1.text= dataObject.odds.handicap!![6].toString()
                holder.indexC2R1.text=dataObject.odds.handicap!![5].toString()
                holder.indexC3R1.text=dataObject.odds.handicap!![7].toString()


                holder.indexC1R2.text= dataObject.odds.overUnder!![6].toString()
                holder.indexC2R2.text=dataObject.odds.overUnder!![5].toString()
                holder.indexC3R2.text=dataObject.odds.overUnder!![7].toString()

                holder.cornerRatio.text=dataObject.homeCorner.toString()+":"+dataObject.awayCorner.toString()
                holder.halfRatio.text=dataObject.homeHalfScore.toString()+":"+dataObject.awayHalfScore.toString()
            }
            catch (e:Exception){

            }
        }
       val timeZone= TimeZone.getDefault()
        println(timeZone.getDisplayName(false,TimeZone.SHORT))

    }

    private fun return24HrsOnly(matchTime:String):String{
        val splitDateTime=matchTime.split(" ")[1].split(":")
        return splitDateTime[0]+":"+splitDateTime[1]
    }


    private fun returnTime(dataObject: Match):String{
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        val time=returnGMTToCurrentTimezone(returnGMTTimeInMs(dataObject.matchTime))
        return sdf.format(time)


    }

    private fun returnMinutes(dataObject: Match):String
    {
        val startTimeMs=returnGMTTimeInMs(dataObject.startTime)
        val matchTimeMs=returnGMTTimeInMs(dataObject.matchTime)
        var minutesElapsed=returnTimeInGmtCurrentLocale()-startTimeMs
        minutesElapsed /= 60000
       return minutesElapsed.toString()
    }

    private fun returnTimeInGmtCurrentLocale():Long{
        val timeZone= TimeZone.getDefault().getDisplayName(false,TimeZone.SHORT)
        val time=timeZone.substringAfter("+")
        var hours=time.substringBefore(":").toLong()
        var minutes=time.substringAfter(":").toLong()
        hours *= HOUR_CONSTANT
        minutes *=MINS_CONSTANT
        val timeFinal= if (timeZone.contains("+")){
            System.currentTimeMillis()-(hours+minutes)
        }else{
            System.currentTimeMillis()+(hours+minutes)
        }
        return timeFinal
    }
    private fun returnGMTToCurrentTimezone(timeMs:Long):Long{

        val timeZone= TimeZone.getDefault().getDisplayName(false,TimeZone.SHORT)
        val time=timeZone.substringAfter("+")
        var hours=time.substringBefore(":").toLong()
        var minutes=time.substringAfter(":").toLong()
        hours *= HOUR_CONSTANT
        minutes *=MINS_CONSTANT
        val timeFinal= if (timeZone.contains("+")){
            timeMs+(hours+minutes)
        }else{
            timeMs-(hours+minutes)
        }
        return timeFinal
    }

    private fun returnGMTTimeInMs(time: String): Long {
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        return sdf.parse(time).time - GMT_OFFSET_IN_MS
    }

    private fun returnStateDate(dataObject:Match):String{
        val sdf=SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        val sdf2=SimpleDateFormat("EEE, dd MMM")
       return when(dataObject.state){
            0->{
               sdf2.format(sdf.parse(dataObject.matchTime).time)
            }
            1->{
                "FH "+returnMinutes(dataObject)+" \'"
            }
            2->{
                "HT "+returnMinutes(dataObject)+" \'"
            }
            3->{
                "SH "+returnMinutes(dataObject)+" \'"
            }
            4-> {
                "OT "+returnMinutes(dataObject)+" \'"
            }
            5-> {
                "PT "+returnMinutes(dataObject)+" \'"
            }
            -1-> {
                "FT"
            }
            -10-> {
                "CL"
            }
            -11-> {
                "TBD"
            }
            -12-> {
                "CIH"
            }
            -13-> {
                "INT"
            }
            -14-> {
                "DEL"
            }
            else -> {
                "Soon"
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}