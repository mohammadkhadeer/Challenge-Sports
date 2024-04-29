package com.challenge.sports.model.data.basketball.odds


import com.google.gson.annotations.SerializedName

data class BasketballOddsBase(
    @SerializedName("List")
    var list: List<Odds> = listOf()
)