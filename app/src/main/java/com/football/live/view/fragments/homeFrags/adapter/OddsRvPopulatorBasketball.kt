package com.football.live.view.fragments.homeFrags.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import score.pro.R

class OddsRvPopulatorBasketball(var context: Context, var dataList: List<Any>, var typeOdds: String) :
    RecyclerView.Adapter<OddsRvPopulatorBasketball.OddsViewHolder>() {

    companion object {
        val SPREAD = "Spread"
        val TOTAL = "Total"
    }

    inner class OddsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var companyName = itemView.findViewById<TextView>(R.id.company_tv)
        var homeOne = itemView.findViewById<TextView>(R.id.home_one_odds)
        var fullHandicap = itemView.findViewById<TextView>(R.id.full_handicap)
        var awayOne = itemView.findViewById<TextView>(R.id.away_one_odds)
        var homeTwo = itemView.findViewById<TextView>(R.id.home_two_odds)
        var halfHandicap = itemView.findViewById<TextView>(R.id.half_handicap)
        var awayTwo = itemView.findViewById<TextView>(R.id.away_two_odds)
        var homeThree = itemView.findViewById<TextView>(R.id.home_three_odds)
        var onepartHandicap = itemView.findViewById<TextView>(R.id.onepart_handicap_odds)
        var awayThree = itemView.findViewById<TextView>(R.id.away_three_odds)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OddsViewHolder {
        return OddsViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.odds_item_basketball, parent, false))
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: OddsViewHolder, position: Int) {
        val data=dataList[position] as List<*>
        holder.companyName.text = returnCompanyName(data as List<Any>)
        try {
            when (typeOdds) {
                SPREAD->{
                    holder.homeOne.text=data[9].toString()
                    holder.fullHandicap.text=data[8].toString()
                    holder.awayOne.text=data[10].toString()
                    holder.homeTwo.text=data[6].toString()
                    holder.halfHandicap.text=data[5].toString()
                    holder.awayTwo.text=data[7].toString()
                    holder.homeThree.text=data[3].toString()
                    holder.onepartHandicap.text=data[2].toString()
                    holder.awayThree.text=data[4].toString()
                }
                TOTAL->{
                    holder.homeOne.text=data[9].toString()
                    holder.fullHandicap.text=data[8].toString()
                    holder.awayOne.text=data[10].toString()
                    holder.homeTwo.text=data[6].toString()
                    holder.halfHandicap.text=data[5].toString()
                    holder.awayTwo.text=data[7].toString()
                    holder.homeThree.text=data[3].toString()
                    holder.onepartHandicap.text=data[2].toString()
                    holder.awayThree.text=data[4].toString()
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