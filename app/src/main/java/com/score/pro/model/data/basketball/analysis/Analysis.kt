package com.score.pro.model.data.basketball.analysis


import com.google.gson.annotations.SerializedName

data class Analysis (
    @SerializedName("awayLastMatches")
    var awayLastMatches: List<AwayLastMatche> = listOf(),
    @SerializedName("awaySchedule")
    var awaySchedule: List<AwaySchedule> = listOf(),
    @SerializedName("headToHead")
    var headToHead: List<HeadToHead> = listOf(),
    @SerializedName("homeLastMatches")
    var homeLastMatches: List<HomeLastMatche> = listOf(),
    @SerializedName("homeSchedule")
    var homeSchedule: List<HomeSchedule> = listOf()
)