package com.football.live.model.data.homepage.event


import com.google.gson.annotations.SerializedName

data class Technic(
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("technicCount")
    var technicCount: String = ""
)