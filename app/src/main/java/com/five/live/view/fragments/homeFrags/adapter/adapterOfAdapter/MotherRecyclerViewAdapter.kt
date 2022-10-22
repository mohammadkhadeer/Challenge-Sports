package com.five.live.view.fragments.homeFrags.adapter.adapterOfAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sports.myapplication.R
import com.five.live.model.data.homepage.leagueInfo.any.LeagueStandingTypeGroupBase
import com.five.live.utils.GeneralTools
import com.five.live.utils.SharedPreference

class MotherRecyclerViewAdapter(var context: Context, var data: LeagueStandingTypeGroupBase):
    RecyclerView.Adapter<MotherRecyclerViewAdapter.MotherRecyclerViewAdapterViewHolder>() {
    inner class MotherRecyclerViewAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var childRv=itemView.findViewById<RecyclerView>(R.id.child_rv)
        var groupNameTV=itemView.findViewById<TextView>(R.id.group_name)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MotherRecyclerViewAdapterViewHolder {
       return MotherRecyclerViewAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.mother_rv_item,parent,false))
    }

    override fun onBindViewHolder(holder: MotherRecyclerViewAdapterViewHolder, position: Int) {
            val element=data.list[0].score[0].groupScore[position]
            val group=when(GeneralTools.getLocale(context)){
                SharedPreference.CHINESE->{
                    element.groupCn
                }
                else->{
                    element.groupEn
                }
            }
            holder.groupNameTV.text=group
            holder.childRv.adapter=ChildRecyclerViewAdapter(context,element.scoreItems)
            holder.childRv.layoutManager=LinearLayoutManager(context)
    }

    override fun getItemCount(): Int {
     return data.list[0].score[0].groupScore.size
    }
}