package com.five.live.view

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.PorterDuff
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.five.live.utils.GeneralTools
import com.five.live.utils.SharedPreference
import com.five.live.view.adapters.ViewPagerAdapter
import com.five.live.view.fragments.OnBackPressedListener
import com.five.live.view.fragments.homeFrags.BaseHomeFragments
import com.five.live.view.fragments.news.NewsFragment
import com.five.live.view.fragments.standings.StandingBaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import corescore.myapplication.R
import java.util.*


class BaseActivity : AppCompatActivity() , OnBackPressedListener{
    var shouldChangeBackpress=false
    var currentFrag:Fragment?=null
    var footBallList = ArrayList<String>()
    var baseHomeFragments:BaseHomeFragments?=null

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

        //val menuIcon=findViewById<View>(R.id.menu_icon)
//        menuIcon.setOnClickListener {
//            findViewById<DrawerLayout>(R.id.drawer_layout).openDrawer(GravityCompat.START)
//        }

//        findViewById<View>(R.id.language_layout).setOnClickListener {
//            showDialogForLanguages()
//        }
//        findViewById<View>(R.id.exit_app).setOnClickListener {
//            exatApp()
//        }
//        findViewById<View>(R.id.privacy_layout).setOnClickListener {
//            getToPrivacyPolicy()
//        }
//        findViewById<View>(R.id.feedback).setOnClickListener {
//            goToFeedBack()
//        }
//        findViewById<View>(R.id.share_app).setOnClickListener {
//            goToShareApp()
//        }
//        findViewById<View>(R.id.rate_us).setOnClickListener {
//            goToRateUs()
//        }

        val searchIcon=findViewById<View>(R.id.search_icon)
        val closeSearchIcon=findViewById<View>(R.id.cross_icon)
        val searchBar=findViewById<EditText>(R.id.search_matches)
        val edt_cont=findViewById<View>(R.id.edt_cont)
        val spinnerFootBallOrBasketBall = findViewById<AppCompatSpinner>(R.id.league_spinner)

        val heading=findViewById<TextView>(R.id.top_heading_mainpage)
        //gradient text color
        val paint = heading.paint
        val width = paint.measureText(heading.text.toString())

        footBallList.add(getString(R.string.football))
        footBallList.add(getString(R.string.basketball))


        spinnerFootBallOrBasketBall.getBackground().setColorFilter(Color.parseColor("#F24CA2"), PorterDuff.Mode.SRC_ATOP);

        spinnerFootBallOrBasketBall.adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            footBallList
        )


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
                            baseHomeFragments?.footballCase()
                        }
                        else -> {
                            baseHomeFragments?.basketballCase()
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }



        val textShader: Shader = LinearGradient(0f, 0f, width, heading.textSize, intArrayOf(
            Color.parseColor("#EC3BDA"),
            Color.parseColor("#F24CA2")
        ), null, Shader.TileMode.REPEAT)
        heading.paint.setShader(textShader)

        val baseViewPager=findViewById<ViewPager2>(R.id.baseViewPager)
        val newsFrag= NewsFragment.newInstance(false,":")
        val homeFragment=BaseHomeFragments.newInstance("","")
        this.baseHomeFragments=homeFragment
        findViewById<View>(R.id.back_btn).setOnClickListener {
            onBackPressed()
        }
        homeFragment.onBackPressedListener=this
        val fragsList=ArrayList<Fragment>()
        fragsList.add(homeFragment)
        fragsList.add(newsFrag)
        fragsList.add(StandingBaseFragment.newInstance("",""))
        newsFrag.setChangeBackPressBehaviourListener(this)

        baseViewPager.adapter=ViewPagerAdapter(supportFragmentManager,lifecycle,fragsList)
        baseViewPager.isUserInputEnabled=false
        baseViewPager.offscreenPageLimit=3

        baseViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
//                if (position!=0){
//                    searchIcon.visibility=View.GONE
//                }else{
//                    searchIcon.visibility=View.VISIBLE
//                }
                if(position ==0)
                {
                    heading.setText(getString(R.string.football))
                    spinnerFootBallOrBasketBall.visibility=View.VISIBLE
                    heading.visibility=View.GONE
                }

                if(position ==1)
                {
                    heading.setText(getString(R.string.news))
                    heading.visibility=View.VISIBLE
                    spinnerFootBallOrBasketBall.visibility=View.GONE
                }

                if(position ==2)
                {
                    heading.setText(getString(R.string.settings))
                    heading.visibility=View.VISIBLE
                    spinnerFootBallOrBasketBall.visibility=View.GONE
                }
            }
        })
        //baseViewPager.setPageTransformer(Pager2_GateTransformer())
        closeSearchIcon.setOnClickListener {
            if (searchBar.visibility==View.VISIBLE){
                closeSearchIcon.visibility=View.GONE
                edt_cont.visibility=View.GONE
                spinnerFootBallOrBasketBall.visibility=View.VISIBLE
                GeneralTools.flipReplaceAnimation(searchBar,heading)
                //GeneralTools.flipReplaceAnimation(it,menuIcon)
                homeFragment.searchMatch("")
            }
        }
        searchIcon.setOnClickListener {
            if (searchBar.visibility==View.GONE){
                edt_cont.visibility=View.VISIBLE
                closeSearchIcon.visibility=View.VISIBLE
                spinnerFootBallOrBasketBall.visibility=View.GONE
                GeneralTools.flipReplaceAnimation(heading,searchBar)
                //GeneralTools.flipReplaceAnimation(menuIcon,closeSearchIcon)
            }else{

                homeFragment.searchMatch(searchBar.editableText.toString())
            }
        }
        searchBar.addTextChangedListener {
            homeFragment.searchMatch(it.toString())

        }
        val tabLayout=findViewById<TabLayout>(R.id.tabLayoutMain)
        TabLayoutMediator(tabLayout,baseViewPager){tab,position->
            when(position){
                0->{
                    //tab.text=getString(R.string.home)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.ic_home)
                }
                1->{
                    //tab.text=getString(R.string.news)
                    tab.icon=getDrawable(R.drawable.ic_news)

                }
                2->{
                    //tab.text=getString(R.string.standings)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.ic_standings)

                }
            }
        }.attach()
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
}