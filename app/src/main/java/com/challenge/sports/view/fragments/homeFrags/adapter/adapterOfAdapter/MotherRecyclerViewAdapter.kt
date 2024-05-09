package com.challenge.sports.view.fragments.homeFrags.adapter.adapterOfAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import score.pro.R
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference

class MotherRecyclerViewAdapter(var context: Context):
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

    }

    override fun getItemCount(): Int {
     return 4
    }
}