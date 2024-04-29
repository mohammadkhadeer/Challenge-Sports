package com.challenge.sports.model.data.homepage.analysis


import com.google.gson.annotations.SerializedName

data class AnalysisBase(
    @SerializedName("list")
    var list: List<AnalysisList> = listOf(),
    @SerializedName("referee")
    var referee: List<Referee> = listOf()
)