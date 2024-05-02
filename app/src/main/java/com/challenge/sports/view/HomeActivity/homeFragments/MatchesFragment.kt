package com.challenge.sports.view.HomeActivity.homeFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.sports.model.data.matchStatus.MatchStatusJ
import com.challenge.sports.model.data.news.List
import com.challenge.sports.utils.GeneralTools.fillMatchesStatus
import com.challenge.sports.view.adapters.RecyclerViewOnclick
import com.challenge.sports.view.fragments.OnDetailListener
import com.challenge.sports.view.fragments.homeFrags.adapter.NewsAdapter_Horizontal
import com.challenge.sports.view.HomeActivity.homeAdapter.MatchStatusAdapter
import score.pro.R

class MatchesFragment : Fragment() {

    private lateinit var recycler_view: RecyclerView
    var match_status_list: ArrayList<MatchStatusJ> = ArrayList()
    var adpterMatchStatus: MatchStatusAdapter? = null
    private var onDetailListener: OnDetailListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matches_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        casting(view)
        match_status_list = fillMatchesStatus(requireContext())
        createRecyclerViewMatchStatus()
    }

    private fun createRecyclerViewMatchStatus() {
        recycler_view.setNestedScrollingEnabled(false);
//        recycler_view.adapter= MatchStatusAdapter(requireContext(), match_status_list)
        recycler_view.adapter= MatchStatusAdapter(requireContext(), match_status_list,
            object : RecyclerViewOnclick{
                override fun onClick(position: Int) {
                    upadateTheSelectedItemInRecyclerView(position)
                    Log.i("TAG",match_status_list.get(position).name)
                    createRecyclerViewMatchStatus()
                }
            })
        recycler_view.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }

    private fun upadateTheSelectedItemInRecyclerView(position: Int) {
        for (i in 0..(match_status_list.size - 1)) {
            if (position == i)
            {
                match_status_list.set(position,MatchStatusJ(match_status_list.get(position).name, true))
            }else{
                match_status_list.set(i,MatchStatusJ(match_status_list.get(i).name, false))
            }

        }
    }

    private fun casting(view: View) {
        recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            MatchesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
