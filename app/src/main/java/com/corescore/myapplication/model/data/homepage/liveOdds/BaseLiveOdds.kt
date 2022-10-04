package com.corescore.myapplication.model.data.homepage.liveOdds


import com.google.gson.annotations.SerializedName

data class BaseLiveOdds(
    @SerializedName("list")
    var list: List<MyList> = listOf()
)