package com.football.live.model.data.homepage.leagueInfo.any


import com.google.gson.annotations.SerializedName

data class GroupScore(
    @SerializedName("groupCn")
    var groupCn: String = "",
    @SerializedName("groupEn")
    var groupEn: String = "",
    @SerializedName("scoreItems")
    var scoreItems: List<ScoreItem> = listOf()
)