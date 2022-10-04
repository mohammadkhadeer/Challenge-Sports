package com.corescore.myapplication.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.corescore.myapplication.model.api.ApiHelperImpl
import com.corescore.myapplication.model.api.RetroInstance
import com.corescore.myapplication.viewmodel.AuthViewModel
import com.corescore.myapplication.viewmodel.MainViewModel

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