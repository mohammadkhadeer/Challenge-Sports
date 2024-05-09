package com.challenge.sports.view.fragments.homeFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import score.pro.R
import com.challenge.sports.model.data.news.details.OnPostDetailResponse
import com.challenge.sports.view.adapters.ViewPagerAdapter
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.challenge.sports.view.fragments.homeFrags.detailFragment.LeagueInfoFragment
import com.challenge.sports.view.fragments.standings.StandingDetailFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LeagueBaseFragmet : Fragment() {
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
        return inflater.inflate(R.layout.fragment_league_base_fragmet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewpager = view.findViewById<ViewPager2>(R.id.league_viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.league_tab_layout)
        val fragsList = ArrayList<Fragment>()


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