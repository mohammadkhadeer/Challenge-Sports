package com.score.pro.model.data.homepage.new2


import com.google.gson.annotations.SerializedName

data class OddsX(
    @SerializedName("europeOdds")
    var europeOdds: List<Any> = listOf(),
    @SerializedName("handicap")
    var handicap: List<Any> = listOf(),
    @SerializedName("handicapHalf")
    var handicapHalf: List<Any> = listOf(),
    @SerializedName("overUnder")
    var overUnder: List<Any> = listOf(),
    @SerializedName("overUnderHalf")
    var overUnderHalf: List<Any> = listOf()
)