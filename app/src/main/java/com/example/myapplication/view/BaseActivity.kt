package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.view.adapters.ViewPagerAdapter
import com.example.myapplication.view.fragments.OnBackPressedListener
import com.example.myapplication.view.fragments.homeFrags.BaseHomeFragments
import com.example.myapplication.view.fragments.news.NewsFragment
import com.example.myapplication.view.fragments.standings.StandingBaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BaseActivity : AppCompatActivity() , OnBackPressedListener{
    var shouldChangeBackpress=false
    var currentFrag:Fragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_activty)
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