package com.challenge.sports.model.data.MatchesKotlin


import com.google.gson.annotations.SerializedName

data class Init(
    @SerializedName("asia")
    val asia: Asia?,
    @SerializedName("bigSmall")
    val bigSmall: BigSmall?,
    @SerializedName("euro")
    val euro: Euro?
)