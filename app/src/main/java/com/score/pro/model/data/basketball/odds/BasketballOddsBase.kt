package com.score.pro.model.data.basketball.odds


import com.google.gson.annotations.SerializedName

data class BasketballOddsBase(
    @SerializedName("List")
    var list: List<Odds> = listOf()
)