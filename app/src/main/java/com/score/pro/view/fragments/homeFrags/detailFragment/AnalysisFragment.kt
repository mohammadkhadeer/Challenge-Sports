package com.score.pro.view.fragments.homeFrags.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import score.pro.R
import com.score.pro.model.data.homepage.analysis.analysisOdds.FormattedAnalysisOdds
import com.score.pro.utils.SpewViewModel
import com.score.pro.utils.Status
import com.score.pro.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.score.pro.view.fragments.homeFrags.adapter.MatchesPopulatingAdapter
import com.score.pro.view.fragments.homeFrags.adapter.MatchesPopulatingAdapterBasketball
import com.score.pro.view.fragments.homeFrags.adapter.SortedOddsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AnalysisFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.fragment_analysis, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val headToHeadRv=view.findViewById<RecyclerView>(R.id.head_to_head_rv)
        val homeRecentRv=view.findViewById<RecyclerView>(R.id.home_team_latest_rv)
        val awayRecentRv=view.findViewById<RecyclerView>(R.id.away_team_latest_rv)
        val homeOddsRv=view.findViewById<RecyclerView>(R.id.home_team_odds_rv)
        val awayOddsRv=view.findViewById<RecyclerView>(R.id.away_team_odds_rv)

        val vm=SpewViewModel.giveMeViewModel(requireActivity())
        if (adapterType==MainAdapterCommunicator.BASKETBALL_TYPE){
            vm.analysisLiveDataBasketball.observe(requireActivity()){
                when(it.status){
                    Status.SUCCESS -> {
                        try {
                            headToHeadRv.visibility=View.VISIBLE
                            awayRecentRv.visibility=View.VISIBLE
                            homeRecentRv.visibility=View.VISIBLE

                            headToHeadRv.adapter=MatchesPopulatingAdapterBasketball(requireContext(),it.data!!.list[0].headToHead)
                            awayRecentRv.adapter=MatchesPopulatingAdapterBasketball(requireContext(),it.data.list[0].awayLastMatches)
                            homeRecentRv.adapter=MatchesPopulatingAdapterBasketball(requireContext(),it.data.list[0].homeLastMatches)

                            headToHeadRv.layoutManager=LinearLayoutManager(context)
                            awayRecentRv.layoutManager=LinearLayoutManager(context)
                            homeRecentRv.layoutManager=LinearLayoutManager(context)
                        }catch (e:Exception){
                            println(e)
                        }


                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING ->{
                        headToHeadRv.visibility=View.GONE
                        awayRecentRv.visibility=View.GONE
                        homeRecentRv.visibility=View.GONE
                        homeOddsRv.visibility=View.GONE
                        awayOddsRv.visibility=View.GONE

                    }
                }
            }

            vm.makeAnalysisCallBasketball(matchId!!)

        }else{
            vm.analysisLiveData.observe(requireActivity()){
                when(it.status){
                    Status.SUCCESS -> {
                        try {
                            headToHeadRv.visibility=View.VISIBLE
                            awayRecentRv.visibility=View.VISIBLE
                            homeRecentRv.visibility=View.VISIBLE
                            homeOddsRv.visibility=View.VISIBLE
                            awayOddsRv.visibility=View.VISIBLE

                            headToHeadRv.adapter=MatchesPopulatingAdapter(requireContext(), it.data?.list?.get(0)?.headToHead!!)
                            headToHeadRv.layoutManager=LinearLayoutManager(requireContext())

                            homeRecentRv.adapter=MatchesPopulatingAdapter(requireContext(), it.data.list[0].homeLastMatches)
                            homeRecentRv.layoutManager=LinearLayoutManager(requireContext())

                            awayRecentRv.adapter=MatchesPopulatingAdapter(requireContext(), it.data.list[0].awayLastMatches)
                            awayRecentRv.layoutManager=LinearLayoutManager(requireContext())

                            homeOddsRv.adapter=SortedOddsAdapter(requireContext(),returnFormattedOddsList(it.data.list[0].homeOdds))
                            homeOddsRv.layoutManager=LinearLayoutManager(requireContext())

                            awayOddsRv.adapter=SortedOddsAdapter(requireContext(),returnFormattedOddsList(it.data.list[0].awayOdds))
                            awayOddsRv.layoutManager=LinearLayoutManager(requireContext())


                        }catch (e:Exception){

                        }

                        //    vm.analysisLiveData.removeObservers(requireActivity())
                    }
                    Status.ERROR -> {
                        //            vm.analysisLiveData.removeObservers(requireActivity())
                    }
                    Status.LOADING -> {
                        headToHeadRv.visibility=View.GONE
                        awayRecentRv.visibility=View.GONE
                        homeRecentRv.visibility=View.GONE
                        homeOddsRv.visibility=View.GONE
                        awayOddsRv.visibility=View.GONE

                    }
                }
            }
            vm.makeAnalysisCall(matchId!!)
        }

    }

    private fun returnFormattedOddsList(mixedOdds: List<List<String>>): List<FormattedAnalysisOdds> {
       val list=ArrayList<FormattedAnalysisOdds>()

        var odds=mixedOdds[0]
        var os=odds[0].split("^")
        list.add(FormattedAnalysisOdds(getString(R.string.total_fulltime),os[1],os[2],os[3],os[4],os[5],os[6]+"/"+os[7],os[8]+"/"+os[9]))

        odds=mixedOdds[1]
        os=odds[0].split("^")
        list.add(FormattedAnalysisOdds(getString(R.string.home_fulltime),os[1],os[2],os[3],os[4],os[5],os[6]+"/"+os[7],os[8]+"/"+os[9]))

        odds=mixedOdds[2]
        os=odds[0].split("^")
        list.add(FormattedAnalysisOdds(getString(R.string.away_fulltime),os[1],os[2],os[3],os[4],os[5],os[6]+"/"+os[7],os[8]+"/"+os[9]))

        odds=mixedOdds[4]
        os=odds[0].split("^")
        list.add(FormattedAnalysisOdds(getString(R.string.total_halftime),os[1],os[2],os[3],os[4],os[5],os[6]+"/"+os[7],os[8]+"/"+os[9]))

        odds=mixedOdds[5]
        os=odds[0].split("^")
        list.add(FormattedAnalysisOdds(getString(R.string.home_halftime),os[1],os[2],os[3],os[4],os[5],os[6]+"/"+os[7],os[8]+"/"+os[9]))

        odds=mixedOdds[6]
        os=odds[0].split("^")
        list.add(FormattedAnalysisOdds(getString(R.string.away_halftime),os[1],os[2],os[3],os[4],os[5],os[6]+"/"+os[7],os[8]+"/"+os[9]))

        return list

    }

    companion object {
        @JvmStatic
        fun newInstance(matchId: String, adapterType: Int) =
            AnalysisFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, matchId)
                    putInt(ARG_PARAM2, adapterType)
                }
            }
    }
}