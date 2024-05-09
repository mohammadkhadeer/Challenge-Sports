package com.challenge.sports.view.fragments.homeFrags.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import score.pro.R
import com.challenge.sports.utils.SpewViewModel
import com.challenge.sports.utils.Status
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.challenge.sports.view.fragments.homeFrags.adapter.MatchesPopulatingAdapter
import com.challenge.sports.view.fragments.homeFrags.adapter.MatchesPopulatingAdapterBasketball
import com.challenge.sports.view.fragments.homeFrags.adapter.SortedOddsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AnalysisFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var matchId: String? = null
    private var adapterType: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            matchId = it.getString(ARG_PARAM1)
            adapterType = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val headToHeadRv=view.findViewById<RecyclerView>(R.id.head_to_head_rv)
        val homeRecentRv=view.findViewById<RecyclerView>(R.id.home_team_latest_rv)
        val awayRecentRv=view.findViewById<RecyclerView>(R.id.away_team_latest_rv)
        val homeOddsRv=view.findViewById<RecyclerView>(R.id.home_team_odds_rv)
        val awayOddsRv=view.findViewById<RecyclerView>(R.id.away_team_odds_rv)

        val vm=SpewViewModel.giveMeViewModel(requireActivity())
        if (adapterType==MainAdapterCommunicator.BASKETBALL_TYPE){

        }else{

        }

    }



    companion object {
        @JvmStatic
        fun newInstance(matchId: String, adapterType: Int) =
            AnalysisFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, matchId)
                    putInt(ARG_PARAM2, adapterType)
                }
            }
    }
}