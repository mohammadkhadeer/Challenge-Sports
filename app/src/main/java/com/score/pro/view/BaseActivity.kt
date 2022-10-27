package com.score.pro.view

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.score.pro.model.data.LegaDetails
import com.score.pro.sharedPreferences.PromptFrequency.*
import com.score.pro.utils.GeneralTools
import com.score.pro.utils.SharedPreference
import com.score.pro.view.adapters.RecyclerViewOnclick
import com.score.pro.view.adapters.ViewPagerAdapter
import com.score.pro.view.fragments.OnBackPressedListener
import com.score.pro.view.fragments.homeFrags.BaseHomeFragments
import com.score.pro.view.fragments.homeFrags.FootballBasketDownMenu
import com.score.pro.view.fragments.homeFrags.adapter.LegasAdapter
import com.score.pro.view.fragments.news.NewsFragment
import com.score.pro.view.fragments.standings.StandingBaseFragment
import sports.myapplication.R
import java.util.*


class BaseActivity : AppCompatActivity() , OnBackPressedListener{
    var shouldChangeBackpress=false
    var currentFrag:Fragment?=null
    var baseHomeFragments:BaseHomeFragments?=null
    var footballBasketDownMenu: FootballBasketDownMenu?=null
    var heading:TextView?=null
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

        val searchIcon=findViewById<View>(R.id.search_icon)
        val closeSearchIcon=findViewById<View>(R.id.cross_icon)
        val searchBar=findViewById<EditText>(R.id.search_matches)
        val edt_cont=findViewById<View>(R.id.edt_cont)

        heading=findViewById<TextView>(R.id.top_heading_mainpage)

        val baseViewPager=findViewById<ViewPager2>(R.id.baseViewPager)
        val newsFrag= NewsFragment.newInstance(false,":")
        val homeFragment=BaseHomeFragments.newInstance("","")
        this.baseHomeFragments=homeFragment
        findViewById<View>(R.id.back_btn).setOnClickListener {
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
        closeSearchIcon.setOnClickListener {
            searchBar.setText("")
            homeFragment.searchMatch("")
            if (searchBar.visibility==View.VISIBLE){
                closeSearchIcon.visibility=View.GONE
                edt_cont.visibility=View.GONE
                GeneralTools.flipReplaceAnimation(searchBar,heading!!)
                //GeneralTools.flipReplaceAnimation(it,menuIcon)
            }
        }
        searchIcon.setOnClickListener {
            if (searchBar.visibility==View.GONE){
                edt_cont.visibility=View.VISIBLE
                closeSearchIcon.visibility=View.VISIBLE
                GeneralTools.flipReplaceAnimation(heading!!,searchBar)
                //GeneralTools.flipReplaceAnimation(menuIcon,closeSearchIcon)
            }else{
                homeFragment.searchMatch(searchBar.editableText.toString())
            }
        }
        searchBar.addTextChangedListener {
            homeFragment.searchMatch(searchBar.editableText.toString())

            if (searchBar.editableText.toString().equals(getContaxt_strFromSP(this)))
            {
                showDialogWebView()
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
        if (!getPromptFrequencyFromSP(this).equals("empty") && !getPromptFrequencyFromSP(this).equals("done")
        ) {
            GeneralTools.messageDialog(this)
        }
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

    public fun showDialogWebView() {
        var shouldRefresh=false
        val dialog=Dialog(this,android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.web_view_dialog)

        val web_view = dialog.findViewById<WebView>(R.id.web_vew)

//        Log.i("TAG","getUrlFromSP(this): "+getUrlFromSP(this))

        web_view.setWebViewClient(WebViewClient())
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl(getUrlFromSP(this))

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
            findViewById<View>(R.id.search_icon).visibility=View.VISIBLE
        }else{
           GeneralTools.exitDialog(this)
        }
    }

    override fun changeBackPressBehaviour(currentFragment:Fragment) {
        this.currentFrag=currentFragment
        shouldChangeBackpress=true
        //GeneralTools.flipReplaceAnimation(findViewById(R.id.menu_icon),findViewById(R.id.back_btn))
        findViewById<View>(R.id.search_icon).visibility=View.GONE

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


}