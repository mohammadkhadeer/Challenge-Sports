package com.challenge.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.sports.model.api.ApiHelper
import com.challenge.sports.model.data.generalized.GeneralTokenResponse
import com.challenge.sports.model.data.generalized.OtpResponse
import com.challenge.sports.utils.Resource
import kotlinx.coroutines.launch

class AuthViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    var loginLiveDataListener = MutableLiveData<Resource<GeneralTokenResponse>>()
    var otpRequestBodyLiveData = MutableLiveData<Resource<OtpResponse>>()


}