package com.five.live.model.data.homepage.past.future


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("awayChs")
    var awayChs: String = "",
    @SerializedName("awayCht")
    var awayCht: String = "",
    @SerializedName("awayCn")
    var awayCn: String = "",
    @SerializedName("awayCorner")
    var awayCorner: Int = 0,
    @SerializedName("awayEn")
    var awayEn: String = "",
    @SerializedName("awayHalfScore")
    var awayHalfScore: Int = 0,
    @SerializedName("awayId")
    var awayId: Int = 0,
    @SerializedName("awayLogo")
    var awayLogo: String = "",
    @SerializedName("awayNameCn")
    var awayNameCn: String = "",
    @SerializedName("awayNameEn")
    var awayNameEn: String = "",
    @SerializedName("awayNameId")
    var awayNameId: String = "",
    @SerializedName("awayNameTh")
    var awayNameTh: String = "",
    @SerializedName("awayNameVi")
    var awayNameVi: String = "",
    @SerializedName("awayRankCn")
    var awayRankCn: String = "",
    @SerializedName("awayRankEn")
    var awayRankEn: String = "",
    @SerializedName("awayRed")
    var awayRed: Int = 0,
    @SerializedName("awayScore")
    var awayScore: Int = 0,
    @SerializedName("awayYellow")
    var awayYellow: Int = 0,
    @SerializedName("color")
    var color: String = "",
    @SerializedName("explainCn")
    var explainCn: String = "",
    @SerializedName("explainEn")
    var explainEn: String = "",
    @SerializedName("extraExplain")
    var extraExplain: String = "",
    @SerializedName("extraExplainCn")
    var extraExplainCn: String = "",
    @SerializedName("groupId")
    var groupId: Int? = 0,
    @SerializedName("grouping")
    var grouping: String = "",
    @SerializedName("hasLineup")
    var hasLineup: String = "",
    @SerializedName("homeChs")
    var homeChs: String = "",
    @SerializedName("homeCht")
    var homeCht: String = "",
    @SerializedName("homeCn")
    var homeCn: String = "",
    @SerializedName("homeCorner")
    var homeCorner: Int = 0,
    @SerializedName("homeEn")
    var homeEn: String = "",
    @SerializedName("homeHalfScore")
    var homeHalfScore: Int = 0,
    @SerializedName("homeId")
    var homeId: Int = 0,
    @SerializedName("homeLogo")
    var homeLogo: String = "",
    @SerializedName("homeNameCn")
    var homeNameCn: String = "",
    @SerializedName("homeNameEn")
    var homeNameEn: String = "",
    @SerializedName("homeNameId")
    var homeNameId: String = "",
    @SerializedName("homeNameTh")
    var homeNameTh: String = "",
    @SerializedName("homeNameVi")
    var homeNameVi: String = "",
    @SerializedName("homeRankCn")
    var homeRankCn: String = "",
    @SerializedName("homeRankEn")
    var homeRankEn: String = "",
    @SerializedName("homeRed")
    var homeRed: Int = 0,
    @SerializedName("homeScore")
    var homeScore: Int = 0,
    @SerializedName("homeYellow")
    var homeYellow: Int = 0,
    @SerializedName("injuryTime")
    var injuryTime: Int = 0,
    @SerializedName("isHidden")
    var isHidden: Boolean = false,
    @SerializedName("isNeutral")
    var isNeutral: Boolean = false,
    @SerializedName("kind")
    var kind: Int = 0,
    @SerializedName("leagueChsShort")
    var leagueChsShort: String = "",
    @SerializedName("leagueChtShort")
    var leagueChtShort: String = "",
    @SerializedName("leagueCn")
    var leagueCn: String = "",
    @SerializedName("leagueCnShort")
    var leagueCnShort: String = "",
    @SerializedName("leagueEn")
    var leagueEn: String = "",
    @SerializedName("leagueEnShort")
    var leagueEnShort: String = "",
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("leagueNameCn")
    var leagueNameCn: String = "",
    @SerializedName("leagueNameEn")
    var leagueNameEn: String = "",
    @SerializedName("leagueNameId")
    var leagueNameId: String = "",
    @SerializedName("leagueNameShortCn")
    var leagueNameShortCn: String = "",
    @SerializedName("leagueNameShortEn")
    var leagueNameShortEn: String = "",
    @SerializedName("leagueNameShortId")
    var leagueNameShortId: String = "",
    @SerializedName("leagueNameShortTh")
    var leagueNameShortTh: String = "",
    @SerializedName("leagueNameShortVi")
    var leagueNameShortVi: String = "",
    @SerializedName("leagueNameTh")
    var leagueNameTh: String = "",
    @SerializedName("leagueNameVi")
    var leagueNameVi: String = "",
    @SerializedName("locationCn")
    var locationCn: String = "",
    @SerializedName("locationEn")
    var locationEn: String = "",
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("matchTime")
    var matchTime: String = "",
    @SerializedName("roundCn")
    var roundCn: String = "",
    @SerializedName("roundEn")
    var roundEn: String = "",
    @SerializedName("season")
    var season: String = "",
    @SerializedName("startTime")
    var startTime: String = "",
    @SerializedName("state")
    var state: Int = 0,
    @SerializedName("subLeagueChs")
    var subLeagueChs: String = "",
    @SerializedName("subLeagueCht")
    var subLeagueCht: String = "",
    @SerializedName("subLeagueCn")
    var subLeagueCn: String = "",
    @SerializedName("subLeagueEn")
    var subLeagueEn: String = "",
    @SerializedName("subLeagueId")
    var subLeagueId: String = "",
    @SerializedName("temp")
    var temp: String = "",
    @SerializedName("updateTime")
    var updateTime: String = "",
    @SerializedName("weatherCn")
    var weatherCn: String = "",
    @SerializedName("weatherEn")
    var weatherEn: String = ""
)