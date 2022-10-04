package com.corescore.myapplication.view.fragments.homeFrags.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import corescore.myapplication.R
import com.corescore.myapplication.model.data.homepage.liveOdds.BaseLiveOdds
import com.corescore.myapplication.view.fragments.homeFrags.adapter.OddsRvPopulator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IndexViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IndexViewPagerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var oddsType: String? = null
    private var param2: String? = null
    private lateinit var data:BaseLiveOdds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            oddsType = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            populateRecyclerView()
        }catch (e:Exception){

        }

    }

     fun populateRecyclerView() {
        val rv=view?.findViewById<RecyclerView>(R.id.odds_rv)
        rv?.adapter= OddsRvPopulator(requireContext(),returnOddsListForType(),oddsType!!)
        rv?.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }

    fun returnOddsListForType(): List<Any> {
        return when(oddsType!!){
            OddsRvPopulator.ASIA_FULL->{
                data.list[0].handicap[0]
            }
            OddsRvPopulator._1X2_FULL->{
                data.list[0].europeOdds[0]
            }
            OddsRvPopulator.OVERUNDER_FULL->{
                data.list[0].overUnder[0]
            }
            OddsRvPopulator.ASIA_HALF->{
                data.list[0].handicapHalf[0]
            }
            OddsRvPopulator.OVERUNDER_HALF->{
                data.list[0].overUnderHalf[0]
            }
            else -> {
                data.list[0].handicap[0]
            }
        }
    }
    fun setupData(data: BaseLiveOdds){
       this.data=data
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IndexViewPagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(oddsType: String, param2: String) =
            IndexViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, oddsType)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}