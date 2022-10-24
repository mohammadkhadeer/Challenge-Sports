package com.score.pro.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.score.pro.model.api.ApiHelperImpl
import com.score.pro.model.api.RetroInstance
import com.score.pro.viewmodel.AuthViewModel
import com.score.pro.viewmodel.MainViewModel

object SpewViewModel {
    fun giveMeViewModel(context: ComponentActivity): MainViewModel {
        return ViewModelProvider(
            context.viewModelStore, ViewModelFactory(
                ApiHelperImpl(RetroInstance.apiService)
            )).get(MainViewModel::class.java)
    }
    fun giveMeViewModelAuth(context: ComponentActivity): AuthViewModel {
        return ViewModelProvider(
            context.viewModelStore, ViewModelFactory(ApiHelperImpl(RetroInstance.apiService)))
            .get(AuthViewModel::class.java)
    }
}