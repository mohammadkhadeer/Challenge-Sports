package com.corescore.myapplication.model.data.homepage.leagueInfo.any


import com.google.gson.annotations.SerializedName

data class GroupList(
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("score")
    var score: List<Score> = listOf(),
    @SerializedName("season")
    var season: String = ""
)