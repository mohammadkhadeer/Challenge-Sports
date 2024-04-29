package com.challenge.sports.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.challenge.sports.model.api.ApiHelperImpl
import com.challenge.sports.model.api.RetroInstance
import com.challenge.sports.viewmodel.AuthViewModel
import com.challenge.sports.viewmodel.MainViewModel

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