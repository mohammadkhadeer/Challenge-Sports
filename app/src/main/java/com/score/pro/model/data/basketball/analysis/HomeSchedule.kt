package com.score.pro.model.data.basketball.analysis


import com.google.gson.annotations.SerializedName

data class HomeSchedule(
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
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("matchTime")
    var matchTime: String = "",
    @SerializedName("score")
    var score: String = "",
    @SerializedName("span")
    var span: Int = 0
)