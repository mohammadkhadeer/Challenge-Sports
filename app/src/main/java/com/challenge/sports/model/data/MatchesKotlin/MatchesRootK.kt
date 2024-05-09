package com.challenge.sports.model.data.MatchesKotlin


import com.google.gson.annotations.SerializedName

data class MatchesRootK(
    @SerializedName("hotLeagues")
    val hotLeagues: List<HotLeague?>?,
    @SerializedName("hotMatches")
    val hotMatches: List<HotMatche?>?
)