package com.corescore.myapplication.model.data.news.details

interface OnPostDetailResponse<T> {
    fun onSuccess(responseBody:T)
    fun onFailure(message:String)
    fun onLoading(message:String)
}