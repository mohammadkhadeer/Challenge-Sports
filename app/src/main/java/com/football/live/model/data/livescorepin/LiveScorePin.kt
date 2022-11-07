package com.football.live.model.data.livescorepin


import com.google.gson.annotations.SerializedName

data class LiveScorePin(
    @SerializedName("matchList")
    var matchList: List<Match> = listOf(),
    @SerializedName("todayHotLeague")
    var todayHotLeague: List<Any> = listOf()
)