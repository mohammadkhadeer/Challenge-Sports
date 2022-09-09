package com.example.myapplication.view.fragments.homeFrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.liveOdds.BaseLiveOdds
import com.example.myapplication.model.data.homepage.new2.Match
import com.example.myapplication.model.data.homepage.new2.Odds
import com.example.myapplication.utils.SpewViewModel
import com.example.myapplication.utils.Status
import com.example.myapplication.view.adapters.ViewPagerAdapter
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapter
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapter.Companion.GMT_OFFSET_IN_MS
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapter.Companion.HOUR_CONSTANT
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapter.Companion.MINS_CONSTANT
import com.example.myapplication.view.fragments.homeFrags.adapter.OddsRvPopulator
import com.example.myapplication.view.fragments.homeFrags.detailFragment.IndexViewPagerFragment
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class IndexDisplayFragmnet : Fragment() {
    private var matchId: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            matchId = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_display_fragmnet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm = SpewViewModel.giveMeViewModel(requireActivity())

        vm.liveOddsLiveData.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    try {
                        populateViewPager(it.data!!)
                    }
                    catch (e: Exception) {

                    }
                }
                Status.ERROR -> {

                }

                Status.LOADING -> {

                }
            }
        }
        try {
            vm.makeLiveOddsCall(matchId!!)
        } catch (e: Exception) {

        }
    }


    private fun populateViewPager(data: BaseLiveOdds) {
        val viewPager = view?.findViewById<ViewPager2>(R.id.odds_viewPager)
        val fragsList = ArrayList<Fragment>()
        val asiaFull = IndexViewPagerFragment.newInstance(OddsRvPopulator.ASIA_FULL, "")
        fragsList.add(asiaFull)
        fragsList.add(IndexViewPagerFragment.newInstance(OddsRvPopulator._1X2_FULL, ""))
        fragsList.add(IndexViewPagerFragment.newInstance(OddsRvPopulator.OVERUNDER_FULL, ""))
        fragsList.add(IndexViewPagerFragment.newInstance(OddsRvPopulator.ASIA_HALF, ""))
        fragsList.add(IndexViewPagerFragment.newInstance(OddsRvPopulator.OVERUNDER_HALF, ""))
        fragsList.forEach {
            (it as IndexViewPagerFragment).setupData(data)
        }
        viewPager?.adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            requireActivity().lifecycle,
            fragsList
        )
        TabLayoutMediator(view?.findViewById(R.id.odds_tab_layout)!!,viewPager!!){tab,position->
            tab.text= when(position){
                0->{
                    OddsRvPopulator.ASIA_FULL
                }
                1->{
                    OddsRvPopulator._1X2_FULL
                }
                2->{
                    OddsRvPopulator.OVERUNDER_FULL
                }
                3->{
                    OddsRvPopulator.ASIA_HALF
                }
                4->{
                    OddsRvPopulator.OVERUNDER_HALF
                }
                else->{
                    "Other"
                }
            }

        }.attach()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IndexDisplayFragmnet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}