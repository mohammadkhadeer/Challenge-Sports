package com.challenge.sports.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.challenge.sports.model.api.ApiReqBannerAds.sentReqBanner
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.view.HomeActivity.BaseActivity
import org.json.JSONException
import score.pro.R
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val localLocale = Locale.getDefault().language
        if (localLocale.contains("zh")) {
            GeneralTools.setLocale(applicationContext, SharedPreference.CHINESE)
        }
        val languageToLoad = SharedPreference.getInstance().getStringValueFromPreference(
            SharedPreference.LOCALE_KEY,
            SharedPreference.CHINESE, this
        ) // your language


//        val config = applicationContext.resources.configuration
//        val locale = Locale(languageToLoad)
//        Locale.setDefault(locale)
//        config.setLocale(locale)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//           applicationContext.createConfigurationContext(config)
//        resources.updateConfiguration(config, resources.displayMetrics)

        setContentView(R.layout.activity_splash_screen)

        val exo_tv=findViewById<TextView>(R.id.exo_score)

        try {
            Handler().postDelayed({ sentReqBanner(applicationContext) }, 100)
        } catch (e: JSONException) {
            e.printStackTrace()
        }



        object : CountDownTimer(1200,1200){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@SplashScreen, BaseActivity::class.java))

//                if (SharedPreference.getInstance().getBooleanValueFromPreference(SharedPreference.IS_FIRST_TIME,true,this@SplashScreen))
//                    startActivity(Intent(this@SplashScreen,OnboardingActivity::class.java))
//                else{
//                    startActivity(Intent(this@SplashScreen,BaseActivity::class.java))
//                }
                finish()
            }

        }.start()


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