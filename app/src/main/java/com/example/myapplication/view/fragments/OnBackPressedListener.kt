package com.example.myapplication.view.fragments

import androidx.fragment.app.Fragment

interface OnBackPressedListener {
    fun changeBackPressBehaviour(currentFragment: Fragment)
}