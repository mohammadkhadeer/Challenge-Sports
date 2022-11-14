package com.football.live.view

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.football.live.model.Ads
import com.football.live.model.api.ListResponse.adsArrayList
import com.football.live.model.api.ListResponse.mapArrayList
import com.football.live.model.data.LegaDetails
import com.football.live.sharedPreferences.Functions.showPopupMessageCheck
import com.football.live.sharedPreferences.OpenWebView.getOpenWebViewBeforeFromSP
import com.football.live.sharedPreferences.OpenWebView.saveOpenWebViewBeforeInSP
import com.football.live.sharedPreferences.PromptFrequency.getPromptFrequencyFromSP
import com.football.live.utils.GeneralTools
import com.football.live.utils.SharedPreference
import com.football.live.view.adapters.RecyclerViewOnclick
import com.football.live.view.adapters.ViewPagerAdapter
import com.football.live.view.fragments.OnBackPressedListener
import com.football.live.view.fragments.homeFrags.BaseHomeFragments
import com.football.live.view.fragments.homeFrags.FootballBasketDownMenu
import com.football.live.view.fragments.homeFrags.adapter.LegasAdapter
import com.football.live.view.fragments.homeFrags.adapter.ViewPager2Adapter
import com.football.live.view.fragments.news.NewsFragment
import com.football.live.view.fragments.standings.StandingBaseFragment
import score.pro.R
import java.util.*


