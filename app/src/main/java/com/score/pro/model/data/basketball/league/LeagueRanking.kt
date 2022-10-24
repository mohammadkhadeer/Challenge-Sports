package com.score.pro.model.data.basketball.league


import com.google.gson.annotations.SerializedName

data class LeagueRanking(
    @SerializedName("list")
    var list: List<LeagueTeam> = listOf(),
    @SerializedName("season")
    var season: String = ""
)