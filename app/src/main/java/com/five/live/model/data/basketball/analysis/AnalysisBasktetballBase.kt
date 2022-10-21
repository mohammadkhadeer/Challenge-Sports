package com.five.live.model.data.basketball.analysis


import com.google.gson.annotations.SerializedName

data class AnalysisBasktetballBase(
    @SerializedName("list")
    var list: List<Analysis> = listOf()
)