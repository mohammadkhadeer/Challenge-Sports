package com.challenge.sports.model.data.homepage.leagueInfo


import com.google.gson.annotations.SerializedName

data class TeamInfo(
    @SerializedName("conferenceFlg")
    var conferenceFlg: Int = 0,
    @SerializedName("flag")
    var flag: String = "",
    @SerializedName("nameChs")
    var nameChs: String = "",
    @SerializedName("nameCht")
    var nameCht: String = "",
    @SerializedName("nameCn")
    var nameCn: String = "",
    @SerializedName("nameEn")
    var nameEn: String = "",
    @SerializedName("teamId")
    var teamId: Int = 0
)