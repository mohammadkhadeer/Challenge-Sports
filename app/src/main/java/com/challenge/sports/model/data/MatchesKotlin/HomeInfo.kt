package com.challenge.sports.model.data.MatchesKotlin


import com.google.gson.annotations.SerializedName

data class HomeInfo(
    @SerializedName("cn_name")
    val cnName: String?,
    @SerializedName("corner_score")
    val cornerScore: Int?,
    @SerializedName("en_name")
    val enName: String?,
    @SerializedName("en_short_name")
    val enShortName: String?,
    @SerializedName("half_time_score")
    val halfTimeScore: Int?,
    @SerializedName("home_score")
    val homeScore: Int?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("overtime_score")
    val overtimeScore: Int?,
    @SerializedName("penalty_score")
    val penaltyScore: Int?,
    @SerializedName("red_cards")
    val redCards: Int?,
    @SerializedName("yellow_cards")
    val yellowCards: Int?
)