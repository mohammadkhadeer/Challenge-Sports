package com.challenge.sports.model.data.MatchesKotlin


import com.google.gson.annotations.SerializedName

data class BigSmall(
    @SerializedName("away")
    val away: Double?,
    @SerializedName("handicap")
    val handicap: Int?,
    @SerializedName("home")
    val home: Double?
)