package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.utils.GeneralTools
import com.example.myapplication.utils.transformer.Pager2_VerticalFlipTransformer
import com.example.myapplication.view.adapters.ViewPagerAdapter
import com.example.myapplication.view.fragments.OnBackPressedListener
import com.example.myapplication.view.fragments.homeFrags.BaseHomeFragments
import com.example.myapplication.view.fragments.news.NewsFragment
import com.example.myapplication.view.fragments.standings.StandingBaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.zip.GZIPOutputStream

class BaseActivity : AppCompatActivity() , OnBackPressedListener{
    var shouldChangeBackpress=false
    var currentFrag:Fragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_activty)

        val menuIcon=findViewById<View>(R.id.menu_icon)
        val searchIcon=findViewById<View>(R.id.search_icon)
        val closeSearchIcon=findViewById<View>(R.id.cross_icon)
        val searchBar=findViewById<EditText>(R.id.search_matches)
        val heading=findViewById<TextView>(R.id.top_heading_mainpage)

        val baseViewPager=findViewById<ViewPager2>(R.id.baseViewPager)
        val newsFrag= NewsFragment.newInstance(false,":")
        val homeFragment=BaseHomeFragments.newInstance("","")
        homeFragment.onBackPressedListener=this
        val fragsList=ArrayList<Fragment>()
        fragsList.add(homeFragment)
        fragsList.add(newsFrag)
        fragsList.add(StandingBaseFragment.newInstance("",""))
        newsFrag.setChangeBackPressBehaviourListener(this)
        baseViewPager.adapter=ViewPagerAdapter(supportFragmentManager,lifecycle,fragsList)
        baseViewPager.isUserInputEnabled=false
        baseViewPager.offscreenPageLimit=3
        baseViewPager.setPageTransformer(Pager2_VerticalFlipTransformer())


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
                    tab.text="Home"
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.ic_home)
                }
                1->{
                    tab.text="News"
                    tab.icon=getDrawable(R.drawable.ic_news)
                }
                2->{
                    tab.text="Standings"
                    tab.icon=ContextCompat.getDrawable(this,R.drawable.ic_standings)
                }
            }
        }.attach()
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
            shouldChangeBackpress=false
        }else{
            super.onBackPressed()
        }
    }

    override fun changeBackPressBehaviour(currentFragment:Fragment) {
        this.currentFrag=currentFragment
        shouldChangeBackpress=true
    }
}