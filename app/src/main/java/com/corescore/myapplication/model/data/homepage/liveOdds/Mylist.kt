package com.corescore.myapplication.model.data.homepage.liveOdds


import com.google.gson.annotations.SerializedName

data class MyList(
    @SerializedName("europeOdds")
    var europeOdds: List<List<Any>> = listOf(),
    @SerializedName("handicap")
    var handicap: List<List<Any>> = listOf(),
    @SerializedName("handicapHalf")
    var handicapHalf: List<List<Any>> = listOf(),
    @SerializedName("overUnder")
    var overUnder: List<List<Any>> = listOf(),
    @SerializedName("overUnderHalf")
    var overUnderHalf: List<List<Any>> = listOf()
)