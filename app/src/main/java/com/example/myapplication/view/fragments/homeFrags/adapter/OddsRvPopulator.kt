package com.example.myapplication.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class OddsRvPopulator(var context: Context, var dataList: List<Any>, var typeOdds: String) :
    RecyclerView.Adapter<OddsRvPopulator.OddsViewHolder>() {

    companion object {
        val ASIA_FULL = "asia(full)"
        val _1X2_FULL = "1x2(full)"
        val OVERUNDER_FULL = "overunder(full)"
        val ASIA_HALF = "asia(1st half)"
        val OVERUNDER_HALF = "overunder(1st half)"
    }

    inner class OddsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var companyName = itemView.findViewById<TextView>(R.id.company_tv)
        var homeOne = itemView.findViewById<TextView>(R.id.home_one_odds)
        var liveHandicap = itemView.findViewById<TextView>(R.id.live_handicap)
        var awayOne = itemView.findViewById<TextView>(R.id.away_one_odds)
        var homeTwo = itemView.findViewById<TextView>(R.id.home_two_odds)
        var initHandicap = itemView.findViewById<TextView>(R.id.init_handicap)
        var awayTwo = itemView.findViewById<TextView>(R.id.away_two_odds)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OddsViewHolder {
        return OddsViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.odds_item, parent, false))
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: OddsViewHolder, position: Int) {
        val data=dataList[position] as List<*>
        holder.companyName.text = returnCompanyName(data as List<Any>)

        try {
            when (typeOdds) {
                ASIA_FULL->{
                    holder.homeOne.text=data[6].toString()
                    holder.liveHandicap.text=data[5].toString()
                    holder.awayOne.text=data[7].toString()
                    holder.homeTwo.text=data[3].toString()
                    holder.initHandicap.text=data[2].toString()
                    holder.awayTwo.text=data[4].toString()
                }
                _1X2_FULL->{
                    holder.homeOne.text=data[5].toString()
                    holder.liveHandicap.text=data[6].toString()
                    holder.awayOne.text=data[7].toString()
                    holder.homeTwo.text=data[4].toString()
                    holder.initHandicap.text=data[3].toString()
                    holder.awayTwo.text=data[4].toString()
                }
                OVERUNDER_FULL->{
                    holder.homeOne.text=data[6].toString()
                    holder.liveHandicap.text=data[5].toString()
                    holder.awayOne.text=data[7].toString()
                    holder.homeTwo.text=data[3].toString()
                    holder.initHandicap.text=data[2].toString()
                    holder.awayTwo.text=data[4].toString()
                }
                ASIA_HALF->{
                    holder.homeOne.text=data[6].toString()
                    holder.liveHandicap.text=data[5].toString()
                    holder.awayOne.text=data[7].toString()
                    holder.homeTwo.text=data[3].toString()
                    holder.initHandicap.text=data[2].toString()
                    holder.awayTwo.text=data[4].toString()
                }
                OVERUNDER_HALF->{
                    holder.homeOne.text=data[6].toString()
                    holder.liveHandicap.text=data[5].toString()
                    holder.awayOne.text=data[7].toString()
                    holder.homeTwo.text=data[3].toString()
                    holder.initHandicap.text=data[2].toString()
                    holder.awayTwo.text=data[4].toString()
                }
            }
        }catch (e:Exception){

        }


    }

    private fun returnCompanyName(list: List<Any>): String {
        //TODO: FIX HERE

        return when ((list[1] as Double).toInt()) {
            1 -> {
                "Macauslot"
            }
            3 -> {
                "Crown"
            }
            4 -> {
                "Landbrokes"
            }
            7 -> {
                "SNAI"
            }
            8 -> {
                "Bet365"
            }
            9 -> {
                "William Hill"
            }
            12 -> {
                "Easybets"
            }
            14 -> {
                "Vcbet"
            }
            17 -> {
                "Mansion88"
            }
            19 -> {
                "Interwetten"
            }
            22 -> {
                "10BET"
            }
            23 -> {
                "188BET"
            }
            24 -> {
                "12bet"
            }
            31 -> {
                "SBOBET"
            }
            35 -> {
                "Wewbet"
            }
            42 -> {
                "18bet"
            }
            45 -> {
                "ManbetX"
            }
            47 -> {
                "Pinnacle"
            }
            48 -> {
                "HK Jockey Club"
            }
            else -> {
                "Other"
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}