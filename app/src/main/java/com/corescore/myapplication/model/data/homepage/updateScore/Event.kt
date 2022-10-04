package com.corescore.myapplication.model.data.homepage.updateScore


import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("event")
    var event: List<EventX> = listOf(),
    @SerializedName("matchId")
    var matchId: Int = 0
)