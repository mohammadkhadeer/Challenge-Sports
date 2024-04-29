package com.challenge.sports.view

import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import score.pro.R
import com.challenge.sports.model.data.onboarding.OnboardingObject
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.view.adapters.ViewPagerAdapter
import com.challenge.sports.view.fragments.onboarding.OnboardingFragment
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
        statusBarColor()

        val viewpager=findViewById<ViewPager2>(R.id.viewpager_onboarding)
        val wormDots=findViewById<WormDotsIndicator>(R.id.onbaording_dots_indicator)
        val heading=findViewById<TextView>(R.id.onboarding_heading)
        val body=findViewById<TextView>(R.id.onboarding_body)
        val skip=findViewById<TextView>(R.id.skip_bt)
        val list=ArrayList<OnboardingObject>()
        if (!SharedPreference.getInstance().getBooleanValueFromPreference(SharedPreference.HAS_SELECTED_FIRST_LANG,false,this)){
            //showDialogForLanguages()
        }
        list.add(OnboardingObject(R.drawable.onboarding_1,getString(R.string.welcome),getString(R.string.onboarding_body_1)))
        list.add(OnboardingObject(R.drawable.onboarding_2,getString(R.string.football),getString(R.string.onboarding_body_2)))
//        list.add(OnboardingObject(R.drawable.onboarding_3,getString(R.string.basketball),getString(R.string.onboarding_body_3)))
        list.add(OnboardingObject(R.drawable.onboarding_4,getString(R.string.live_score_update),getString(R.string.onboarding_body_4)))
//        list.add(OnboardingObject(R.drawable.onboarding_5,getString(R.string.match_statistics),getString(R.string.onboarding_body_5)))
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
        val paint = skip.paint
        val width = paint.measureText(skip.text.toString())

//        val textShader: Shader = LinearGradient(0f, 0f, width, skip.textSize, intArrayOf(
//            Color.parseColor("#EC3BDA"),
//            Color.parseColor("#F24CA2")
//        ), null, Shader.TileMode.REPEAT)
//        skip.paint.setShader(textShader)

        skip.setOnClickListener {
            SharedPreference.getInstance().saveBooleanToPreferences(SharedPreference.IS_FIRST_TIME,false,this)
            startActivity(Intent(this,BaseActivity::class.java))
            finish()
        }


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

        dialog.findViewById<View>(R.id.back_btn_rl).setOnClickListener {
            dialog.dismiss()
            if (shouldRefresh){
                SharedPreference.getInstance().saveBooleanToPreferences(SharedPreference.HAS_SELECTED_FIRST_LANG,true,this)
                recreate()
            }

        }

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

    private fun statusBarColor() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}