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
import com.example.myapplication.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.example.myapplication.model.data.homepage.leagueInfo.LeagueStanding
import com.example.myapplication.model.data.homepage.leagueInfo.any.LeagueStandingTypeGroupBase
import com.example.myapplication.model.data.standings.StandingsBase
import com.example.myapplication.model.data.standings.TeamInfo
import com.example.myapplication.model.data.standings.TotalStanding
import com.example.myapplication.model.data.standings.player.List
import com.example.myapplication.utils.SpewViewModel
import com.example.myapplication.utils.Status
import com.example.myapplication.view.fragments.homeFrags.adapter.adapterOfAdapter.MotherRecyclerViewAdapter
import com.example.myapplication.view.fragments.standings.adapter.PlayerStandingAdapter
import com.example.myapplication.view.fragments.standings.adapter.RankingsAdapter
import com.example.myapplication.view.fragments.standings.adapter.RankingsAdapterDetail

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
    var leagueStandingsBase:StandingsBase?=null

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

    }

    private fun populateRecyclerView(rv: RecyclerView, list: MutableList<List>?) {
        val properList = list as ArrayList<List>
        properList.add(0, List())
        rv.adapter=PlayerStandingAdapter(requireContext(),properList)
        rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }

    fun populateRecyclerView(data: BaseLeagueInfoHomePage){
        val rv=view?.findViewById<RecyclerView>(R.id.standing_recycler)
        try {
            when(data.leagueStanding[0]){
                is LeagueStandingTypeGroupBase ->{
                    populateRecyclerView(rv!!,data)
                }
                is LeagueStanding->{
                    populateRecyclerView(rv!!,data.leagueStanding[0] as LeagueStanding)
                }
                else->{

                }
            }

        }catch (e:Exception){
            println(e)
        }
    }

    private fun populateRecyclerView(rv: RecyclerView, data: BaseLeagueInfoHomePage) {
        val dataList=data.leagueStanding[0] as LeagueStandingTypeGroupBase
        val loadinto=view?.findViewById<ImageView>(R.id.league_logo)
        Glide.with(requireContext())
            .load(data.leagueData01[0].leagueLogo)
            .into(loadinto!!)
        rv.adapter=MotherRecyclerViewAdapter(requireContext(),data.leagueStanding[0] as LeagueStandingTypeGroupBase)

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
    private fun populateRecyclerView(rv: RecyclerView, data: LeagueStanding) {
        val loadinto=view?.findViewById<ImageView>(R.id.league_logo)
        Glide.with(requireContext())
            .load(data.leagueInfo.logo)
            .into(loadinto!!)
        val rankings = data.totalStandings as ArrayList<com.example.myapplication.model.data.homepage.leagueInfo.TotalStanding>
        rankings.add(0, com.example.myapplication.model.data.homepage.leagueInfo.TotalStanding())
        rv.adapter = RankingsAdapterDetail(
            requireContext(), data.teamInfo as ArrayList<com.example.myapplication.model.data.homepage.leagueInfo.TeamInfo>,
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