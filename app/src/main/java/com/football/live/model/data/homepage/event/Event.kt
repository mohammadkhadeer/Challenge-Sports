package com.football.live.model.data.homepage.event


import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("event")
    var event: List<EventX> = listOf(),
    @SerializedName("matchId")
    var matchId: Int = 0
)