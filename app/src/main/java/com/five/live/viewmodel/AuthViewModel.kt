package com.five.live.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.five.live.model.api.ApiHelper
import com.five.live.model.data.generalized.GeneralTokenResponse
import com.five.live.model.data.generalized.OtpResponse
import com.five.live.model.data.raw.LoginRequestBody
import com.five.live.model.data.raw.OTPRequestBody
import com.five.live.utils.Resource
import kotlinx.coroutines.launch

class AuthViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    var loginLiveDataListener = MutableLiveData<Resource<GeneralTokenResponse>>()
    var otpRequestBodyLiveData = MutableLiveData<Resource<OtpResponse>>()

    private fun sendOTP(number: String, type: String) {
        otpRequestBodyLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val response = apiHelper.sendOTP(OTPRequestBody(number, type, number))
                otpRequestBodyLiveData.postValue(Resource.success(response))
            } catch (e: Exception) {
                otpRequestBodyLiveData.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    private fun loginUser(number: String, password: String) {
        loginLiveDataListener.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val response = apiHelper.authenticateUser(LoginRequestBody(number, password))
                loginLiveDataListener.postValue(Resource.success(response))

            } catch (e: Exception) {

                loginLiveDataListener.postValue(Resource.error(e.toString(), null))

            }
        }
    }

}