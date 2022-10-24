package com.score.pro.model.data.basketball.league


import com.google.gson.annotations.SerializedName

data class LeagueTeam(
    @SerializedName("group")
    var group: String = "",
    @SerializedName("isPromoted")
    var isPromoted: Boolean = false,
    @SerializedName("loseCount")
    var loseCount: Int = 0,
    @SerializedName("rank")
    var rank: Int = 0,
    @SerializedName("roundChs")
    var roundChs: String = "",
    @SerializedName("roundCht")
    var roundCht: String = "",
    @SerializedName("roundEn")
    var roundEn: String = "",
    @SerializedName("streak")
    var streak: Int = 0,
    @SerializedName("teamId")
    var teamId: Int = 0,
    @SerializedName("totalLoss")
    var totalLoss: Int = 0,
    @SerializedName("totalScore")
    var totalScore: Int = 0,
    @SerializedName("winCount")
    var winCount: Int = 0
)