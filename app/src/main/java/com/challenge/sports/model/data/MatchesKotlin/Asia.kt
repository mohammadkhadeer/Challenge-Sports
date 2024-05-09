package com.challenge.sports.model.data.MatchesKotlin


import com.google.gson.annotations.SerializedName

data class Asia(
    @SerializedName("away")
    val away: Int?,
    @SerializedName("handicap")
    val handicap: Double?,
    @SerializedName("home")
    val home: Double?
)