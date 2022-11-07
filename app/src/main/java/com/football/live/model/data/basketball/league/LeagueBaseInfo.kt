package com.football.live.model.data.basketball.league


import com.google.gson.annotations.SerializedName

data class LeagueBaseInfo(
    @SerializedName("leagueData")
    var leagueData: List<LeagueData> = listOf(),
    @SerializedName("leagueRanking")
    var leagueRanking:Any
)