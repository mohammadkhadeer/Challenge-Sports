package com.challenge.sports.model.data.homepage.leagueInfo.any


import com.google.gson.annotations.SerializedName

data class LeagueStandingTypeGroupBase(
    @SerializedName("list")
    var list: List<GroupList> = listOf()
)