package com.challenge.sports.model.data.MatchesKotlin


import com.google.gson.annotations.SerializedName

data class Coverage(
    @SerializedName("lineup")
    val lineup: Int?,
    @SerializedName("mlive")
    val mlive: Int?
)