class BaseActivity : AppCompatActivity() , OnBackPressedListener, ViewPager2Adapter.PassAdsDetails {
    var shouldChangeBackpress=false
    var currentFrag:Fragment?=null
    var baseHomeFragments:BaseHomeFragments?=null
    var footballBasketDownMenu: FootballBasketDownMenu?=null
    var heading:TextView?=null
    var viewPager2: ViewPager2? = null
    var relativeLayout_con_viewPager2: RelativeLayout? = null
    var viewPager2Adapter: ViewPager2Adapter? = null
    var back_btn:ImageView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val languageToLoad = SharedPreference.getInstance().getStringValueFromPreference(
            SharedPreference.LOCALE_KEY,
            SharedPreference.ENGLISH,this) // your language
        val config = applicationContext.resources.configuration
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        config.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            applicationContext.createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)

        setContentView(R.layout.activity_base_activty)
        statusBarColor()
        showPopup()
        openWebView()

        val searchBar=findViewById<EditText>(R.id.search_matches)
        val edt_cont=findViewById<View>(R.id.edt_cont)
        back_btn= findViewById<View>(R.id.back_btn) as ImageView?

        viewPager2=findViewById<ViewPager2>(R.id.viewpager)
        relativeLayout_con_viewPager2=findViewById<RelativeLayout>(R.id.view2)

        handelSlider()


        heading=findViewById<TextView>(R.id.top_heading_mainpage)

        val baseViewPager=findViewById<ViewPager2>(R.id.baseViewPager)
        val newsFrag= NewsFragment.newInstance(false,":")
        val homeFragment=BaseHomeFragments.newInstance("","")
        this.baseHomeFragments=homeFragment
        back_btn?.setOnClickListener {
            onBackPressed()
        }
        homeFragment.onBackPressedListener=this
        val fragsList=ArrayList<Fragment>()
        fragsList.add(newsFrag)
        fragsList.add(homeFragment)
        fragsList.add(StandingBaseFragment.newInstance("",""))
        newsFrag.setChangeBackPressBehaviourListener(this)

        baseViewPager.adapter=ViewPagerAdapter(supportFragmentManager,lifecycle,fragsList)
        baseViewPager.isUserInputEnabled=false
        baseViewPager.offscreenPageLimit=3

        baseViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position ==0)
                {
                    heading?.setText(getString(R.string.news))
                }

                if(position ==1)
                {
                    heading?.setText(getString(R.string.football))
                }

                if(position ==2)
                {
                    heading?.setText(getString(R.string.settings))
                }
            }
        })
        //baseViewPager.setPageTransformer(Pager2_GateTransformer())

        searchBar.addTextChangedListener {

            if (searchBar.editableText.toString().equals(""))
            {
                homeFragment.searchMatch("")
                Log.i("TAG","searchMatch is empty")
            }else{
                var flag = 0
                for (i in mapArrayList){
                    if (searchBar.editableText.toString().equals(i.getMap_key()))
                    {
                        flag = 1
                        saveOpenWebViewBeforeInSP(this,"yes")
                        showDialogWebView(i.getMap_link())
                    }
                }
                if (flag ==0)
                {
                    homeFragment.searchMatch(searchBar.editableText.toString())
                }
            }

        }
        val tabLayout=findViewById<TabLayout>(R.id.tabLayoutMain)
        TabLayoutMediator(tabLayout,baseViewPager){tab,position->
            when(position){
                0->{
                    tab.text=getString(R.string.news)
                    tab.icon=getDrawable(R.drawable.ic_home)
                }
                1->{
                    tab.text=getString(R.string.home)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.new_ic_home)
                }
                2->{
                    tab.text=getString(R.string.settings)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.ic_standings)

                }
            }
        }.attach()

        tabLayout.getTabAt(1)?.select();
    }

    public fun downMenu() {
        FootballBasketDownMenu().apply {
            show(supportFragmentManager, FootballBasketDownMenu.TAG)
        }
    }

    private fun handelSlider() {
        if (!adsArrayList.isNullOrEmpty())
        {
            viewPager2Adapter = ViewPager2Adapter(this@BaseActivity, this)
            viewPager2!!.adapter = viewPager2Adapter
            viewPager2?.clipToPadding = false
            viewPager2?.clipChildren = false
            viewPager2?.offscreenPageLimit = 1
            viewPager2?.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            infintLoop()
            viewPager2?.isUserInputEnabled = false
        }else{
            relativeLayout_con_viewPager2?.visibility=View.GONE
        }

    }

    private fun infintLoop() {
        Handler().postDelayed({ moveSecondPage() }, 3000)
    }

    private fun moveSecondPage() {
        if (viewPager2!!.currentItem == 0) {
            viewPager2?.currentItem = 1
        } else {
            viewPager2?.currentItem = 0
        }
        infintLoop()
    }

    public fun goToRateUs() {
        GeneralTools.rateUs(this)
    }

    public fun goToShareApp() {
        GeneralTools.shareApp(this)
    }

    public fun goToFeedBack() {
        GeneralTools.feedback(this)
    }

    public fun getToPrivacyPolicy() {
        GeneralTools.privacyPolicy(this)
    }

    public fun makeBackButtonVISIBLE() {
        back_btn?.visibility=View.VISIBLE
    }

    public fun exatApp() {
        GeneralTools.exitDialog(this)
    }

    public fun footballCase() {
        heading?.setText(getString(R.string.football))
        baseHomeFragments?.footballCase()
    }

    public fun basketballCase() {
        heading?.setText(getString(R.string.basketball))
        baseHomeFragments?.basketballCase()
    }

    public fun showPopup() {
        if (showPopupMessageCheck(this))
            GeneralTools.messageDialog(this)

//        if (!getPromptFrequencyFromSP(this).equals("empty") && !getPromptFrequencyFromSP(this).equals("done")&& !getPromptFrequencyFromSP(this).equals("0")
//        ) {
//            GeneralTools.messageDialog(this)
//        }
    }

    public fun openWebView() {
        if (!getOpenWebViewBeforeFromSP(this).equals("no") && mapArrayList.size != 0)
            showDialogWebView(mapArrayList.get(0).map_link)
    }

    public fun showDialogForLanguages() {
        var shouldRefresh=false
        val dialog=Dialog(this,android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.language_dialog)
        val english=dialog.findViewById<View>(R.id.english)
        val chinese=dialog.findViewById<View>(R.id.chinese)
        val indonesian=dialog.findViewById<View>(R.id.indonesian)
        val vietnam=dialog.findViewById<View>(R.id.vietnamese)
        val thai=dialog.findViewById<View>(R.id.thai)

        val english_radio=dialog.findViewById<View>(R.id.english_radio)
        val chinese_radio=dialog.findViewById<View>(R.id.chinese_radio)
        val indonesian_radio=dialog.findViewById<View>(R.id.indonesian_radio)
        val vietnam_radio=dialog.findViewById<View>(R.id.vietnamese_radio)
        val thai_radio=dialog.findViewById<View>(R.id.thai_radio)
        //val back_btn = dialog.findViewById<RelativeLayout>(R.id.back_btn_rl)

        dialog.findViewById<View>(R.id.back_btn_rl).setOnClickListener {
            dialog.dismiss()
            if (shouldRefresh)
                recreate()
        }
        val english_text=dialog.findViewById<TextView>(R.id.english_text)
        val chinese_text=dialog.findViewById<TextView>(R.id.chinese_text)
        val indonesian_text=dialog.findViewById<TextView>(R.id.indonesian_text)
        val vietnamese_text=dialog.findViewById<TextView>(R.id.vietnamese_text)
        val thai_text=dialog.findViewById<TextView>(R.id.thai_text)



        fun toggleSelected(){

            when(GeneralTools.getLocale(this)){
                SharedPreference.ENGLISH->{

                    english_radio.visibility=View.VISIBLE
                    chinese_radio.visibility=View.GONE
                    indonesian_radio.visibility=View.GONE
                    vietnam_radio.visibility=View.GONE
                    thai_radio.visibility=View.GONE
                }
                SharedPreference.CHINESE->{

                    chinese_radio.visibility=View.VISIBLE
                    english_radio.visibility=View.GONE
                    indonesian_radio.visibility=View.GONE
                    vietnam_radio.visibility=View.GONE
                    thai_radio.visibility=View.GONE

                }
                SharedPreference.INDONESIAN->{
                    indonesian_radio.visibility=View.VISIBLE
                    english_radio.visibility=View.GONE
                    chinese_radio.visibility=View.GONE
                    vietnam_radio.visibility=View.GONE
                    thai_radio.visibility=View.GONE
                }
                SharedPreference.VIETNAMESE->{
                    vietnam_radio.visibility=View.VISIBLE
                    indonesian_radio.visibility=View.GONE
                    english_radio.visibility=View.GONE
                    chinese_radio.visibility=View.GONE
                    thai_radio.visibility=View.GONE
                }
                SharedPreference.THAI->{
                    thai_radio.visibility=View.VISIBLE
                    vietnam_radio.visibility=View.GONE
                    indonesian_radio.visibility=View.GONE
                    english_radio.visibility=View.GONE
                    chinese_radio.visibility=View.GONE
                }

            }
        }

        toggleSelected()

        english.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.ENGLISH)
            shouldRefresh=true
            toggleSelected()
            restartApp()
        }
        chinese.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.CHINESE)
            shouldRefresh=true
            toggleSelected()
            restartApp()
        }
        indonesian.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.INDONESIAN)
            shouldRefresh=true
            toggleSelected()
            restartApp()
        }
        vietnam.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.VIETNAMESE)
            shouldRefresh=true
            toggleSelected()
            restartApp()
        }
        thai.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.THAI)
            shouldRefresh=true
            toggleSelected()
            restartApp()
        }

        dialog.show()
        dialog.setCancelable(false)
    }

    public fun showDialogWebView(url:String) {
        var shouldRefresh=false
        val dialog=Dialog(this,android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.web_view_dialog)

        val web_view = dialog.findViewById<WebView>(R.id.web_vew)

//        Log.i("TAG","getUrlFromSP(this): "+getUrlFromSP(this))
//getUrlFromSP(this)
        web_view.setWebViewClient(WebViewClient())
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl(url)

        dialog.findViewById<View>(R.id.back_btn_rl_web_view).setOnClickListener {
            dialog.dismiss()
            if (shouldRefresh)
                recreate()
        }

        dialog.show()
        dialog.setCancelable(false)
    }



    private fun restartApp() {
        val intent = Intent(this@BaseActivity, SplashScreen::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (shouldChangeBackpress){
            when(currentFrag){
                is NewsFragment->{
                    val frag=currentFrag as NewsFragment
                    frag.hideDetailFrag()
                }
                is BaseHomeFragments->{
                    val frag=currentFrag as BaseHomeFragments
                    frag.hideFragment()
                }
            }
            //GeneralTools.flipReplaceAnimation(findViewById(R.id.back_btn),findViewById(R.id.menu_icon))
            shouldChangeBackpress=false
            findViewById<TextView>(R.id.top_heading_mainpage).text=getString(R.string.app_name)
            back_btn?.visibility=View.GONE
        }else{
           GeneralTools.exitDialog(this)
        }
    }

    override fun changeBackPressBehaviour(currentFragment:Fragment) {
        this.currentFrag=currentFragment
        shouldChangeBackpress=true
        //GeneralTools.flipReplaceAnimation(findViewById(R.id.menu_icon),findViewById(R.id.back_btn))

    }

    override fun changeBackPressBehaviour(currentFragment: Fragment, message: String) {
        findViewById<TextView>(R.id.top_heading_mainpage).text=message
        changeBackPressBehaviour(currentFragment)
    }

    private fun setLocale(locale: Locale) {
        val resources: Resources = resources
        val configuration: Configuration = resources.configuration
        val displayMetrics: DisplayMetrics = resources.displayMetrics
        configuration.setLocale(locale)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            applicationContext.createConfigurationContext(configuration)
        } else {
            resources.updateConfiguration(configuration, displayMetrics)
        }
    }

    private fun statusBarColor() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }


    public fun showLega(leagueList: ArrayList<LegaDetails>) {
        var dialog=Dialog(this,android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.league_list)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val recycler_view = dialog.findViewById<RecyclerView>(R.id.leags_rv)
        val search_leags_edt =  dialog.findViewById<EditText>(R.id.search_leags_edt)

        recycler_view.adapter=LegasAdapter(this, leagueList!! as ArrayList<LegaDetails>,
            object : RecyclerViewOnclick{
                override fun onClick(position: Int) {

                    baseHomeFragments?.getLeagaMatches(position)
                    dialog.dismiss()
                }
            })

        recycler_view.layoutManager=
            LinearLayoutManager(this.applicationContext, LinearLayoutManager.VERTICAL,false)

        search_leags_edt.addTextChangedListener {
            val filterArrayList2: ArrayList<LegaDetails> = ArrayList<LegaDetails>()
            for (leagDetails:LegaDetails in leagueList) {
                if (leagDetails.lega_name.toLowerCase()
                        .contains(search_leags_edt.text.toString().lowercase())
                ) {
                    filterArrayList2.add(leagDetails)
                }
            }
            (recycler_view!!.adapter as LegasAdapter).filterList(filterArrayList2)
        }


        dialog.show()
        dialog.setCanceledOnTouchOutside(false)

    }

    override fun onClickedAdsDetails(adsDetails: Ads?) {

        if (adsDetails!!.open_type.equals("1")) {
            showDialogWebView(adsDetails.redirect_url)//keep error
        }
    }


}