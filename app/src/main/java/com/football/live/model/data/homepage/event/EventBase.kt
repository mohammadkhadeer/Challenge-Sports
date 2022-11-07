package com.football.live.model.data.homepage.event


import com.google.gson.annotations.SerializedName

data class EventBase(
    @SerializedName("eventList")
    var eventList: List<Event> = listOf(),
    @SerializedName("penalty")
    var penalty: List<Any> = listOf(),
    @SerializedName("technic")
    var technic: List<Technic> = listOf()
)