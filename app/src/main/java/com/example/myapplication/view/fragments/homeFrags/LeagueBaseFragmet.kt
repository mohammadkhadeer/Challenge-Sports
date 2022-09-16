package com.example.myapplication.view.fragments.homeFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.example.myapplication.model.data.homepage.new2.Match
import com.example.myapplication.model.data.news.details.OnPostDetailResponse
import com.example.myapplication.view.adapters.ViewPagerAdapter
import com.example.myapplication.view.fragments.homeFrags.detailFragment.LeagueInfoFragment
import com.example.myapplication.view.fragments.standings.StandingDetailFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeagueBaseFragmet.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeagueBaseFragmet : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var match: Match? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun setData(match: Match) {
        this.match = match
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
            val frag=LeagueInfoFragment.newInstance(match!!.leagueId.toString(), "")
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

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeagueBaseFragmet.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeagueBaseFragmet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}