package com.example.myapplication.model.data.basketball.homepage


import com.google.gson.annotations.SerializedName

data class BaseIndexBasketball(
    @SerializedName("matchList")
    var matchList: List<Match> = listOf()
)