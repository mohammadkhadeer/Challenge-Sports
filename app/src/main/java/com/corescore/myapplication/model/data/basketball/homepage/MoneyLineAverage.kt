package com.corescore.myapplication.model.data.basketball.homepage


import com.google.gson.annotations.SerializedName

data class MoneyLineAverage(
    @SerializedName("awayTeamChs")
    var awayTeamChs: String = "",
    @SerializedName("awayTeamCht")
    var awayTeamCht: String = "",
    @SerializedName("awayTeamEn")
    var awayTeamEn: String = "",
    @SerializedName("changeTime")
    var changeTime: String = "",
    @SerializedName("companyId")
    var companyId: String = "",
    @SerializedName("homeTeamChs")
    var homeTeamChs: String = "",
    @SerializedName("homeTeamCht")
    var homeTeamCht: String = "",
    @SerializedName("homeTeamEn")
    var homeTeamEn: String = "",
    @SerializedName("initAwayWinRate")
    var initAwayWinRate: Double = 0.0,
    @SerializedName("initHomeWinRate")
    var initHomeWinRate: Double = 0.0,
    @SerializedName("liveAwayWinRate")
    var liveAwayWinRate: Double = 0.0,
    @SerializedName("liveHomeWinRate")
    var liveHomeWinRate: Double = 0.0,
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("totalCompany")
    var totalCompany: Int = 0
)