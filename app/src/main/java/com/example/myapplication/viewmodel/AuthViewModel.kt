package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.api.ApiHelper
import com.example.myapplication.model.data.generalized.GeneralTokenResponse
import com.example.myapplication.model.data.generalized.OtpResponse
import com.example.myapplication.model.data.raw.LoginRequestBody
import com.example.myapplication.model.data.raw.OTPRequestBody
import com.example.myapplication.utils.Resource
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