package com.example.myapplication.view.fragments.standings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.view.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StandingBaseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewpager=view.findViewById<ViewPager2>(R.id.standings_viewpager)
        val frags=ArrayList<Fragment>()
        val fragsTitles=ArrayList<String>()
        fragsTitles.add(getString(R.string.team_standing))
        fragsTitles.add(getString(R.string.player_standing))
        frags.add(StandingDetailFragment.newInstance("36",true))

        frags.add(StandingDetailFragment.newInstance("36",false))

        val tabLayout=view.findViewById<TabLayout>(R.id.tab_layout_standings)
        viewpager.adapter=ViewPagerAdapter(requireActivity().supportFragmentManager,requireActivity().lifecycle,frags)
        TabLayoutMediator(tabLayout,viewpager){ tab,position->
         tab.text =fragsTitles[position]
        }.attach()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StandingBaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}