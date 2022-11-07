package com.football.live.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.football.live.model.api.ApiHelperImpl
import com.football.live.model.api.RetroInstance
import com.football.live.viewmodel.AuthViewModel
import com.football.live.viewmodel.MainViewModel

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