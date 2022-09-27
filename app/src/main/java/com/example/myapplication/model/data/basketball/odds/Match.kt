package com.example.myapplication.model.data.basketball.odds


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("awayRank")
    var awayRank: String = "",
    @SerializedName("awayScore")
    var awayScore: Int? = 0,
    @SerializedName("awayTeamChs")
    var awayTeamChs: String = "",
    @SerializedName("awayTeamCht")
    var awayTeamCht: String = "",
    @SerializedName("awayTeamEn")
    var awayTeamEn: String = "",
    @SerializedName("awayTeamId")
    var awayTeamId: Int = 0,
    @SerializedName("homeRank")
    var homeRank: String = "",
    @SerializedName("homeScore")
    var homeScore: Int? = 0,
    @SerializedName("homeTeamChs")
    var homeTeamChs: String = "",
    @SerializedName("homeTeamCht")
    var homeTeamCht: String = "",
    @SerializedName("homeTeamEn")
    var homeTeamEn: String = "",
    @SerializedName("homeTeamId")
    var homeTeamId: Int = 0,
    @SerializedName("isNeutral")
    var isNeutral: Boolean = false,
    @SerializedName("leagueChs")
    var leagueChs: String = "",
    @SerializedName("leagueCht")
    var leagueCht: String = "",
    @SerializedName("leagueEn")
    var leagueEn: String = "",
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("matchTime")
    var matchTime: String = "",
    @SerializedName("state")
    var state: Int = 0
)