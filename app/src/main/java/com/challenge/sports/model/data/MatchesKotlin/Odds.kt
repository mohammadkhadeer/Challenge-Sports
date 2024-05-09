package com.challenge.sports.model.data.MatchesKotlin


import com.google.gson.annotations.SerializedName

data class Odds(
    @SerializedName("init")
    val `init`: Init?
)