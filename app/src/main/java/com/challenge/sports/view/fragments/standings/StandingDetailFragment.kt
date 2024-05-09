package com.challenge.sports.view.fragments.standings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import score.pro.R


import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.utils.SpewViewModel
import com.challenge.sports.utils.Status
import com.challenge.sports.view.fragments.homeFrags.adapter.adapterOfAdapter.MotherRecyclerViewAdapter
import com.challenge.sports.view.fragments.standings.adapter.PlayerStandingAdapter
import com.challenge.sports.view.fragments.standings.adapter.RankingsAdapter
import com.challenge.sports.view.fragments.standings.adapter.RankingsAdapterDetail

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"


class StandingDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var leagueId: String? = null
    private var isLeague: Boolean? = null
    private var callApi:Boolean?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            leagueId = it.getString(ARG_PARAM1)
            isLeague = it.getBoolean(ARG_PARAM2)
            callApi=it.getBoolean(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = view.findViewById<RecyclerView>(R.id.standing_recycler)
        if (callApi!!){
            val vm = SpewViewModel.giveMeViewModel(requireActivity())
            if (isLeague!!) {
                view.findViewById<View>(R.id.team_standing_view).visibility=View.VISIBLE

            } else {
                   view.findViewById<View>(R.id.player_standing_view).visibility=View.VISIBLE
                try {

                }catch (e:Exception){

                }


            }
        }
        else{
            try {

            }catch (e:Exception){

            }

        }

    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StandingDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(leagueId: String, isLeague: Boolean) =
            StandingDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, leagueId)
                    putBoolean(ARG_PARAM2, isLeague)
                    putBoolean(ARG_PARAM3, true)
                }
            }
        @JvmStatic
        fun newInstance(leagueId: String, isLeague: Boolean,callApi:Boolean) =
            StandingDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, leagueId)
                    putBoolean(ARG_PARAM2, isLeague)
                    putBoolean(ARG_PARAM3, callApi)
                }
            }
    }
}