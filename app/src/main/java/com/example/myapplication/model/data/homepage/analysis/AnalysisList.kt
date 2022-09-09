package com.example.myapplication.model.data.homepage.analysis


import com.google.gson.annotations.SerializedName

data class AnalysisList(
    @SerializedName("awayGoals")
    var awayGoals: List<List<String>> = listOf(),
    @SerializedName("awayHT")
    var awayHT: List<List<String>> = listOf(),
    @SerializedName("awayLastMatches")
    var awayLastMatches: List<List<String>> = listOf(),
    @SerializedName("awayOdds")
    var awayOdds: List<List<String>> = listOf(),
    @SerializedName("awaySchedule")
    var awaySchedule: List<List<String>> = listOf(),
    @SerializedName("awayShootTime")
    var awayShootTime: List<List<String>> = listOf(),
    @SerializedName("headToHead")
    var headToHead: List<List<String>> = listOf(),
    @SerializedName("homeGoals")
    var homeGoals: List<List<String>> = listOf(),
    @SerializedName("homeHT")
    var homeHT: List<List<String>> = listOf(),
    @SerializedName("homeLastMatches")
    var homeLastMatches: List<List<String>> = listOf(),
    @SerializedName("homeOdds")
    var homeOdds: List<List<String>> = listOf(),
    @SerializedName("homeSchedule")
    var homeSchedule: List<List<String>> = listOf(),
    @SerializedName("homeShootTime")
    var homeShootTime: List<List<String>> = listOf()
)