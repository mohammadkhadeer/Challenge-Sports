package com.football.live.model.data.homepage.new2


import com.google.gson.annotations.SerializedName

data class VideoDetail(
    @SerializedName("flv")
    var flv: String = "",
    @SerializedName("iframe")
    var iframe: String = "",
    @SerializedName("m3u8")
    var m3u8: String = "",
    @SerializedName("snapshot")
    var snapshot: String = "",
    @SerializedName("sort")
    var sort: Int = 0,
    @SerializedName("status")
    var status: String = "",
    @SerializedName("url")
    var url: String = ""
)