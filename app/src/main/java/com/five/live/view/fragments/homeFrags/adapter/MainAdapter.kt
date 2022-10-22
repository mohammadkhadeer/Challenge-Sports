package com.five.live.view.fragments.homeFrags.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sports.myapplication.R
import com.five.live.model.data.homepage.new2.Match
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

 class MainAdapter(var context:Context,var dataList:ArrayList<Match>,var communicator: MainAdapterCommunicator) : RecyclerView.Adapter<MainAdapter.MainPageAdapterViewHolder>(),Filterable {
     companion object{
         val GMT_OFFSET_IN_MS:Long=28800000
         val HOUR_CONSTANT:Long=3600000
         val MINS_CONSTANT:Long=60000
    }

     var loadMore: Boolean=true
     var isMaxLoaded: Boolean=false
     var originalList=dataList
     var liveMatches= ArrayList<Match>()
     var soonMatches= ArrayList<Match>()
     var ftMatches= ArrayList<Match>()
     var categoryFilterType:CategoryFilterType=CategoryFilterType.ALL
     private var filterString=""
     init {
         sortMatchesOnCategory()
     }


     private fun sortMatchesOnCategory(){
         soonMatches.clear()
         liveMatches.clear()
         ftMatches.clear()
         for (match in originalList){
             when(match.state){
                 0->{
                     soonMatches.add(match)
                     //soon
                 }
                 1->{
                     liveMatches.add(match)
                    // "FH "
                 }
                 2->{
                     liveMatches.add(match)
                   //  "HT "
                 }
                 3->{
                     liveMatches.add(match)
                    // "SH "
                 }
                 4-> {
                     liveMatches.add(match)
                     //"OT "
                 }
                 5-> {
                     liveMatches.add(match)
                    // "PT "
                 }
                 -1-> {
                     ftMatches.add(match)
                   //  "FT"
                 }
                 -10-> {
                   //  "CL"
                 }
                 -11-> {
                  //   "TBD"
                 }
                 -12-> {
                  //   "CIH"
                 }
                 -13-> {
                  //   "INT"
                 }
                 -14-> {
                 //    "DEL"
                 }
                 else -> {
                     soonMatches.add(match)
                    // "Soon"
                 }
             }
         }
     }


     override fun getItemViewType(position: Int): Int {
         return position
     }

    inner class MainPageAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var leagueNameShort=itemView.findViewById<TextView>(R.id.group_indicator)
        var homeNameTv=itemView.findViewById<TextView>(R.id.team_1_name)
        var awayNameTv=itemView.findViewById<TextView>(R.id.team_2_name)
        var scoreIndicatorHome=itemView.findViewById<TextView>(R.id.score_indicator_home)
        var scoreIndicatorAway=itemView.findViewById<TextView>(R.id.score_indicator_away)
        //var redCard=itemView.findViewById<TextView>(R.id.red_card)
        //var yellowCard=itemView.findViewById<TextView>(R.id.yellow_cards)
        var stateNdate=itemView.findViewById<TextView>(R.id.match_date)
        var matchTimeTv=itemView.findViewById<TextView>(R.id.match_time)
        var indexC1R1=itemView.findViewById<TextView>(R.id.index_c1_r1)
        var indexC1R2=itemView.findViewById<TextView>(R.id.index_c1_r2)
        var indexC2R1=itemView.findViewById<TextView>(R.id.index_c2_r1)
        var indexC2R2=itemView.findViewById<TextView>(R.id.index_c2_r2)
        var indexC3R1=itemView.findViewById<TextView>(R.id.index_c3_r1)
        var indexC3R2=itemView.findViewById<TextView>(R.id.index_c3_r2)
        var cornerRatio=itemView.findViewById<TextView>(R.id.c_ht_text)
        var halfRatio=itemView.findViewById<TextView>(R.id.ht_ratio)
        var index_btn=itemView.findViewById<View>(R.id.index_bt)
        var analysis_bt=itemView.findViewById<View>(R.id.analysis_bt)
        var event_bt=itemView.findViewById<View>(R.id.event_bt)
        var brief_bt=itemView.findViewById<View>(R.id.briefing_button)
        var league_bt=itemView.findViewById<View>(R.id.league_bt)
        var fragment_container=itemView.findViewById<FrameLayout>(R.id.fragment_container)
        //var normal_options_container=itemView.findViewById<View>(R.id.normal_options_container)
        var bottom_options_container=itemView.findViewById<View>(R.id.bottom_options_container)
        var odds_container=itemView.findViewById<View>(R.id.odds_container)
        init {
            itemView.setOnClickListener {
//                if (normal_options_container.visibility==VISIBLE){
//                    GeneralTools.flipReplaceAnimation(normal_options_container,bottom_options_container)
//                }else{
//                    GeneralTools.flipReplaceAnimation(bottom_options_container,normal_options_container)
//                }

            }

//            itemView.setOnLongClickListener(View.OnLongClickListener {
//                communicator.onMessageFromAdapter(MainAdapterMessages.LONG_PRESS_ITEM,absoluteAdapterPosition,fragment_container.id)
//                return@OnLongClickListener true
//            })

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
            brief_bt.setOnClickListener {
                communicator.onMessageFromAdapter(MainAdapterMessages.OPEN_BRIEF,layoutPosition,fragment_container.id)
            }
            league_bt.setOnClickListener {
                communicator.onMessageFromAdapter(MainAdapterMessages.OPEN_LEAGUE,layoutPosition,fragment_container.id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageAdapterViewHolder {
        return MainPageAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_item,parent,false))
    }

    override fun onBindViewHolder(holder: MainPageAdapterViewHolder, position: Int) {

        val dataObject=dataList[position]

        if (!dataObject.havOdds){
            holder.index_btn.visibility= GONE
            holder.odds_container.visibility= GONE
        }else{
            holder.index_btn.visibility= VISIBLE
        }
        if (!dataObject.havBriefing){
            holder.brief_bt.visibility= GONE
        }else{
            holder.brief_bt.visibility= VISIBLE
        }

        holder.leagueNameShort.text=dataObject.leagueName

        holder.homeNameTv.text=dataObject.homeName
        holder.awayNameTv.text=dataObject.awayName




        if (dataObject.state!=0){
              holder.scoreIndicatorHome.text=dataObject.homeScore.toString() +"  "+dataObject.homeYellow +"  "+dataObject.homeRed
              holder.scoreIndicatorAway.text=dataObject.awayScore.toString() +"  "+dataObject.awayYellow +"  "+dataObject.awayRed
        }else{

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
                  }
            catch (e:Exception){

            }
        }


        //holder.redCard.text=context.getString(R.string.red_card)+" "+dataObject.homeRed+":"+dataObject.awayRed
        //holder.yellowCard.text=context.getString(R.string.yellow_card)+" "+dataObject.homeYellow+":"+dataObject.awayYellow
        val c_ht="C= "+dataObject.homeCorner.toString()+":"+dataObject.awayCorner.toString()+" HT= "+dataObject.homeHalfScore.toString()+":"+dataObject.awayHalfScore.toString()
        holder.cornerRatio.text=c_ht
        if (position==dataList.size-1&&loadMore)
        communicator.onMessageFromAdapter(MainAdapterMessages.LOAD_MORE,position,0)
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

     override fun getFilter(): Filter {
         return object : Filter(){
             override fun performFiltering(charset: CharSequence?): FilterResults {
                 val filterResults=FilterResults()
                 val filterList=ArrayList<Match>()
                 val baseList=returnFilterData()
                if (charset.isNullOrEmpty()){
                    filterResults.apply {
                        count=baseList.size
                        values=baseList
                    }
                }else{
                    for (match in baseList){
                        if (match.homeName.startsWith(charset,true)
                            ||match.awayName.startsWith(charset,true)
                            ||match.leagueName.startsWith(charset,true)
                            ||match.leagueNameShort.startsWith(charset,true))
                        {
                            filterList.add(match)
                        }
                    }
                    filterResults.apply {
                        count=filterList.size
                        values=filterList
                    }
                }
                 return filterResults
             }

             @Suppress("UNCHECKED_CAST")
             @SuppressLint("NotifyDataSetChanged")
             override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                 if (!dataList.isNullOrEmpty()){
                     dataList=results?.values as ArrayList<Match>
                     filterString=p0.toString()
                     if (dataList.size==0&&!isMaxLoaded){
                         if (loadMore){
                             communicator.onMessageFromAdapter(MainAdapterMessages.LOAD_MORE,0,0)
                         }
                     }
                     notifyDataSetChanged()
                 }else{

                 }

             }
         }
     }

     fun updateList(list:List<Match>) {
         originalList.addAll(list)
         sortMatchesOnCategory()
         updateFilter()
     }
     fun setNewList(list:List<Match>,loadMore:Boolean){
         this.loadMore=loadMore
         originalList.clear()
         originalList.addAll(list)
         sortMatchesOnCategory()
         updateFilter()
     }

     fun setFilter(filter:CategoryFilterType){
         categoryFilterType=filter
         updateFilter()
     }
     fun updateFilter(){
         when(categoryFilterType){
             CategoryFilterType.ALL -> {
                 dataList=originalList
             }
             CategoryFilterType.LIVE -> {
                 dataList=liveMatches
             }
             CategoryFilterType.SOON -> {
                 dataList=soonMatches
             }
             CategoryFilterType.FT -> {
                 dataList=ftMatches
             }
             CategoryFilterType.OTHER -> {

             }
         }
         filter.filter(filterString)
         notifyDataSetChanged()
     }
     fun returnFilterData():ArrayList<Match>{
         return when(categoryFilterType){
             CategoryFilterType.ALL -> {
                 originalList
             }
             CategoryFilterType.LIVE -> {
                 liveMatches
             }
             CategoryFilterType.SOON -> {
                 soonMatches
             }
             CategoryFilterType.FT -> {
                 ftMatches
             }
             CategoryFilterType.OTHER -> {
                 originalList
             }
         }
     }
     enum class CategoryFilterType{
         ALL,
         LIVE,
         SOON,
         FT,
         OTHER
     }

 }