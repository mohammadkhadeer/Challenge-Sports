package com.challenge.sports.view.fragments.homeFrags.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import score.pro.R
import com.challenge.sports.utils.Formatters
import com.challenge.sports.utils.SpewViewModel
import com.challenge.sports.utils.Status
import com.challenge.sports.view.fragments.homeFrags.adapter.EventsAdapter
import com.challenge.sports.view.fragments.homeFrags.adapter.TechnicAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EventFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val technicRv=view.findViewById<RecyclerView>(R.id.technic_rv)
        val eventsRv=view.findViewById<RecyclerView>(R.id.event_rv)
        val vm=SpewViewModel.giveMeViewModel(requireActivity())
        vm.eventsLiveData.observe(requireActivity()){
            when(it.status){
                Status.SUCCESS -> {
                    try {
                        val data=it.data!!

                        val eventList=Formatters.filterEvents(matchId!!,data)
                        val formattedEvents=Formatters.formatEvents(matchId!!,eventList)

                        eventsRv.adapter=EventsAdapter(requireContext(),formattedEvents)
                        eventsRv.layoutManager=LinearLayoutManager(requireContext())

                        val technicList=Formatters.formatTechnic(Formatters.filterEvents(matchId!!,data).technic[0].technicCount)
                        technicRv.adapter=TechnicAdapter(requireContext(),technicList)
                        technicRv.layoutManager=LinearLayoutManager(context)

                    }catch (e:Exception){

                    }

                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        }
        vm.makeEventListCall()
    }

    private fun inti() {

    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(matchId: String, param2: String) =
            EventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, matchId)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}