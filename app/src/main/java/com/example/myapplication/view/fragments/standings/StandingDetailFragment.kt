package com.example.myapplication.view.fragments.standings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.data.standings.StandingsBase
import com.example.myapplication.model.data.standings.TeamInfo
import com.example.myapplication.model.data.standings.TotalStanding
import com.example.myapplication.model.data.standings.player.List
import com.example.myapplication.utils.SpewViewModel
import com.example.myapplication.utils.Status
import com.example.myapplication.view.fragments.standings.adapter.PlayerStandingAdapter
import com.example.myapplication.view.fragments.standings.adapter.RankingsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StandingDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var leagueId: String? = null
    private var isLeague: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            leagueId = it.getString(ARG_PARAM1)
            isLeague = it.getBoolean(ARG_PARAM2)
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
        val vm = SpewViewModel.giveMeViewModel(requireActivity())
        if (isLeague!!) {
            vm.standingsLiveData.observe(requireActivity()) {
                if (it.status == Status.SUCCESS) {
                    populateRecyclerView(rv, it?.data!!)
                    vm.standingsLiveData.removeObservers(requireActivity())
                }
            }
            vm.makeStandingsCall(leagueId!!)
        } else {
            vm.playerStandingsLiveData.observe(requireActivity()) {
                if (it.status == Status.SUCCESS) {
                    val theList = it.data?.list
                    populateRecyclerView(rv, theList)
                    vm.standingsLiveData.removeObservers(requireActivity())
                }
            }
            vm.makePlayerStandingCall(leagueId!!)
        }
    }

    private fun populateRecyclerView(rv: RecyclerView, list: MutableList<List>?) {
        val properList = list as ArrayList<List>
        properList.add(0, List())
        rv.adapter=PlayerStandingAdapter(requireContext(),properList)
        rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }


    private fun populateRecyclerView(rv: RecyclerView, data: StandingsBase) {
        val loadinto=view?.findViewById<ImageView>(R.id.league_logo)
        Glide.with(requireContext())
            .load(data.leagueInfo.logo)
            .into(loadinto!!)
        val rankings = data.totalStandings as ArrayList<TotalStanding>
        rankings.add(0, TotalStanding())
        rv.adapter = RankingsAdapter(
            requireContext(), data.teamInfo as ArrayList<TeamInfo>,
            rankings
        )
        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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
                }
            }
    }
}