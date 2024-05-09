package com.challenge.sports.view.fragments.homeFrags.adapter.adapterOfAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import score.pro.R
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference

class ChildRecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<ChildRecyclerViewAdapter.ChildRecyclerViewAdapterViewHolder>() {
    inner class ChildRecyclerViewAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var ranking_tv=itemView.findViewById<TextView>(R.id.rank_text)
        var teamName=itemView.findViewById<TextView>(R.id.team_name)
        var matchPlayed=itemView.findViewById<TextView>(R.id.mp_tv)
        var won=itemView.findViewById<TextView>(R.id.w_tv)
        var draw=itemView.findViewById<TextView>(R.id.d_tv)
        var loss=itemView.findViewById<TextView>(R.id.l_tv)
        var gf=itemView.findViewById<TextView>(R.id.gf_tv)
        var ga=itemView.findViewById<TextView>(R.id.ga_tv)
        var pts=itemView.findViewById<TextView>(R.id.pts_tv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildRecyclerViewAdapterViewHolder {
        return ChildRecyclerViewAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.child_item,parent,false))
    }

    override fun onBindViewHolder(holder: ChildRecyclerViewAdapterViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
            return 4
         }

}