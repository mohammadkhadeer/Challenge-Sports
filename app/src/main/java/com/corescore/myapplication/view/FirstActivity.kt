package com.corescore.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import corescore.myapplication.R
import com.corescore.myapplication.utils.SharedPreference
import com.corescore.myapplication.view.fragments.authFrags.AuthFragmentCommunicationListener
import com.corescore.myapplication.view.fragments.authFrags.FragmentMessage
import com.corescore.myapplication.view.fragments.authFrags.LoginFrag
import com.corescore.myapplication.view.fragments.authFrags.SignupFragment
import java.util.*

class FirstActivity : AppCompatActivity(), AuthFragmentCommunicationListener {
    var loginFrag:LoginFrag?=null
    var signupFrag:SignupFragment?=null
    var currentFragmnet:Fragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        object : CountDownTimer(1200, 300) {
            override fun onTick(p0: Long) {

            }
            override fun onFinish() {
                if (SharedPreference.getInstance().getStringValueFromPreference(
                        SharedPreference.AUTH_TOKEN_KEY,
                        SharedPreference.EMPTY_KEY,
                        this@FirstActivity
                    ).equals(SharedPreference.EMPTY_KEY)
                ) {
                    startLoginFlow()
                }
            }
        }.start()
    }

    private fun startLoginFlow() {
        findViewById<View>(R.id.splash_layout).visibility = View.GONE
        val fragsContainer = findViewById<FrameLayout>(R.id.fragments_container)
        fragsContainer.visibility = View.VISIBLE
        loginFrag = LoginFrag.newInstance("", "")
        loginFrag?.setFragmentCommunicator(this)
        signupFrag = SignupFragment.newInstance("", "")
        signupFrag?.setFragmentCommunicator(this)
        try {
            replaceFragment(loginFrag!!)
        }catch (e:Exception){
            println(e.message)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val transactionManager = supportFragmentManager.beginTransaction()
        transactionManager.replace(R.id.fragments_container, fragment)
        transactionManager.commit()
    }

    override fun onMessageFromFragment(fragment: Fragment, message: FragmentMessage) {
        when (message) {

            FragmentMessage.CHANGE_TO_FORGOT_PW -> {

            }
            FragmentMessage.CHANGE_TO_LOGIN -> {

                try {
                    replaceFragment(signupFrag!!)
                }catch (e:Exception){
                    try {
                        signupFrag=SignupFragment.newInstance("","")
                        replaceFragment(signupFrag!!)
                    }catch (e:Exception){
                        println(e.toString())
                    }
                }
            }
            FragmentMessage.CHANGE_TO_SIGN_UP -> {

            }
            FragmentMessage.LOGIN_SUCCESS -> {

            }

        }
    }
}
