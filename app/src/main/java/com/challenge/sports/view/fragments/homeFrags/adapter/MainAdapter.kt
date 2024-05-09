package com.challenge.sports.view.fragments.homeFrags.adapter

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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import score.pro.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


 class MainAdapter(var context:Context,var communicator: MainAdapterCommunicator)
     : RecyclerView.Adapter<MainAdapter.MainPageAdapterViewHolder>(),Filterable {
     var loadMore: Boolean=true
     var isMaxLoaded: Boolean=false

     var categoryFilterType:CategoryFilterType=CategoryFilterType.ALL
     private var filterString=""

     companion object{
         val GMT_OFFSET_IN_MS:Long=28800000
         val HOUR_CONSTANT:Long=3600000
         val MINS_CONSTANT:Long=60000
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageAdapterViewHolder {
         //home_page_item the old adapter
         return MainPageAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.football_adapter,parent,false))
     }


     override fun onBindViewHolder(holder: MainPageAdapterViewHolder, position: Int) {
















         //holder.redCard.text=context.getString(R.string.red_card)+" "+dataObject.homeRed+":"+dataObject.awayRed
         //holder.yellowCard.text=context.getString(R.string.yellow_card)+" "+dataObject.homeYellow+":"+dataObject.awayYellow
//         val c_ht="C= "+dataObject.homeCorner.toString()+":"+dataObject.awayCorner.toString()+" HT= "+dataObject.homeHalfScore.toString()+":"+dataObject.awayHalfScore.toString()





     }


     init {

     }


     override fun getItemViewType(position: Int): Int {
         return position
     }

    inner class MainPageAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var leagueNameShort=itemView.findViewById<TextView>(R.id.group_indicator)
        var homeNameTv=itemView.findViewById<TextView>(R.id.team_1_name)
        var awayNameTv=itemView.findViewById<TextView>(R.id.team_2_name)

        var score_team1=itemView.findViewById<TextView>(R.id.score_team1)
        var score_team2=itemView.findViewById<TextView>(R.id.score_team2)
        var team_1_logo=itemView.findViewById<ImageView>(R.id.team_1_logo)
        var team_2_logo=itemView.findViewById<ImageView>(R.id.team_2_logo)

        var yallow_card_Home=itemView.findViewById<TextView>(R.id.yallow_card_Home)
        var red_card_Home=itemView.findViewById<TextView>(R.id.red_card_Home)
        var yallow_card_Away=itemView.findViewById<TextView>(R.id.yallow_card_Away)
        var red_card_Away=itemView.findViewById<TextView>(R.id.red_card_Away)

        var cards_con=itemView.findViewById<LinearLayout>(R.id.cards_con)


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
        var cRatio=itemView.findViewById<TextView>(R.id.c_text)
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


    private fun return24HrsOnly(matchTime:String):String{
        val splitDateTime=matchTime.split(" ")[1].split(":")
        return splitDateTime[0]+":"+splitDateTime[1]
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


    override fun getItemCount(): Int {
        return 5
    }



     fun setFilter(filter:CategoryFilterType){
         categoryFilterType=filter

     }


     enum class CategoryFilterType{
         ALL,
         LIVE,
         SOON,
         FT,
         OTHER
     }

     override fun getFilter(): Filter {
         TODO("Not yet implemented")
     }

 }