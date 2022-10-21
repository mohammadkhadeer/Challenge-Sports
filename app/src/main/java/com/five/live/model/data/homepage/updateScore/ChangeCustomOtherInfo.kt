package com.five.live.model.data.homepage.updateScore


import com.google.gson.annotations.SerializedName

data class ChangeCustomOtherInfo(
    @SerializedName("animateURLCn")
    var animateURLCn: String? = "",
    @SerializedName("animateURLEn")
    var animateURLEn: String? = "",
    @SerializedName("havAnim")
    var havAnim: Boolean = false,
    @SerializedName("havLiveAnchor")
    var havLiveAnchor: Boolean = false,
    @SerializedName("havLiveAnchorId")
    var havLiveAnchorId: Int = 0,
    @SerializedName("havLiveAnchorLocale")
    var havLiveAnchorLocale: String? = "",
    @SerializedName("havLiveVideo")
    var havLiveVideo: Boolean = false,
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("videoId")
    var videoId: Int? = 0
)