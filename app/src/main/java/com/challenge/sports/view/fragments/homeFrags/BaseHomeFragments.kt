package com.challenge.sports.view.fragments.homeFrags

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.challenge.sports.model.data.LegaDetails
import com.challenge.sports.model.data.homepage.new2.Match
import com.challenge.sports.model.data.news.details.OnPostDetailResponse
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.utils.SpewViewModel
import com.challenge.sports.utils.Status
import com.challenge.sports.view.adapters.ViewPagerAdapter
import com.challenge.sports.view.floatingbubble.service.FloatingScoreService
import com.challenge.sports.view.fragments.OnBackPressedListener
import com.challenge.sports.view.fragments.homeFrags.adapter.*
import com.challenge.sports.view.fragments.homeFrags.adapter.pastfuture.DatesListAdapter
import com.challenge.sports.view.fragments.homeFrags.adapter.pastfuture.PastFutureAdapter
import com.challenge.sports.view.fragments.homeFrags.detailFragment.HighlightedFragment
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import score.pro.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BaseHomeFragments : Fragment(), MainAdapterCommunicator,
    SwipeRefreshLayout.OnRefreshListener {
    private var param1: String? = null
    private var param2: String? = null
    private var result_schedule: String? =null
    private var mainAdapter: MainAdapter? = null
    private var pastFutureAdapter: PastFutureAdapter? = null
    private var pastScheduleAdapter: ScheduleAdapter? = null
    var onBackPressedListener: OnBackPressedListener? = null
    var footBallList = ArrayList<String>()
    var leagueList = ArrayList<LegaDetails>()
    var basketBallAdapter: MainAdapterBasketBall? = null
    var adapterTypeParent: Int = MainAdapterCommunicator.FOOTBALL_TYPE
    var footballBasketDownMenu:FootballBasketDownMenu?=null
    var recyclerViewMain= view?.findViewById<RecyclerView>(R.id.main_recycler_view)
    var rest_matches_heading= view?.findViewById<TextView>(R.id.rest_matches_heading)

    var onChangeType:OnPostDetailResponse<Int>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //Score Pro
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_home_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewMain = view.findViewById<RecyclerView>(R.id.main_recycler_view)
        rest_matches_heading= view?.findViewById<TextView>(R.id.rest_matches_heading)
        val vm = SpewViewModel.giveMeViewModel(requireActivity())

        val tablayout = view.findViewById<TabLayout>(R.id.tab_layout_local_filters)
        val new_spenner = view.findViewById<TextView>(R.id.lega_name_tv)
        val mSwipeRefreshLayout= view?.findViewById<SwipeRefreshLayout>(R.id.swipe_to_refresh)
        val daysRecyclerView =
            view.findViewById<RecyclerView>(R.id.days_recycler_view)

        val football_basketball_rl=view.findViewById<RelativeLayout>(R.id.football_basketball_rl)
        //gradient text color
        football_basketball_rl.setOnClickListener{
//            (activity as BaseActivity?)?.downMenu()
        }

        val lega_rl=view.findViewById<RelativeLayout>(R.id.lega_rl)
        lega_rl.setOnClickListener{
//            (activity as BaseActivity?)?.showLega(leagueList)
        }

        val dateSelectedListener = object : OnPostDetailResponse<String> {
            override fun onSuccess(responseBody: String) {
                Log.i("TAG","result_schedule: "+result_schedule)
                Log.i("TAG","responseBody Date: "+responseBody)

                if (adapterTypeParent != MainAdapterCommunicator.BASKETBALL_TYPE) {
                    rest_matches_heading?.text=responseBody

                    if (result_schedule.equals("result"))
                    {
                        vm.makePastFutureCall(responseBody)
                        //can move it to observer onCreate
                        recyclerViewMain?.layoutManager = GridLayoutManager(context, 2)
                    }
                    else{
                        vm.makeScheduleCall(responseBody)
                        recyclerViewMain?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }

                } else {
                    //shod to handel here
                    if (result_schedule.equals("schedule"))
                    {
                        vm.makeFutureCallBasketball(responseBody)
                        recyclerViewMain?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }else{
                        vm.makePastFutureCallBasketball(responseBody)
                        recyclerViewMain?.layoutManager = GridLayoutManager(context, 2)
                    }
                }
            }

            override fun onFailure(message: String) {

            }

            override fun onLoading(message: String) {

            }
        }

        mSwipeRefreshLayout?.setOnRefreshListener(this)
        mSwipeRefreshLayout?.setColorSchemeResources(
            R.color.brand_color,
            android.R.color.black,
            R.color.brand_color,
            R.color.brand_color
        )

        mSwipeRefreshLayout?.setOnRefreshListener {
            Log.i("TAG","on setOnRefreshListener case")
            Handler().postDelayed({ mSwipeRefreshLayout?.isRefreshing = false }, 500)
        }




        vm.baseHomePageLiveData.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    try {
                        footBallList.add(getString(R.string.football))
                        footBallList.add(getString(R.string.basketball))
                        new_spenner.setText(getString(R.string.league))

                        leagueList.add(LegaDetails(getString(R.string.league), "image"))
                        for (leagues in it.data!!.todayHotLeague) {
                            if (GeneralTools.getLocale(requireContext())==SharedPreference.CHINESE){
                                leagueList.add(LegaDetails(leagues.leagueChsShort, "image"))
                            }else{
                                leagueList.add(LegaDetails(leagues.leagueName, "image"))
                            }
                        }


                        mainAdapter = MainAdapter(
                            requireContext(),
                            it.data?.matchList as ArrayList<Match>,
                            this
                        )

                        recyclerViewMain?.adapter = mainAdapter
//                        recyclerViewMain?.layoutManager =
//                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                        recyclerViewMain?.layoutManager =
                            GridLayoutManager(context, 2)

                        view.findViewById<View>(R.id.loader_anim_container).visibility = View.GONE

                        val spinnerFootBallOrBasketBall = view.findViewById<AppCompatSpinner>(R.id.league_spinner)
                        val spinnerLeague = view.findViewById<AppCompatSpinner>(R.id.time_spinner)
//                        spinnerLeague
                        spinnerFootBallOrBasketBall.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            footBallList
                        )

                        spinnerLeague.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            leagueList
                        )
                        val baseList = ArrayList<Match>()
                        baseList.addAll(it.data.matchList)
                        val baseDefaultAdapter = mainAdapter






                        spinnerFootBallOrBasketBall.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    when (position) {
                                        0 -> {
                                            footballCase();
                                        }
                                        else -> {
                                            basketballCase();
                                        }
                                    }
                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {

                                }
                            }



                        onChangeType=object : OnPostDetailResponse<Int> {
                            override fun onSuccess(position: Int) {
                                when (position) {
                                    0 -> {
                                        mainAdapter?.setNewList(baseList, true)
                                        recyclerViewMain?.adapter?.notifyDataSetChanged()
                                    }
                                    else -> {
                                      //  getLeagaMatches(position)
                                        val selectedList = ArrayList<Match>()
                                        for (match in it.data.todayHotLeagueList) {
                                            if (match.leagueId == it.data.todayHotLeague[position - 1].leagueId) {
                                                selectedList.add(match)
                                            }
                                        }
                                        mainAdapter?.setNewList(selectedList, false)
                                        mainAdapter?.notifyDataSetChanged()
                                    }
                                }



                            }

                            override fun onFailure(message: String) {
                            }

                            override fun onLoading(message: String) {

                            }
                        }



                        vm.pastFutureLiveData.observe(requireActivity()) { data ->
                            when (data.status) {
                                Status.SUCCESS -> {
                                    pastFutureAdapter = PastFutureAdapter(
                                        requireContext(),
                                        data.data!!.matchList,
                                        15
                                    )
                                    recyclerViewMain?.adapter = pastFutureAdapter
                                    recyclerViewMain?.adapter?.notifyDataSetChanged()
                                }
                                Status.ERROR -> {

                                }
                                Status.LOADING -> {

                                }
                            }
                        }


                        vm.scheduleFutureLiveData.observe(requireActivity()) { data ->
                            when (data.status) {
                                Status.SUCCESS -> {

                                    Log.i("TAG","BasketBall or football schedule ")

                                    pastScheduleAdapter = ScheduleAdapter(
                                        requireContext(),
                                        data.data!!.matchList,
                                        15
                                    )
                                    recyclerViewMain?.adapter = pastScheduleAdapter
                                    recyclerViewMain?.adapter?.notifyDataSetChanged()
                                }
                                Status.ERROR -> {

                                }
                                Status.LOADING -> {

                                }
                            }
                        }

                        vm.pastFutureLiveDataBasketball.observe(requireActivity()) { data ->
                            when (data.status) {
                                Status.SUCCESS -> {
                                    Log.i("TAG","BasketBall1 past ")
                                    pastFutureAdapter = PastFutureAdapter(
                                        requireContext(),
                                        data.data!!.matchList,
                                        MainAdapterCommunicator.BASKETBALL_TYPE
                                    )
                                    recyclerViewMain?.adapter = pastFutureAdapter
                                    recyclerViewMain?.adapter?.notifyDataSetChanged()
                                }
                                Status.ERROR -> {

                                }
                                Status.LOADING -> {

                                }
                            }
                        }


                        vm.futureLiveDataBasketball.observe(requireActivity()) { data ->
                            when (data.status) {
                                Status.SUCCESS -> {
                                    Log.i("TAG","BasketBall1 past ")
                                    pastScheduleAdapter = ScheduleAdapter(
                                        requireContext(),
                                        data.data!!.matchList,
                                        MainAdapterCommunicator.BASKETBALL_TYPE
                                    )
                                    recyclerViewMain?.adapter = pastScheduleAdapter
                                    recyclerViewMain?.adapter?.notifyDataSetChanged()
                                }
                                Status.ERROR -> {

                                }
                                Status.LOADING -> {

                                }
                            }
                        }


                    } catch (e: Exception) {
                        //TODO: Put Error, could not load here
                    }

                }
                Status.ERROR -> {
                    println(it.message)
                }
                Status.LOADING -> {
                    view.findViewById<View>(R.id.loader_anim_container).visibility = View.VISIBLE
                }
            }
        }

        vm.basketBallLiveData.observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    basketBallAdapter = MainAdapterBasketBall(
                        requireContext(),
                        it.data!!.matchList as ArrayList<com.challenge.sports.model.data.basketball.homepage.Match>,
                        this
                    )
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        }



        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_local_filters)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.ALL)
                        basketBallAdapter?.setFilter(MainAdapter.CategoryFilterType.ALL)

                    }
                    1 -> {
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.LIVE)
                        basketBallAdapter?.setFilter(MainAdapter.CategoryFilterType.LIVE)
                    }
                    2 -> {
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.SOON)
                        basketBallAdapter?.setFilter(MainAdapter.CategoryFilterType.SOON)
                    }
                    3 -> {
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.FT)
                        basketBallAdapter?.setFilter(MainAdapter.CategoryFilterType.FT)
                    }
                    else -> {
                        mainAdapter?.setFilter(MainAdapter.CategoryFilterType.ALL)
                        basketBallAdapter?.setFilter(MainAdapter.CategoryFilterType.ALL)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


        val timeTabLayout = view.findViewById<TabLayout>(R.id.tab_layout_time_filters)
        timeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {

//                        GeneralTools.flipReplaceAnimation(
//                            tablayout,
//                            daysRecyclerView
//                        )
                        daysRecyclerView.adapter = DatesListAdapter(requireContext(), GeneralTools.getPastWeekDates(), dateSelectedListener)
                        //create this value to can check where is user press the time on result or schedule to change ui
                        result_schedule = "result"
                        daysRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                    1 -> {
                        rest_matches_heading?.text=resources.getString(R.string.all_matches)
//                        GeneralTools.flipReplaceAnimation(daysRecyclerView, tablayout)
                        recyclerViewMain?.adapter = mainAdapter
                        recyclerViewMain?.adapter?.notifyDataSetChanged()
                        //change layout manger
                        recyclerViewMain?.layoutManager = GridLayoutManager(context, 2)
                    }
                    2 -> {
//                        GeneralTools.flipReplaceAnimation(
//                            tablayout,
//                            daysRecyclerView
//                        )
                        daysRecyclerView.adapter = DatesListAdapter(requireContext(), GeneralTools.getFutureDates(), dateSelectedListener)
                        result_schedule = "schedule"
                        daysRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        timeTabLayout.getTabAt(1)?.select();


        vm.makeIndexNetworkCall("1",GeneralTools.getLocale(requireContext()))
        vm.makeIndexBasketBallCall()
        refreshHighlights()
    }

     fun getLeagaMatches(position:Int) {
        val selectedList = ArrayList<Match>()
         onChangeType?.onSuccess(position)
//        for (match in it.data.todayHotLeagueList) {
//            if (match.leagueId == it.data.todayHotLeague[position - 1].leagueId) {
//                selectedList.add(match)
//            }
//        }
//        mainAdapter?.setNewList(selectedList, false)
//        mainAdapter?.notifyDataSetChanged()
    }

    public fun footballCase() {
        recyclerViewMain?.adapter = mainAdapter
        recyclerViewMain?.adapter?.notifyDataSetChanged()
        adapterTypeParent = MainAdapterCommunicator.FOOTBALL_TYPE

        recyclerViewMain?.layoutManager =
            GridLayoutManager(context, 2)
    }

    public fun basketballCase() {
        recyclerViewMain?.adapter = basketBallAdapter
        recyclerViewMain?.adapter?.notifyDataSetChanged()
        adapterTypeParent = MainAdapterCommunicator.BASKETBALL_TYPE

        recyclerViewMain?.layoutManager =
            GridLayoutManager(context, 2)

//        recyclerViewMain?.layoutManager =
//            LinearLayoutManager(
//                context,
//                LinearLayoutManager.VERTICAL,
//                false
//            )
    }

    fun searchMatch(constraint: String) {
        mainAdapter?.filter?.filter(constraint)
        basketBallAdapter?.filter?.filter(constraint)
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

    override fun onMessageFromAdapter(
        message: MainAdapterMessages,
        position: Int,
        adapterType: Int
    ) {
        when (message) {
            MainAdapterMessages.OPEN_INDEX -> {
                //        startActivity(Intent(requireContext(),TestActivity::class.java).putExtra("matchid", (mainAdapter?.dataList?.get(position)?.matchId ?:0).toString()))
                val matchid =
                    if (adapterType != MainAdapterCommunicator.BASKETBALL_TYPE) (mainAdapter?.dataList?.get(
                        position
                    )?.matchId
                        ?: 0).toString() else basketBallAdapter?.dataList?.get(position)?.matchId.toString()

                val frag = MatchOptionsFragment.newInstance(
                    matchid, MatchOptionsFragment.INDEX_TYPE, adapterType
                )
                try {
                    if (adapterType != MainAdapterCommunicator.BASKETBALL_TYPE) {
                        frag.setData(mainAdapter?.dataList?.get(position)!!)
                    } else {
                        frag.data = basketBallAdapter?.dataList?.get(position)
                    }
                    inflateFragment(
                        frag, R.id.fragment_container
                    )
                } catch (e: Exception) {

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
            MainAdapterMessages.CLOSE_INDEX -> {

            }
            MainAdapterMessages.OPEN_ANALYSIS -> {
                val matchid =
                    if (adapterType != MainAdapterCommunicator.BASKETBALL_TYPE) (mainAdapter?.dataList?.get(
                        position
                    )?.matchId
                        ?: 0).toString() else basketBallAdapter?.dataList?.get(position)?.matchId.toString()

                val frag = MatchOptionsFragment.newInstance(
                    matchid, MatchOptionsFragment.ANALYSIS_TYPE, adapterType
                )
                try {
                    if (adapterType != MainAdapterCommunicator.BASKETBALL_TYPE) {
                        frag.setData(mainAdapter?.dataList?.get(position)!!)
                    } else {
                        frag.data = basketBallAdapter?.dataList?.get(position)
                    }
                    inflateFragment(
                        frag, R.id.fragment_container
                    )
                } catch (e: Exception) {

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
            MainAdapterMessages.OPEN_LEAGUE -> {
                val matchid =
                    if (adapterType == MainAdapterCommunicator.BASKETBALL_TYPE) basketBallAdapter?.dataList?.get(
                        position
                    )?.matchId.toString() else (mainAdapter
                        ?.dataList
                        ?.get(position)
                        ?.matchId
                        ?: 0)
                        .toString()
                val frag = MatchOptionsFragment.newInstance(
                    matchid, MatchOptionsFragment.LEAGUE_FRAGMENT, adapterType
                )
                try {
                    if (adapterType != MainAdapterCommunicator.BASKETBALL_TYPE) {
                        frag.setData(mainAdapter?.dataList?.get(position)!!)
                    } else {
                        frag.data = basketBallAdapter?.dataList?.get(position)
                    }
                    inflateFragment(
                        frag, R.id.fragment_container
                    )

                } catch (e: Exception) {

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
            MainAdapterMessages.CLOSE_LEAGUE -> {

            }
            MainAdapterMessages.OPEN_EVENT -> {
                val frag = MatchOptionsFragment.newInstance(
                    (mainAdapter
                        ?.dataList
                        ?.get(position)
                        ?.matchId
                        ?: 0)
                        .toString(), MatchOptionsFragment.EVENT_TYPE, adapterType
                )
                try {
                    frag.setData(mainAdapter?.dataList?.get(position)!!)
                    inflateFragment(
                        frag, R.id.fragment_container
                    )
                } catch (e: Exception) {

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
            MainAdapterMessages.OPEN_BRIEF -> {
                val matchid =
                    if (adapterType == MainAdapterCommunicator.BASKETBALL_TYPE) basketBallAdapter?.dataList?.get(
                        position
                    )?.matchId.toString() else (mainAdapter
                        ?.dataList
                        ?.get(position)
                        ?.matchId
                        ?: 0)
                        .toString()
                val frag = MatchOptionsFragment.newInstance(
                    matchid, MatchOptionsFragment.BRIEFING_FRAGMENT, adapterType
                )
                try {
                    if (adapterType != MainAdapterCommunicator.BASKETBALL_TYPE) {
                        frag.setData(mainAdapter?.dataList?.get(position)!!)
                    } else {
                        frag.data = basketBallAdapter?.dataList?.get(position)
                    }
                    inflateFragment(
                        frag, R.id.fragment_container
                    )
                } catch (e: Exception) {

                }
                onBackPressedListener?.changeBackPressBehaviour(this)
            }
            MainAdapterMessages.LONG_PRESS_ITEM -> {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        displayDialog(requireActivity(), mainAdapter?.dataList?.get(position)!!)
                    }
                } catch (e: Exception) {

                }
            }
            MainAdapterMessages.LOAD_MORE -> {
                try {
                    loadMoreItems()
                } catch (e: Exception) {

                }
            }
        }
    }

    private fun loadMoreItems() {
        val vm = SpewViewModel.giveMeViewModel(requireActivity())
        vm.makeIndexNetworkCall(object : OnPostDetailResponse<List<Match>> {
            override fun onSuccess(responseBody: List<Match>) {
                view?.findViewById<View>(R.id.loading_more_bar)?.visibility = View.GONE
                mainAdapter?.updateList(responseBody)
            }

            override fun onFailure(message: String) {
                mainAdapter?.isMaxLoaded = message == SharedPreference.MAX_PAGE_REACHED
                view?.findViewById<View>(R.id.loading_more_bar)?.visibility = View.GONE
                println(message)
            }

            override fun onLoading(message: String) {
                view?.findViewById<View>(R.id.loading_more_bar)?.visibility = View.VISIBLE
            }
        },GeneralTools.getLocale(requireContext()))
    }

    private fun inflateFragment(fragment: Fragment, resourceId: Int) {
        val ft = requireActivity().supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container_base, fragment)
        ft.commit()
        view?.findViewById<View>(R.id.fragment_container_base)?.visibility = View.VISIBLE
    }

    fun hideFragment() {
        view?.findViewById<View>(R.id.fragment_container_base)?.visibility = View.GONE

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun displayDialog(context: Activity, match: Match) {
        val dialog = Dialog(context, android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.dialog_longpress)

        val radius = 2f

        val decorView = context.getWindow().getDecorView()
        val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)

        val windowBackground = decorView.getBackground()
        val blurView = dialog.findViewById<BlurView>(R.id.baseBlurView)
        blurView.setupWith(rootView, RenderScriptBlur(context)) // or RenderEffectBlur
            .setFrameClearDrawable(windowBackground) // Optional
            .setBlurRadius(radius)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<View>(R.id.add_to_highlights_bt).setOnClickListener {
            GeneralTools.saveToHighlightedMatches(match, context)
            refreshHighlights()
            dialog.dismiss()
        }
        dialog.findViewById<View>(R.id.cross_bt).setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<View>(R.id.pin_live_score).setOnClickListener {

            if (!Settings.canDrawOverlays(requireContext())) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + requireActivity().packageName)
                )
                startActivityForResult(intent, 0)
            } else {
                ActivityCompat.startForegroundService(
                    requireContext(),
                    Intent(requireContext(), FloatingScoreService::class.java).putExtra(
                        SharedPreference.MATCH_ID_TOKEN,
                        match.matchId.toString()
                    )
                )
                dialog.dismiss()
            }

        }
        dialog.show()

    }

    private fun refreshHighlights() {
        println(GeneralTools.getHighlightedMatches(requireContext()))
        val list = GeneralTools.getHighlightedMatches(requireContext())
        list ?: return
        list.reverse()
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

    override fun onRefresh() {
        Log.i("TAG","on onRefresh case")

        //mSwipeRefreshLayout?.isRefreshing = false
    }

}