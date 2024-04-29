package com.challenge.sports.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.challenge.sports.sharedPreferences.OpenWebView
import score.pro.R


class WebViewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    var web_view:WebView?=null
    var back_button:RelativeLayout? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        //from_init
        val url: String
        val from_where_come: String
        url = intent.getStringExtra("url")!!
        from_where_come = intent.getStringExtra("from_where")!!

        web_view = findViewById<WebView>(R.id.web_vew_activity)
        back_button = findViewById<RelativeLayout>(R.id.back_btn_rl_web_view_activity)

        web_view?.setWebViewClient(WebViewClient())
        web_view?.settings?.javaScriptEnabled = true
        web_view?.settings?.setDomStorageEnabled(true)
        web_view?.loadUrl(url)

        //Log.w("TAG", "from_where_come: $from_where_come")

        if (from_where_come.equals("from_init"))
        {
            back_button?.visibility=View.GONE
        }else{
            back_button?.visibility=View.VISIBLE
        }
        back_button?.setOnClickListener {
            OpenWebView.saveWebViewOnOrOffInSP(applicationContext, "off")

            finish()
        }


    }

    override fun onBackPressed() {
        //OpenWebView.saveWebViewOnOrOffInSP(applicationContext, "off")
        try {
            if (web_view!!.canGoBack()){
                web_view?.goBack()
            }else{
                finishAffinity()
            }
        }catch (e:Exception){
            super.onBackPressed()
        }

    }
}