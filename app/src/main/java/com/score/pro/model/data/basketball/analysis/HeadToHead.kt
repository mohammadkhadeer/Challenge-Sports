package com.score.pro.model.data.basketball.analysis


import com.google.gson.annotations.SerializedName

data class HeadToHead(
    @SerializedName("awayHalfScore")
    var awayHalfScore: Int = 0,
    @SerializedName("awayScore")
    var awayScore: Int = 0,
    @SerializedName("awayTeamChs")
    var awayTeamChs: String = "",
    @SerializedName("awayTeamCht")
    var awayTeamCht: String = "",
    @SerializedName("awayTeamEn")
    var awayTeamEn: String = "",
    @SerializedName("awayTeamId")
    var awayTeamId: Int = 0,
    @SerializedName("awayTeamNameCn")
    var awayTeamNameCn: String = "",
    @SerializedName("awayTeamNameEn")
    var awayTeamNameEn: String = "",
    @SerializedName("awayTeamNameId")
    var awayTeamNameId: String = "",
    @SerializedName("awayTeamNameTh")
    var awayTeamNameTh: String = "",
    @SerializedName("awayTeamNameVi")
    var awayTeamNameVi: String = "",
    @SerializedName("color")
    var color: String = "",
    @SerializedName("homeHalfScore")
    var homeHalfScore: Int = 0,
    @SerializedName("homeScore")
    var homeScore: Int = 0,
    @SerializedName("homeTeamChs")
    var homeTeamChs: String = "",
    @SerializedName("homeTeamCht")
    var homeTeamCht: String = "",
    @SerializedName("homeTeamEn")
    var homeTeamEn: String = "",
    @SerializedName("homeTeamId")
    var homeTeamId: Int = 0,
    @SerializedName("homeTeamNameCn")
    var homeTeamNameCn: String = "",
    @SerializedName("homeTeamNameEn")
    var homeTeamNameEn: String = "",
    @SerializedName("homeTeamNameId")
    var homeTeamNameId: String = "",
    @SerializedName("homeTeamNameTh")
    var homeTeamNameTh: String = "",
    @SerializedName("homeTeamNameVi")
    var homeTeamNameVi: String = "",
    @SerializedName("leagueChs")
    var leagueChs: String = "",
    @SerializedName("leagueCht")
    var leagueCht: String = "",
    @SerializedName("leagueEn")
    var leagueEn: String = "",
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("leagueNameCn")
    var leagueNameCn: String = "",
    @SerializedName("leagueNameEn")
    var leagueNameEn: String = "",
    @SerializedName("leagueNameId")
    var leagueNameId: String = "",
    @SerializedName("leagueNameTh")
    var leagueNameTh: String = "",
    @SerializedName("leagueNameVi")
    var leagueNameVi: String = "",
    @SerializedName("margin")
    var margin: Int = 0,
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("matchKindCn")
    var matchKindCn: String = "",
    @SerializedName("matchKindEn")
    var matchKindEn: String = "",
    @SerializedName("matchTime")
    var matchTime: String = "",
    @SerializedName("moneyLine")
    var moneyLine: Int = 0,
    @SerializedName("spreadOdds")
    var spreadOdds: String = "",
    @SerializedName("spreadResult")
    var spreadResult: String = "",
    @SerializedName("total")
    var total: Int = 0,
    @SerializedName("totalOdds")
    var totalOdds: String = "",
    @SerializedName("totalResult")
    var totalResult: String = ""
)