package com.football.live.model.data.homepage.leagueInfo.any


import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("groupNameChs")
    var groupNameChs: String = "",
    @SerializedName("groupNameCht")
    var groupNameCht: String = "",
    @SerializedName("groupNameEn")
    var groupNameEn: String = "",
    @SerializedName("groupScore")
    var groupScore: List<GroupScore> = listOf()
)