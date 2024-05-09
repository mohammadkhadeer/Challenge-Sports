package com.challenge.sports.view.HomeActivity

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.challenge.sports.model.Ads
import com.challenge.sports.model.api.ListResponse.*
import com.challenge.sports.sharedPreferences.OpenWebView.*
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.view.adapters.ViewPagerAdapter
import com.challenge.sports.view.fragments.OnBackPressedListener
import com.challenge.sports.view.fragments.homeFrags.BaseHomeFragments
import com.challenge.sports.view.fragments.homeFrags.FootballBasketDownMenu
import com.challenge.sports.view.fragments.homeFrags.adapter.ViewPager2Adapter
import com.challenge.sports.view.fragments.news.NewsFragment
import com.challenge.sports.view.fragments.standings.StandingBaseFragment
import com.challenge.sports.view.HomeActivity.homeFragments.MatchesFragment
import com.challenge.sports.view.HomeActivity.homeFragments.ProfileFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import score.pro.R
import java.util.*

class BaseActivity : AppCompatActivity()
    , OnBackPressedListener, ViewPager2Adapter.PassAdsDetails {

    var shouldChangeBackpress=false
    var currentFrag:Fragment?=null
    var baseHomeFragments:BaseHomeFragments?=null
    var footballBasketDownMenu: FootballBasketDownMenu?=null
    var heading:TextView?=null
    var viewPager2: ViewPager2? = null

    var relativeLayout_con_viewPager2: RelativeLayout? = null
    var viewPager2Adapter: ViewPager2Adapter? = null

    var back_btn:ImageView? =null
    var web_view_button: RelativeLayout?=null

    var dialog: Dialog? = null
    var coun_down = 0
    var ct: CountDownTimer? = null
    var how_many_time_popup_show = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting language
        //config()

        setContentView(R.layout.activity_base_activty)
        statusBarColor()

        infintLoopForPopUp()

        val searchBar=findViewById<EditText>(R.id.search_matches)
        val edt_cont=findViewById<View>(R.id.edt_cont)
        back_btn= findViewById<View>(R.id.back_btn) as ImageView?

        viewPager2=findViewById<ViewPager2>(R.id.viewpager)
        relativeLayout_con_viewPager2=findViewById<RelativeLayout>(R.id.view2)

        handelSlider()
        checkToActiveWebButton()

        heading=findViewById<TextView>(R.id.top_heading_mainpage)

        val baseViewPager=findViewById<ViewPager2>(R.id.baseViewPager)

        val newsFrag= NewsFragment.newInstance(false,":")
        val homeFragment=BaseHomeFragments.newInstance("","")
        val matchFrag= MatchesFragment.newInstance()
        val profileFrag= ProfileFragment.newInstance()

        web_view_button=findViewById<RelativeLayout>(R.id.web_view_button)

        this.baseHomeFragments=homeFragment

        back_btn?.setOnClickListener {
            onBackPressed()
        }

        homeFragment.onBackPressedListener=this
        val fragsList=ArrayList<Fragment>()
        fragsList.add(newsFrag)
        fragsList.add(matchFrag)
        fragsList.add(StandingBaseFragment.newInstance("",""))
        fragsList.add(homeFragment)
        fragsList.add(profileFrag)

        newsFrag.setChangeBackPressBehaviourListener(this)

        baseViewPager.adapter=ViewPagerAdapter(supportFragmentManager,lifecycle,fragsList)
        baseViewPager.isUserInputEnabled=false
        baseViewPager.offscreenPageLimit=4


        searchBar.addTextChangedListener {
            homeFragment.searchMatch(searchBar.editableText.toString())
            if (searchBar.editableText.toString().equals(""))
            {
                homeFragment.searchMatch("")
            }else{
                var flag = 0
                for (i in mapArrayList){
                    if (searchBar.editableText.toString().equals(i.keyword))
                    {
                        flag = 1
                        //to active button open web view
                        activeWebButtonInSP(applicationContext,"active")
                        checkToActiveWebButton()
                        if (i.open_type.equals("1")) {
                            GeneralTools.openBrowser(this,i.redirect_url,i.open_type,"search")
                        } else {
                        }
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
                    tab.text=getString(R.string.home)
                    tab.icon=getDrawable(R.drawable.ic_home)
                }
                1->{
                    tab.text=getString(R.string.matches)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.matches)
                }
                2->{
                    tab.text=" "
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.upload)

                }
                3->{
                    tab.text=getString(R.string.discover)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.discoveries)
                }
                4->{
                    tab.text=getString(R.string.profile)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.profile_ic)
                }
            }
        }.attach()

        //to detect tab bar will start from where
        tabLayout.getTabAt(1)?.select();
    }

    private  fun config(){
        val languageToLoad = SharedPreference.getInstance().getStringValueFromPreference(
            SharedPreference.LOCALE_KEY,
            SharedPreference.CHINESE,this) // your language

        val config = applicationContext.resources.configuration
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        config.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            applicationContext.createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun infintLoopForPopUp() {
        if (repeat_time != null) {
            coun_down = repeat_time.toInt()
            coun_down = coun_down * 1000

            ct =object : CountDownTimer(100000000,coun_down.toLong()){
                override fun onTick(p0: Long) {

                    if (dialog != null && dialog?.isShowing() == false && repeat_status.equals("0")&& how_many_time_popup_show==0)
                    {
                        how_many_time_popup_show=how_many_time_popup_show+1
                        dialog?.show()
                    }

                    if (dialog != null && dialog?.isShowing() == false && repeat_status.equals("1"))
                    {
                        dialog?.show()
                    } else {
                        coun_down = repeat_time.toInt()
                        coun_down = coun_down * 1000
                    }
                }
                override fun onFinish() {
                }
            }.start()

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
        if (viewPager2!!.currentItem != (adsArrayList.size-1)) {
            viewPager2?.currentItem = viewPager2!!.currentItem+1
        } else {
            viewPager2?.currentItem = 0
        }
        infintLoop()
    }

    override fun onPause() {
        if (repeat_time != null) ct!!.cancel()
        super.onPause()
    }
    override fun onResume() {
        if (repeat_time != null) ct!!.start()
        super.onResume()
    }

    private fun checkToActiveWebButton() {
        if (getWebButtonActiveOrNoSP(applicationContext).equals("active"))
        {
            web_view_button?.visibility=View.VISIBLE
        }else{
            web_view_button?.visibility=View.GONE
        }
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

    private fun statusBarColor() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }


    override fun onClickedAdsDetails(adsDetails: Ads?) {
        activeWebButtonInSP(applicationContext,"active")
        checkToActiveWebButton()

        if (adsDetails!!.open_type.equals("1")) {
            GeneralTools.openBrowser(applicationContext,adsDetails!!.redirect_url,adsDetails!!.open_type,"from_init")
        } else {
            //web_view_on_the_top = true
        }
    }
}