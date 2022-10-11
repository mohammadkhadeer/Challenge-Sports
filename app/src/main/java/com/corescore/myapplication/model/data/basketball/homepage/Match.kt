package com.corescore.myapplication.model.data.basketball.homepage


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("animateURLCn")
    var animateURLCn: String? = "",
    @SerializedName("animateURLEn")
    var animateURLEn: String? = "",
    @SerializedName("away1")
    var away1: String = "",
    @SerializedName("away2")
    var away2: String = "",
    @SerializedName("away3")
    var away3: String = "",
    @SerializedName("away4")
    var away4: String = "",
    @SerializedName("awayLogo")
    var awayLogo: String = "",
    @SerializedName("awayOT1")
    var awayOT1: String = "",
    @SerializedName("awayOT2")
    var awayOT2: String = "",
    @SerializedName("awayOT3")
    var awayOT3: String = "",
    @SerializedName("awayRankCn")
    var awayRankCn: String = "",
    @SerializedName("awayRankEn")
    var awayRankEn: String = "",
    @SerializedName("awayScore")
    var awayScore: String = "",
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
    @SerializedName("cupQualifyId")
    var cupQualifyId: Int? = 0,
    @SerializedName("explainCn")
    var explainCn: String = "",
    @SerializedName("explainEn")
    var explainEn: String = "",
    @SerializedName("group")
    var group: String = "",
    @SerializedName("hasStats")
    var hasStats: Boolean = false,
    @SerializedName("havAnim")
    var havAnim: Boolean = false,
    @SerializedName("havAnimCompareAway")
    var havAnimCompareAway: String? = "",
    @SerializedName("havAnimCompareHome")
    var havAnimCompareHome: String? = "",
    @SerializedName("havAnimComparePercent")
    var havAnimComparePercent: Int? = 0,
    @SerializedName("havAnimate")
    var havAnimate: Boolean = false,
    @SerializedName("havBriefing")
    var havBriefing: Boolean = false,
    @SerializedName("havLineup")
    var havLineup: Boolean = false,
    @SerializedName("havLiveAnchor")
    var havLiveAnchor: Boolean = false,
    @SerializedName("havLiveAnchorId")
    var havLiveAnchorId: String = "",
    @SerializedName("havLiveAnchorLocale")
    var havLiveAnchorLocale: String = "",
    @SerializedName("havLiveText")
    var havLiveText: Boolean = false,
    @SerializedName("havLiveTextEn")
    var havLiveTextEn: Boolean = false,
    @SerializedName("havLiveVideo")
    var havLiveVideo: Boolean = false,
    @SerializedName("home1")
    var home1: String = "",
    @SerializedName("home2")
    var home2: String = "",
    @SerializedName("home3")
    var home3: String = "",
    @SerializedName("home4")
    var home4: String = "",
    @SerializedName("homeLogo")
    var homeLogo: String = "",
    @SerializedName("homeOT1")
    var homeOT1: String = "",
    @SerializedName("homeOT2")
    var homeOT2: String = "",
    @SerializedName("homeOT3")
    var homeOT3: String = "",
    @SerializedName("homeRankCn")
    var homeRankCn: String = "",
    @SerializedName("homeRankEn")
    var homeRankEn: String = "",
    @SerializedName("homeScore")
    var homeScore: String = "",
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
    @SerializedName("isNeutral")
    var isNeutral: Boolean = false,
    @SerializedName("leagueChs")
    var leagueChs: String = "",
    @SerializedName("leagueCht")
    var leagueCht: String = "",
    @SerializedName("leagueCn")
    var leagueCn: String = "",
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
    @SerializedName("leagueType")
    var leagueType: Int = 0,
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("matchKind")
    var matchKind: String = "",
    @SerializedName("matchState")
    var matchState: Int = 0,
    @SerializedName("matchTime")
    var matchTime: String = "",
    @SerializedName("odds")
    var odds: Odds = Odds(),
    @SerializedName("overtimeCount")
    var overtimeCount: Int = 0,
    @SerializedName("playoffsId")
    var playoffsId: Int? = 0,
    @SerializedName("remainTime")
    var remainTime: String = "",
    @SerializedName("roundTypeChs")
    var roundTypeChs: String = "",
    @SerializedName("roundTypeEn")
    var roundTypeEn: String = "",
    @SerializedName("season")
    var season: String = "",
    @SerializedName("tv")
    var tv: String = "",
    @SerializedName("updateTime")
    var updateTime: String = "",
    @SerializedName("videoDetail")
    var videoDetail: VideoDetail? = VideoDetail(),
    @SerializedName("videoId")
    var videoId: String? = "0"
)