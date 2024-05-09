package com.challenge.sports.view.fragments.homeFrags.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import score.pro.R


import com.challenge.sports.model.data.news.details.OnPostDetailResponse
import com.challenge.sports.utils.SpewViewModel
import com.challenge.sports.utils.Status
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapterCommunicator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeagueInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeagueInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var adapterType: Int? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            adapterType = it.getInt(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val leagueFn=view.findViewById<TextView>(R.id.league_full_name_tv)
        val abbr=view.findViewById<TextView>(R.id.league_abbr_tv)
        val type=view.findViewById<TextView>(R.id.league_type_tv)
        val subLeague=view.findViewById<TextView>(R.id.subleague_tv)
        val totalRounds=view.findViewById<TextView>(R.id.total_rounds_tv)
        val currRound=view.findViewById<TextView>(R.id.curr_round_tv)
        val currSeason=view.findViewById<TextView>(R.id.curr_season_tv)
        val countryTv=view.findViewById<TextView>(R.id.country_tv)
        val rulesTv=view.findViewById<TextView>(R.id.rules_tv)
        val leagueLogo=view.findViewById<ImageView>(R.id.league_logo_iv)
        val vm=SpewViewModel.giveMeViewModel(requireActivity())



    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param adapterType Parameter 2.
         * @return A new instance of fragment LeagueInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, adapterType: Int) =
            LeagueInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, adapterType)
                }
            }
    }
}