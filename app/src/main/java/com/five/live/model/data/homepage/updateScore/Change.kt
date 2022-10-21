package com.five.live.model.data.homepage.updateScore


import com.google.gson.annotations.SerializedName

data class Change(
    @SerializedName("awayCorner")
    var awayCorner: Int = 0,
    @SerializedName("awayHalfScore")
    var awayHalfScore: Int = 0,
    @SerializedName("awayRed")
    var awayRed: Int = 0,
    @SerializedName("awayScore")
    var awayScore: Int = 0,
    @SerializedName("awayYellow")
    var awayYellow: Int = 0,
    @SerializedName("explain")
    var explain: String = "",
    @SerializedName("extraExplain")
    var extraExplain: String = "",
    @SerializedName("hasLineup")
    var hasLineup: String = "",
    @SerializedName("homeCorner")
    var homeCorner: Int = 0,
    @SerializedName("homeHalfScore")
    var homeHalfScore: Int = 0,
    @SerializedName("homeRed")
    var homeRed: Int = 0,
    @SerializedName("homeScore")
    var homeScore: Int = 0,
    @SerializedName("homeYellow")
    var homeYellow: Int = 0,
    @SerializedName("injuryTime")
    var injuryTime: String = "",
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("matchTime")
    var matchTime: String = "",
    @SerializedName("startTime")
    var startTime: String = "",
    @SerializedName("state")
    var state: Int = 0
)