package com.football.live.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.football.live.model.api.ApiHelper
import com.football.live.viewmodel.AuthViewModel
import com.football.live.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       /* if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper) as T//TODO: Replace with Viewmodel
        } */
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper) as T//TODO: Replace with Viewmodel
        }else if (modelClass.isAssignableFrom(AuthViewModel::class.java)){
            return AuthViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}