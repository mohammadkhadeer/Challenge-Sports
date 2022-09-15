package com.example.myapplication.view.fragments.homeFrags.adapter.adapterOfAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.leagueInfo.any.LeagueStandingTypeGroupBase

class MotherRecyclerViewAdapter(var context: Context, var data: LeagueStandingTypeGroupBase):
    RecyclerView.Adapter<MotherRecyclerViewAdapter.MotherRecyclerViewAdapterViewHolder>() {
    inner class MotherRecyclerViewAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MotherRecyclerViewAdapterViewHolder {
       return MotherRecyclerViewAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.mother_rv_item,parent,false))
    }

    override fun onBindViewHolder(holder: MotherRecyclerViewAdapterViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
     return data.list[0].score[0].groupScore.size
    }
}