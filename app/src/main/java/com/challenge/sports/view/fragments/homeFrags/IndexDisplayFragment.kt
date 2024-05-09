package com.challenge.sports.view.fragments.homeFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import score.pro.R
import com.challenge.sports.utils.SpewViewModel
import com.challenge.sports.utils.Status
import com.challenge.sports.view.adapters.ViewPagerAdapter
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.challenge.sports.view.fragments.homeFrags.adapter.OddsRvPopulator
import com.challenge.sports.view.fragments.homeFrags.adapter.OddsRvPopulatorBasketball
import com.challenge.sports.view.fragments.homeFrags.basketball.IndexViewpagerFragmentBasketball
import com.challenge.sports.view.fragments.homeFrags.detailFragment.IndexViewPagerFragment
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

            }
            else->{

            }

        }

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