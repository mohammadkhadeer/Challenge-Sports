package com.score.pro.model.data.homepage.liveOdds


import com.google.gson.annotations.SerializedName

data class BaseLiveOdds(
    @SerializedName("list")
    var list: List<MyList> = listOf()
)