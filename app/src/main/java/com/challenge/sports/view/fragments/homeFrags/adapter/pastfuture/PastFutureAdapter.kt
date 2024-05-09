package com.challenge.sports.view.fragments.homeFrags.adapter.pastfuture

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
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapterCommunicator
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

        }

        else {
            val mHolder = holder as PastFutureAdapterBasketball

        }


    }

    override fun getItemCount(): Int {
        return matches.size
    }
}