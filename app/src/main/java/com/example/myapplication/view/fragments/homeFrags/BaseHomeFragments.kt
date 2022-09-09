package com.example.myapplication.view.fragments.homeFrags

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.new2.Match
import com.example.myapplication.model.test_exo.TestActivity
import com.example.myapplication.utils.SpewViewModel
import com.example.myapplication.utils.Status
import com.example.myapplication.view.fragments.OnBackPressedListener
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapter
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapterMessages

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BaseHomeFragments.newInstance] factory method to
 * create an instance of this fragment.
 */
class BaseHomeFragments : Fragment(),MainAdapterCommunicator {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mainAdapter:MainAdapter?=null
    var onBackPressedListener: OnBackPressedListener?=null

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
        return inflater.inflate(R.layout.fragment_base_home_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewMain=view.findViewById<RecyclerView>(R.id.main_recycler_view)
        val vm=SpewViewModel.giveMeViewModel(requireActivity())
        vm.baseHomePageLiveData.observe(requireActivity()){
            when(it.status){
                Status.SUCCESS -> {
                    mainAdapter=MainAdapter(requireContext(), it.data?.matchList as ArrayList<Match>,this)
                    recyclerViewMain.adapter=mainAdapter
                    recyclerViewMain.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                }
                Status.ERROR ->{
                    println(it.message)
                }
                Status.LOADING -> {

                }
            }
        }
        vm.makeIndexNetworkCall("1")
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BaseHomeFragments().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMessageFromAdapter(message: MainAdapterMessages, position: Int,resourceId:Int) {
        when(message){
            MainAdapterMessages.OPEN_INDEX ->
            {
        //        startActivity(Intent(requireContext(),TestActivity::class.java).putExtra("matchid", (mainAdapter?.dataList?.get(position)?.matchId ?:0).toString()))
               val frag=MatchOptionsFragment.newInstance(
                   (mainAdapter
                       ?.dataList
                       ?.get(position)
                       ?.matchId
                       ?:0)
                       .toString(),MatchOptionsFragment.INDEX_TYPE)
                try {
                    frag.setData(mainAdapter?.dataList?.get(position)!!)
                    inflateFragment(frag
                        ,R.id.fragment_container)
                }catch (e:Exception){

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
            MainAdapterMessages.CLOSE_INDEX -> {

            }
            MainAdapterMessages.OPEN_ANALYSIS -> {
                val frag=MatchOptionsFragment.newInstance(
                    (mainAdapter
                        ?.dataList
                        ?.get(position)
                        ?.matchId
                        ?:0)
                        .toString(),MatchOptionsFragment.ANALYSIS_TYPE)
                try {
                    frag.setData(mainAdapter?.dataList?.get(position)!!)
                    inflateFragment(frag
                        ,R.id.fragment_container)
                }catch (e:Exception){

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
            MainAdapterMessages.OPEN_LEAGUE -> {

            }
            MainAdapterMessages.CLOSE_LEAGUE -> {

            }
            MainAdapterMessages.OPEN_EVENT -> {
                val frag=MatchOptionsFragment.newInstance(
                    (mainAdapter
                        ?.dataList
                        ?.get(position)
                        ?.matchId
                        ?:0)
                        .toString(),MatchOptionsFragment.EVENT_TYPE)
                try {
                    frag.setData(mainAdapter?.dataList?.get(position)!!)
                    inflateFragment(frag
                        ,R.id.fragment_container)
                }catch (e:Exception){

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
        }
    }

    private fun inflateFragment(fragment: Fragment, resourceId: Int) {
        val ft=requireActivity().supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container_base,fragment)
        ft.commit()
        view?.findViewById<View>(R.id.fragment_container_base)?.visibility=View.VISIBLE
    }
     fun hideFragment() {
        view?.findViewById<View>(R.id.fragment_container_base)?.visibility=View.GONE

    }

}