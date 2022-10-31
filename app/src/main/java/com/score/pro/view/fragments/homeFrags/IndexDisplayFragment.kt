package com.score.pro.view.fragments.homeFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import score.pro.R
import com.score.pro.model.data.basketball.odds.BasketballOddsBase
import com.score.pro.model.data.homepage.liveOdds.BaseLiveOdds
import com.score.pro.utils.SpewViewModel
import com.score.pro.utils.Status
import com.score.pro.view.adapters.ViewPagerAdapter
import com.score.pro.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.score.pro.view.fragments.homeFrags.adapter.OddsRvPopulator
import com.score.pro.view.fragments.homeFrags.adapter.OddsRvPopulatorBasketball
import com.score.pro.view.fragments.homeFrags.basketball.IndexViewpagerFragmentBasketball
import com.score.pro.view.fragments.homeFrags.detailFragment.IndexViewPagerFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class IndexDisplayFragmnet : Fragment() {
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
        return inflater.inflate(R.layout.fragment_index_display_fragmnet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm = SpewViewModel.giveMeViewModel(requireActivity())

        when(adapterType){
            MainAdapterCommunicator.BASKETBALL_TYPE->{
                vm.basketballLiveOdds.observe(requireActivity()){
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
                vm.makeOddsBasketballCall(matchId!!)
            }
            else->{
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
    private fun populateViewPager(data: BasketballOddsBase) {
        val viewPager = view?.findViewById<ViewPager2>(R.id.odds_viewPager)
        val fragsList = ArrayList<Fragment>()

        fragsList.add(IndexViewpagerFragmentBasketball.newInstance(OddsRvPopulatorBasketball.SPREAD, ""))
        fragsList.add(IndexViewpagerFragmentBasketball.newInstance(OddsRvPopulatorBasketball.TOTAL, ""))
        fragsList.forEach {
            (it as IndexViewpagerFragmentBasketball).setupData(data)
        }
        viewPager?.adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            requireActivity().lifecycle,
            fragsList
        )
        TabLayoutMediator(view?.findViewById(R.id.odds_tab_layout)!!,viewPager!!){tab,position->
            tab.text= when(position){
                0->{
                    OddsRvPopulatorBasketball.SPREAD
                }
                1->{
                    OddsRvPopulatorBasketball.TOTAL
                }
                else->{
                    "Other"
                }
            }

        }.attach()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, adapterType: Int) =
            IndexDisplayFragmnet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, adapterType)
                }
            }
    }
}