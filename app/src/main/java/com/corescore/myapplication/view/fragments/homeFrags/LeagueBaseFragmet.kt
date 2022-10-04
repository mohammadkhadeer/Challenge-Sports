package com.corescore.myapplication.view.fragments.homeFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import corescore.myapplication.R
import com.corescore.myapplication.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.corescore.myapplication.model.data.homepage.new2.Match
import com.corescore.myapplication.model.data.news.details.OnPostDetailResponse
import com.corescore.myapplication.view.adapters.ViewPagerAdapter
import com.corescore.myapplication.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.corescore.myapplication.view.fragments.homeFrags.detailFragment.LeagueInfoFragment
import com.corescore.myapplication.view.fragments.standings.StandingDetailFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LeagueBaseFragmet : Fragment() {
    private var param1: String? = null
    private var adapterType: Int? = null
    private var match: Match? = null
    private var bbMatch: com.corescore.myapplication.model.data.basketball.homepage.Match? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            adapterType = it.getInt(ARG_PARAM2)
        }
    }

    fun setData(match: Match) {
        this.match = match
    }
    fun setData(match: com.corescore.myapplication.model.data.basketball.homepage.Match) {
        this.bbMatch = match
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_base_fragmet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewpager = view.findViewById<ViewPager2>(R.id.league_viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.league_tab_layout)
        val fragsList = ArrayList<Fragment>()
        try {
            val frag=LeagueInfoFragment.newInstance(if (adapterType==MainAdapterCommunicator.BASKETBALL_TYPE)bbMatch!!.leagueId.toString()else match!!.leagueId.toString()
                ,adapterType!!)
            if (adapterType==MainAdapterCommunicator.BASKETBALL_TYPE){
                frag.bbMatch=bbMatch
                fragsList.add(frag)
            }else{
                frag.match=match
                fragsList.add(frag)
                val frag2=StandingDetailFragment.newInstance(match!!.leagueId.toString(), true,false)
                fragsList.add(frag2)
                frag.OnPostDetailResponse=object : OnPostDetailResponse<BaseLeagueInfoHomePage>{
                    override fun onSuccess(responseBody: BaseLeagueInfoHomePage) {
                        frag2.populateRecyclerView(responseBody)
                    }

                    override fun onFailure(message: String) {
                        println(message)
                    }

                    override fun onLoading(message: String) {

                    }
                }

            }

            viewpager.adapter = ViewPagerAdapter(
                requireActivity().supportFragmentManager,
                requireActivity().lifecycle,
                fragsList
            )
            TabLayoutMediator(tabLayout, viewpager) { tab, pos ->
                tab.text = when (pos) {
                    0 -> {
                        getString(R.string.league_cup_info)
                    }
                    1 -> {
                        getString(R.string.league_standings)
                    }
                    else -> {
                        getString(R.string.league_cup_info)
                    }
                }
            }.attach()
        }catch (e:Exception){
            println(e)
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(matchId: String, adapterType: Int) =
            LeagueBaseFragmet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, matchId)
                    putInt(ARG_PARAM2, adapterType)
                }
            }
    }
}