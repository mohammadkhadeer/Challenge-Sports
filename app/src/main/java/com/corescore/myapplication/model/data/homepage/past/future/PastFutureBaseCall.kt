package com.corescore.myapplication.model.data.homepage.past.future


import com.google.gson.annotations.SerializedName

data class PastFutureBaseCall(
    @SerializedName("matchList")
    var matchList: List<Match> = listOf()
)