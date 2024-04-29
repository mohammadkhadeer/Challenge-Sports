package com.challenge.sports.view.fragments.authFrags

import androidx.fragment.app.Fragment

interface AuthFragmentCommunicationListener {
    fun onMessageFromFragment(fragment:Fragment,message:FragmentMessage)
}
enum class FragmentMessage{
    CHANGE_TO_SIGN_UP,
    CHANGE_TO_LOGIN,
    CHANGE_TO_FORGOT_PW,
    LOGIN_SUCCESS
}