package com.five.live.model.data.basketball.homepage


import com.google.gson.annotations.SerializedName

data class Odds(
    @SerializedName("moneyLine")
    var moneyLine: List<Double>? = listOf(),
    @SerializedName("moneyLineAverage")
    var moneyLineAverage: MoneyLineAverage? = MoneyLineAverage(),
    @SerializedName("spread")
    var spread: List<Double>? = listOf(),
    @SerializedName("spreadHalf")
    var spreadHalf: List<Double>? = listOf(),
    @SerializedName("spreadPart")
    var spreadPart: List<Double>? = listOf(),
    @SerializedName("total")
    var total: List<Double>? = listOf(),
    @SerializedName("totalHalf")
    var totalHalf: List<Double>? = listOf(),
    @SerializedName("totalPart")
    var totalPart: List<Double>? = listOf()
)