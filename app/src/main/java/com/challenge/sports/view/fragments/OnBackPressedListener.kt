package com.challenge.sports.view.fragments

import androidx.fragment.app.Fragment

interface OnBackPressedListener {
    fun changeBackPressBehaviour(currentFragment: Fragment)
    fun changeBackPressBehaviour(currentFragment: Fragment,message:String)
}