package com.five.live.view.fragments.homeFrags.basketball

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sports.myapplication.R
import com.five.live.model.data.basketball.odds.BasketballOddsBase
import com.five.live.view.fragments.homeFrags.adapter.OddsRvPopulatorBasketball

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IndexViewpagerFragmentBasketball.newInstance] factory method to
 * create an instance of this fragment.
 */
class IndexViewpagerFragmentBasketball : Fragment() {


    // TODO: Rename and change types of parameters
    private var param2: String? = null
    private var oddsType: String?=null

    var data:BasketballOddsBase?=null
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
        return inflater.inflate(R.layout.fragment_index_viewpager_basketball, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateRecyclerView()
    }

    fun setupData(data: BasketballOddsBase) {
        this.data=data
    }

    fun populateRecyclerView() {
        val rv=view?.findViewById<RecyclerView>(R.id.odds_rv)
        rv?.adapter= OddsRvPopulatorBasketball(requireContext(),returnOddsListForType(),oddsType!!)
        rv?.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }
    private fun returnOddsListForType(): List<Any> {
        return when(oddsType!!){
            OddsRvPopulatorBasketball.SPREAD->{
                val list= data?.list?.get(0)?.spread?.get(0)
             list?:ArrayList()
            }
            OddsRvPopulatorBasketball.TOTAL->{
                val list= data?.list?.get(0)?.total?.get(0)
                list?:ArrayList()

            }
            else -> {
                val list= data?.list?.get(0)?.spread?.get(0)
                list?:ArrayList()

            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IndexViewpagerFragmentBasketball.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IndexViewpagerFragmentBasketball().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}