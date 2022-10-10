package com.corescore.myapplication.view

import android.app.Dialog
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import corescore.myapplication.R
import com.corescore.myapplication.utils.GeneralTools
import com.corescore.myapplication.utils.SharedPreference
import com.corescore.myapplication.view.adapters.ViewPagerAdapter
import com.corescore.myapplication.view.fragments.OnBackPressedListener
import com.corescore.myapplication.view.fragments.homeFrags.BaseHomeFragments
import com.corescore.myapplication.view.fragments.news.NewsFragment
import com.corescore.myapplication.view.fragments.standings.StandingBaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class BaseActivity : AppCompatActivity() , OnBackPressedListener{
    var shouldChangeBackpress=false
    var currentFrag:Fragment?=null
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
        val menuIcon=findViewById<View>(R.id.menu_icon)
        menuIcon.setOnClickListener {
            findViewById<DrawerLayout>(R.id.drawer_layout).openDrawer(GravityCompat.START)
        }

        findViewById<View>(R.id.language_layout).setOnClickListener {
            showDialogForLanguages()
        }
        findViewById<View>(R.id.exit_app).setOnClickListener {
            GeneralTools.exitDialog(this)
        }
        findViewById<View>(R.id.privacy_layout).setOnClickListener {
            GeneralTools.privacyPolicy(this)
        }
        findViewById<View>(R.id.feedback).setOnClickListener {
            GeneralTools.feedback(this)
        }
        findViewById<View>(R.id.share_app).setOnClickListener {
            GeneralTools.shareApp(this)
        }
        findViewById<View>(R.id.rate_us).setOnClickListener {
            GeneralTools.rateUs(this)
        }

        val searchIcon=findViewById<View>(R.id.search_icon)
        val closeSearchIcon=findViewById<View>(R.id.cross_icon)
        val searchBar=findViewById<EditText>(R.id.search_matches)
        val heading=findViewById<TextView>(R.id.top_heading_mainpage)
        val baseViewPager=findViewById<ViewPager2>(R.id.baseViewPager)
        val newsFrag= NewsFragment.newInstance(false,":")
        val homeFragment=BaseHomeFragments.newInstance("","")
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
                if (position!=0){
                    searchIcon.visibility=View.GONE
                }else{
                    searchIcon.visibility=View.VISIBLE
                }
            }
        })
        //baseViewPager.setPageTransformer(Pager2_GateTransformer())
        closeSearchIcon.setOnClickListener {
            if (searchBar.visibility==View.VISIBLE){
                GeneralTools.flipReplaceAnimation(searchBar,heading)
                GeneralTools.flipReplaceAnimation(it,menuIcon)
                homeFragment.searchMatch("")
            }
        }
        searchIcon.setOnClickListener {
            if (searchBar.visibility==View.GONE){
                GeneralTools.flipReplaceAnimation(heading,searchBar)
                GeneralTools.flipReplaceAnimation(menuIcon,closeSearchIcon)
            }else{
                homeFragment.searchMatch(searchBar.editableText.toString())
            }
        }

        val tabLayout=findViewById<TabLayout>(R.id.tabLayoutMain)
        TabLayoutMediator(tabLayout,baseViewPager){tab,position->
            when(position){
                0->{
                    tab.text=getString(R.string.home)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.ic_home)

                }
                1->{
                    tab.text=getString(R.string.news)
                    tab.icon=getDrawable(R.drawable.ic_news)
                }
                2->{
                    tab.text=getString(R.string.standings)
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.ic_standings)
                }
            }
        }.attach()
    }

    private fun showDialogForLanguages() {
        var shouldRefresh=false
        val dialog=Dialog(this,android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.language_dialog)
        val english=dialog.findViewById<View>(R.id.english)
        val chinese=dialog.findViewById<View>(R.id.chinese)
        val indonesian=dialog.findViewById<View>(R.id.indonesian)
        val vietnam=dialog.findViewById<View>(R.id.vietnamese)
        val thai=dialog.findViewById<View>(R.id.thai)

        val english_text=dialog.findViewById<TextView>(R.id.english_text)
        val chinese_text=dialog.findViewById<TextView>(R.id.chinese_text)
        val indonesian_text=dialog.findViewById<TextView>(R.id.indonesian_text)
        val vietnamese_text=dialog.findViewById<TextView>(R.id.vietnamese_text)
        val thai_text=dialog.findViewById<TextView>(R.id.thai_text)

        dialog.findViewById<View>(R.id.back_btn).setOnClickListener {
            dialog.dismiss()
            if (shouldRefresh)
            recreate()
        }

       fun toggleSelected(){
           english.backgroundTintList =null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               english_text.setTextColor(Color.BLACK)
           }
           chinese.backgroundTintList =
               null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               chinese_text.setTextColor(Color.BLACK)
           }
           indonesian.backgroundTintList =
                null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               indonesian_text.setTextColor(Color.BLACK)
           }
           vietnam.backgroundTintList =
                null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               vietnamese_text.setTextColor(Color.BLACK)
           }
           thai.backgroundTintList =
                null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               thai_text.setTextColor(Color.BLACK)
           }

           when(GeneralTools.getLocale(this)){
               SharedPreference.ENGLISH->{
                   english.backgroundTintList =
                       ContextCompat.getColorStateList(this, R.color.colorPrimary)
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       english_text.setTextColor(getColor(R.color.white))
                   }
               }
               SharedPreference.CHINESE->{
                   chinese.backgroundTintList =
                       ContextCompat.getColorStateList(this, R.color.colorPrimary)
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       chinese_text.setTextColor(getColor(R.color.white))
                   }
               }
               SharedPreference.INDONESIAN->{
                   indonesian.backgroundTintList =
                       ContextCompat.getColorStateList(this, R.color.colorPrimary)
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       indonesian_text.setTextColor(getColor(R.color.white))
                   }
               }
               SharedPreference.VIETNAMESE->{
                   vietnam.backgroundTintList =
                       ContextCompat.getColorStateList(this, R.color.colorPrimary)
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       vietnamese_text.setTextColor(getColor(R.color.white))
                   }
               }
               SharedPreference.THAI->{

                   thai.backgroundTintList =
                       ContextCompat.getColorStateList(this, R.color.colorPrimary)
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       thai_text.setTextColor(getColor(R.color.white))
                   }
               }

           }
       }

        toggleSelected()

        english.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.ENGLISH)
            shouldRefresh=true
            toggleSelected()
        }
        chinese.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.CHINESE)
            shouldRefresh=true
            toggleSelected()
        }
        indonesian.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.INDONESIAN)
            shouldRefresh=true
            toggleSelected()
        }
        vietnam.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.VIETNAMESE)
            shouldRefresh=true
            toggleSelected()
        }
        thai.setOnClickListener {
            GeneralTools.setLocale(this,SharedPreference.THAI)
            shouldRefresh=true
            toggleSelected()
        }

        dialog.show()
        dialog.setCancelable(false)
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
            GeneralTools.flipReplaceAnimation(findViewById(R.id.back_btn),findViewById(R.id.menu_icon))
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
        GeneralTools.flipReplaceAnimation(findViewById(R.id.menu_icon),findViewById(R.id.back_btn))
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