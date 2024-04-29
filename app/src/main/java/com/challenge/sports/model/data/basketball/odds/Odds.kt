package com.challenge.sports.model.data.basketball.odds


import com.google.gson.annotations.SerializedName

data class Odds(
    @SerializedName("match")
    var match: List<Match> = listOf(),
    @SerializedName("moneyLine")
    var moneyLine: List<List<Any>> = listOf(),
    @SerializedName("spread")
    var spread: List<List<Any>> = listOf(),
    @SerializedName("total")
    var total: List<List<Any>> = listOf()
)