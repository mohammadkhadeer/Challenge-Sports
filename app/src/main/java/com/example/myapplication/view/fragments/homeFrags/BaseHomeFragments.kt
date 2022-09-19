package com.example.myapplication.view.fragments.homeFrags

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.new2.Match
import com.example.myapplication.model.data.news.details.OnPostDetailResponse
import com.example.myapplication.utils.GeneralTools
import com.example.myapplication.utils.SpewViewModel
import com.example.myapplication.utils.Status
import com.example.myapplication.view.adapters.ViewPagerAdapter
import com.example.myapplication.view.fragments.OnBackPressedListener
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapter
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapterMessages
import com.example.myapplication.view.fragments.homeFrags.detailFragment.HighlightedFragment
import com.google.android.material.tabs.TabLayout
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur

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
                    view.findViewById<View>(R.id.loader_anim_container).visibility=View.GONE
                }
                Status.ERROR ->{
                    println(it.message)
                }
                Status.LOADING -> {
                   view.findViewById<View>(R.id.loader_anim_container).visibility=View.VISIBLE
                }
            }
        }
        val tabLayout=view.findViewById<TabLayout>(R.id.tab_layout_local_filters)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.ALL)
                    }
                    1->{
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.LIVE)
                    }
                    2->{
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.SOON)
                    }
                    3->{
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.FT)
                    }
                    else->{
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.ALL)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        vm.makeIndexNetworkCall("1")
        refreshHighlights()
    }
    fun searchMatch(constraint:String){
        mainAdapter?.filter?.filter(constraint)
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
                val frag=MatchOptionsFragment.newInstance(
                    (mainAdapter
                        ?.dataList
                        ?.get(position)
                        ?.matchId
                        ?:0)
                        .toString(),MatchOptionsFragment.LEAGUE_FRAGMENT)
                try {
                    frag.setData(mainAdapter?.dataList?.get(position)!!)
                    inflateFragment(frag
                        ,R.id.fragment_container)
                }catch (e:Exception){

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
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
            MainAdapterMessages.OPEN_BRIEF -> {
                val frag=MatchOptionsFragment.newInstance(
                    (mainAdapter
                        ?.dataList
                        ?.get(position)
                        ?.matchId
                        ?:0)
                        .toString(),MatchOptionsFragment.BRIEFING_FRAGMENT)
                try {
                    frag.setData(mainAdapter?.dataList?.get(position)!!)
                    inflateFragment(frag
                        ,R.id.fragment_container)
                }catch (e:Exception){

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
            MainAdapterMessages.LONG_PRESS_ITEM -> {
                try {
                    displayDialog(requireActivity(),mainAdapter?.dataList?.get(position)!!)
                }catch (e:Exception){

                }
                 }
            MainAdapterMessages.LOAD_MORE->{
                try {
                    loadMoreItems()
                }catch (e:Exception){

                }
            }
        }
    }

    private fun loadMoreItems() {
        val vm=SpewViewModel.giveMeViewModel(requireActivity())
        vm.makeIndexNetworkCall(object : OnPostDetailResponse<List<Match>>{
            override fun onSuccess(responseBody: List<Match>) {
                view?.findViewById<View>(R.id.loading_more_bar)?.visibility=View.GONE
                mainAdapter?.updateList(responseBody)
            }
            override fun onFailure(message: String) {
                view?.findViewById<View>(R.id.loading_more_bar)?.visibility=View.GONE
                println(message)
            }

            override fun onLoading(message: String) {
                view?.findViewById<View>(R.id.loading_more_bar)?.visibility=View.VISIBLE
            }
        })
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
    fun displayDialog(context: Activity,match: Match){
        val dialog=Dialog(context,android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.dialog_longpress)

        val radius = 2f

        val decorView = context.getWindow().getDecorView()
        // ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        val rootView =  decorView.findViewById<ViewGroup>(android.R.id.content)

        // Optional:
        // Set drawable to draw in the beginning of each blurred frame.
        // Can be used in case your layout has a lot of transparent space and your content
        // gets a too low alpha value after blur is applied.
        val windowBackground = decorView.getBackground()
        val blurView=dialog.findViewById<BlurView>(R.id.baseBlurView)
        blurView.setupWith(rootView,  RenderScriptBlur(context)) // or RenderEffectBlur
            .setFrameClearDrawable(windowBackground) // Optional
            .setBlurRadius(radius)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<View>(R.id.add_to_highlights_bt).setOnClickListener {
            GeneralTools.saveToHighlightedMatches(match,context)
            refreshHighlights()
        }
        dialog.show()

    }

    private fun refreshHighlights() {
        println(GeneralTools.getHighlightedMatches(requireContext()))
        val list = GeneralTools.getHighlightedMatches(requireContext())
        list ?: return
        val fragsList = ArrayList<Fragment>()
        for (item in list) {
            val frag = HighlightedFragment.newInstance("", "")
            frag.match = item
            fragsList.add(frag)
        }
        val viewPager = view?.findViewById<ViewPager2>(R.id.highlighted_matches_viewpager)
        viewPager?.adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            requireActivity().lifecycle,
            fragsList
        )
        val dotsIndicator = view?.findViewById<DotsIndicator>(R.id.dots_indicator)
        try {
            dotsIndicator?.attachTo(viewPager!!)
        } catch (e: Exception) {

        }
    }

}