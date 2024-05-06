package com.challenge.sports.view.HomeActivity.homeAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.challenge.sports.model.data.Matches.HotMatches
import com.challenge.sports.model.data.Matches.MatchesRoot
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import score.pro.R
import kotlin.collections.ArrayList

class MatchesAdapter (var context: Context, var dataList: MatchesRoot, var communicator: MainAdapterCommunicator)
    : RecyclerView.Adapter<MatchesAdapter.MainAdapterViewHolder>(),
    Filterable {

    companion object{
        val GMT_OFFSET_IN_MS:Long=28800000
        val HOUR_CONSTANT:Long=3600000
        val MINS_CONSTANT:Long=60000
    }

    var loadMore: Boolean=true
    var isMaxLoaded: Boolean=false
    var originalList = dataList.hotMatches

    var listMatches= ArrayList<HotMatches>()


    var categoryFilterType:CategoryFilterType=CategoryFilterType.ALL
    private var filterString=""

    init {
        sortMatchesOnCategory()
    }

    private fun sortMatchesOnCategory(){
        listMatches.clear()

        for (match in originalList ){
            listMatches.add(match)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MainAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
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

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder {
        return MainAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_matches,parent,false))
    }

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {

        val dataObject=dataList.hotMatches.get(position)


//        holder.leagueNameShort.text=dataObject.leagueInfo.enName
//
//        holder.homeNameTv.text=dataObject.homeInfo.enName
//        holder.awayNameTv.text=dataObject.awayInfo.enName


//        if (position==dataList.size-1&&loadMore)
//            communicator.onMessageFromAdapter(MainAdapterMessages.LOAD_MORE,position,0)
    }


    override fun getItemCount(): Int {
        return dataList.hotMatches.size
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