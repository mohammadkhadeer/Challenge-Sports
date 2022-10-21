package com.five.live.model.data.homepage.liveOdds


import com.google.gson.annotations.SerializedName

data class BaseLiveOdds(
    @SerializedName("list")
    var list: List<MyList> = listOf()
)