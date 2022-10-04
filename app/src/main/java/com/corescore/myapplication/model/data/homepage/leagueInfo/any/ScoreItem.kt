package com.corescore.myapplication.model.data.homepage.leagueInfo.any


import com.google.gson.annotations.SerializedName

data class ScoreItem(
    @SerializedName("color")
    var color: String = "",
    @SerializedName("drawCount")
    var drawCount: String = "",
    @SerializedName("getScore")
    var getScore: String = "",
    @SerializedName("goalDifference")
    var goalDifference: String = "",
    @SerializedName("integral")
    var integral: String = "",
    @SerializedName("loseCount")
    var loseCount: String = "",
    @SerializedName("loseScore")
    var loseScore: String = "",
    @SerializedName("rank")
    var rank: String = "",
    @SerializedName("teamId")
    var teamId: String = "",
    @SerializedName("teamNameChs")
    var teamNameChs: String = "",
    @SerializedName("teamNameCht")
    var teamNameCht: String = "",
    @SerializedName("teamNameEn")
    var teamNameEn: String = "",
    @SerializedName("totalCount")
    var totalCount: String = "",
    @SerializedName("winCount")
    var winCount: String = ""
)