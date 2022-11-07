package com.football.live.view.fragments.standings

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
import com.football.live.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.football.live.model.data.homepage.leagueInfo.LeagueStanding
import com.football.live.model.data.homepage.leagueInfo.any.LeagueStandingTypeGroupBase
import com.football.live.model.data.standings.StandingsBase
import com.football.live.model.data.standings.TeamInfo
import com.football.live.model.data.standings.TotalStanding
import com.football.live.model.data.standings.player.List
import com.football.live.utils.GeneralTools
import com.football.live.utils.SharedPreference
import com.football.live.utils.SpewViewModel
import com.football.live.utils.Status
import com.football.live.view.fragments.homeFrags.adapter.adapterOfAdapter.MotherRecyclerViewAdapter
import com.football.live.view.fragments.standings.adapter.PlayerStandingAdapter
import com.football.live.view.fragments.standings.adapter.RankingsAdapter
import com.football.live.view.fragments.standings.adapter.RankingsAdapterDetail

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
    var leagueStandingsBase:BaseLeagueInfoHomePage?=null

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
                vm.leagueInfoLiveData.observe(requireActivity()) {
                    when(it.status){
                        Status.SUCCESS -> {
                            try {
                                var name= when(GeneralTools.getLocale(requireContext())){
                                    SharedPreference.ENGLISH->{
                                        it?.data?.leagueData01?.get(0)?.nameEn
                                    }
                                    SharedPreference.CHINESE->{
                                        it?.data?.leagueData01?.get(0)?.nameChs
                                    }
                                    SharedPreference.INDONESIAN->{
                                        it?.data?.leagueData01?.get(0)?.nameId
                                    }
                                    SharedPreference.VIETNAMESE->{
                                        it?.data?.leagueData01?.get(0)?.nameVi
                                    }
                                    SharedPreference.THAI->{
                                        it?.data?.leagueData01?.get(0)?.nameTh
                                    }
                                    else->{
                                        it?.data?.leagueData01?.get(0)?.nameEn
                                    }

                                }
                                view.findViewById<TextView>(R.id.league_name).text= name+" "+ it?.data?.leagueData01?.get(0)?.currSeason
                                populateRecyclerView(view,it?.data!!)
                                view.findViewById<View>(R.id.loading_layout).visibility=View.GONE
                            }catch (e:Exception){

                            }
                        }
                        Status.ERROR -> {
                            view.findViewById<View>(R.id.error_layout).visibility=View.VISIBLE
                        }
                        Status.LOADING -> {
                            view.findViewById<View>(R.id.error_layout).visibility=View.GONE
                            view.findViewById<View>(R.id.loading_layout).visibility=View.VISIBLE
                        }
                    }

                     //   vm.standingsLiveData.removeObservers(requireActivity())

                }
                vm.getLeagueInfoForMatch(leagueId!!,"0","0")
            } else {
                   view.findViewById<View>(R.id.player_standing_view).visibility=View.VISIBLE
                try {
                    vm.playerStandingsLiveData.observe(requireActivity()) {

                        when(it.status){
                            Status.SUCCESS -> {

                                val theList = it.data?.list
                                        try {
                                            view.findViewById<TextView>(R.id.league_name).visibility=View.GONE
                                            populateRecyclerView(rv, theList)
                                            view.findViewById<View>(R.id.loading_layout).visibility=View.GONE
                                        }catch (e:Exception){

                                        }
                            }
                            Status.ERROR -> {
                                view.findViewById<View>(R.id.error_layout).visibility=View.VISIBLE
                            }
                            Status.LOADING -> {
                                view.findViewById<View>(R.id.error_layout).visibility=View.GONE
                                view.findViewById<View>(R.id.loading_layout).visibility=View.VISIBLE
                            }
                        }

                          //  vm.standingsLiveData.removeObservers(requireActivity())

                    }
                    vm.makePlayerStandingCall(leagueId!!)
                }catch (e:Exception){

                }


            }
        }
        else{
            try {
                populateRecyclerView(leagueStandingsBase!!)
            }catch (e:Exception){

            }

        }

    }

    private fun populateRecyclerView(rv: RecyclerView, list: MutableList<List>?) {
        val properList = list as ArrayList<List>
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
            leagueStandingsBase=data
            println(e)
        }
    }
    fun populateRecyclerView(view: View,data: BaseLeagueInfoHomePage){
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
            leagueStandingsBase=data
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
        rv.layoutManager=LinearLayoutManager(requireContext())
    }


    private fun populateRecyclerView(rv: RecyclerView, data: StandingsBase) {
        val loadinto=view?.findViewById<ImageView>(R.id.league_logo)
        Glide.with(requireContext())
            .load(data.leagueInfo.logo)
            .into(loadinto!!)
        val rankings = data.totalStandings as ArrayList<TotalStanding>
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
        val rankings = data.totalStandings as ArrayList<com.football.live.model.data.homepage.leagueInfo.TotalStanding>
        rv.adapter = RankingsAdapterDetail(
            requireContext(), data.teamInfo as ArrayList<com.football.live.model.data.homepage.leagueInfo.TeamInfo>,
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