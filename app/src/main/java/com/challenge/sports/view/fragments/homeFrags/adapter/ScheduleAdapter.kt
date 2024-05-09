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




    }

    override fun getItemCount(): Int {
        return matches.size
    }
}