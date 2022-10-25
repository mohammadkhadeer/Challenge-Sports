package com.score.pro.view.fragments.homeFrags

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.score.pro.sharedPreferences.FootballOrBasketball.getFootballOrBasketballFromSP
import com.score.pro.sharedPreferences.FootballOrBasketball.saveFootballOrBasketballInSP
import com.score.pro.view.BaseActivity
import sports.myapplication.R

class FootballBasketDownMenu : BottomSheetDialogFragment() {

    companion object {

        const val TAG = "FootballBasketDownMenu"

    }
    override fun onAttach(context: Context) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.football_basket_down_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val football_radio=view.findViewById<RelativeLayout>(R.id.football_radio)
        val basketball_radio=view.findViewById<RelativeLayout>(R.id.basketball_radio)
        val football_cont=view.findViewById<RelativeLayout>(R.id.football_cont)
        val basketball_cont=view.findViewById<RelativeLayout>(R.id.basketball_cont)

        football_cont.setOnClickListener(View.OnClickListener {
            football_radio.visibility=View.VISIBLE
            basketball_radio.visibility=View.GONE
            saveFootballOrBasketballInSP(requireContext(),"football")
            (activity as BaseActivity?)?.footballCase()
            dismiss()
        })

        basketball_cont.setOnClickListener(View.OnClickListener {
            football_radio.visibility=View.GONE
            basketball_radio.visibility=View.VISIBLE
            saveFootballOrBasketballInSP(requireContext(),"basketball")

            (activity as BaseActivity?)?.basketballCase()
            dismiss()
        })

        if (!getFootballOrBasketballFromSP(requireContext()).isEmpty()
            &&
            (getFootballOrBasketballFromSP(requireContext()).equals("empty")
            || getFootballOrBasketballFromSP(requireContext()).equals("football")))
        {
            football_radio.visibility=View.VISIBLE
        }else{
            basketball_radio.visibility=View.VISIBLE
        }

    }

}