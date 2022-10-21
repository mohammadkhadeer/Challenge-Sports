package com.five.live.model.data.homepage.briefing


import com.google.gson.annotations.SerializedName

data class BriefingBase(
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("playerSuspend")
    var playerSuspend: List<Any> = listOf(),
    @SerializedName("recommendCn")
    var recommendCn: String = "",
    @SerializedName("recommendEn")
    var recommendEn: String = "",
    @SerializedName("recommendId")
    var recommendId: String = "",
    @SerializedName("recommendTh")
    var recommendTh: String = "",
    @SerializedName("recommendVi")
    var recommendVi: String = ""
)