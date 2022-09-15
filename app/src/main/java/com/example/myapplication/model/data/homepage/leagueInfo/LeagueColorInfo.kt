package com.example.myapplication.model.data.homepage.leagueInfo


import com.google.gson.annotations.SerializedName

data class LeagueColorInfo(
    @SerializedName("beginRank")
    var beginRank: Int = 0,
    @SerializedName("color")
    var color: String = "",
    @SerializedName("endRank")
    var endRank: Int = 0,
    @SerializedName("leagueNameChs")
    var leagueNameChs: String = "",
    @SerializedName("leagueNameCht")
    var leagueNameCht: String = "",
    @SerializedName("leagueNameEn")
    var leagueNameEn: String = ""
)