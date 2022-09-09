package com.example.myapplication.model.data.homepage.new2


import com.google.gson.annotations.SerializedName

data class BaseClassIndexNew(
    @SerializedName("matchList")
    var matchList: List<Match> = listOf(),
    @SerializedName("meta")
    var meta: Meta = Meta(),
    @SerializedName("todayHotLeague")
    var todayHotLeague: List<TodayHotLeague> = listOf(),
    @SerializedName("todayHotLeagueList")
    var todayHotLeagueList: List<TodayHotLeagueX> = listOf()
)