package com.example.myapplication.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.model.api.ApiHelperImpl
import com.example.myapplication.model.api.RetroInstance
import com.example.myapplication.viewmodel.AuthViewModel
import com.example.myapplication.viewmodel.MainViewModel

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