package com.corescore.myapplication.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import corescore.myapplication.R
import com.corescore.myapplication.model.data.onboarding.OnboardingObject
import com.corescore.myapplication.utils.GeneralTools
import com.corescore.myapplication.utils.SharedPreference
import com.corescore.myapplication.view.adapters.ViewPagerAdapter
import com.corescore.myapplication.view.fragments.onboarding.OnboardingFragment
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import java.util.*
import kotlin.collections.ArrayList

class OnboardingActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_onboarding)
        val viewpager=findViewById<ViewPager2>(R.id.viewpager_onboarding)
        val wormDots=findViewById<WormDotsIndicator>(R.id.onbaording_dots_indicator)
        val heading=findViewById<TextView>(R.id.onboarding_heading)
        val body=findViewById<TextView>(R.id.onboarding_body)
        val list=ArrayList<OnboardingObject>()
        if (!SharedPreference.getInstance().getBooleanValueFromPreference(SharedPreference.HAS_SELECTED_FIRST_LANG,false,this)){
            showDialogForLanguages()
        }
        list.add(OnboardingObject(R.drawable.onboarding_1,getString(R.string.welcome),getString(R.string.onboarding_body_1)))
        list.add(OnboardingObject(R.drawable.onboarding_2,getString(R.string.football),getString(R.string.onboarding_body_2)))
        list.add(OnboardingObject(R.drawable.onboarding_3,getString(R.string.basketball),getString(R.string.onboarding_body_3)))
        list.add(OnboardingObject(R.drawable.onboarding_4,getString(R.string.live_score_update),getString(R.string.onboarding_body_4)))
        list.add(OnboardingObject(R.drawable.onboarding_5,getString(R.string.match_statistics),getString(R.string.onboarding_body_5)))
        list.add(OnboardingObject(R.drawable.onboarding_6,getString(R.string.experince_the_app),getString(R.string.onboarding_body_6)))
        val frags=ArrayList<Fragment>()
        list.forEach{
            frags.add(OnboardingFragment.newInstance(it.resId,""))
        }
        viewpager.adapter=ViewPagerAdapter(supportFragmentManager,lifecycle,frags)
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                heading.text=list[position].heading
                body.text=list[position].body
            }
        })
        wormDots.attachTo(viewpager)
        findViewById<View>(R.id.next_bt).setOnClickListener {
            val pos=viewpager.currentItem+1
            viewpager.setCurrentItem(pos,true)
            if (pos==viewpager!!.adapter!!.itemCount){
                SharedPreference.getInstance().saveBooleanToPreferences(SharedPreference.IS_FIRST_TIME,false,this)
                startActivity(Intent(this,BaseActivity::class.java))
                finish()
            }
        }

        findViewById<View>(R.id.skip_bt).setOnClickListener {
            SharedPreference.getInstance().saveBooleanToPreferences(SharedPreference.IS_FIRST_TIME,false,this)
            startActivity(Intent(this,BaseActivity::class.java))
            finish()
        }

    }

    private fun showDialogForLanguages() {
        var shouldRefresh=false
        val dialog= Dialog(this,android.R.style.ThemeOverlay)
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
            if (shouldRefresh){
                SharedPreference.getInstance().saveBooleanToPreferences(SharedPreference.HAS_SELECTED_FIRST_LANG,true,this)
                recreate()
            }

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
}