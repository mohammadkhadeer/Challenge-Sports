package com.challenge.sports.model.data.basketball.homepage.past.future


import com.google.gson.annotations.SerializedName

data class PastFutureBasketBall(
    @SerializedName("matchList")
    var matchList: List<Match> = listOf()
)