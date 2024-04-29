package com.challenge.sports.model.data.homepage.updateScore


import com.google.gson.annotations.SerializedName

data class Technic(
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("technicCount")
    var technicCount: String = ""
)