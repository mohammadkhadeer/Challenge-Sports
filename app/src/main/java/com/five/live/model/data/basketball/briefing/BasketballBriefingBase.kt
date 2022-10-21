package com.five.live.model.data.basketball.briefing


import com.google.gson.annotations.SerializedName

data class BasketballBriefingBase(
    @SerializedName("analyseCn")
    var analyseCn: String = "",
    @SerializedName("analyseEn")
    var analyseEn: String = "",
    @SerializedName("analyseId")
    var analyseId: String = "",
    @SerializedName("analyseTh")
    var analyseTh: String = "",
    @SerializedName("analyseVi")
    var analyseVi: String = "",
    @SerializedName("awayRecentFive")
    var awayRecentFive: String = "",
    @SerializedName("awayRecentFiveSpread")
    var awayRecentFiveSpread: String = "",
    @SerializedName("awayRecentFiveTotal")
    var awayRecentFiveTotal: String = "",
    @SerializedName("confidenceCn")
    var confidenceCn: String = "",
    @SerializedName("confidenceEn")
    var confidenceEn: String = "",
    @SerializedName("confidenceId")
    var confidenceId: String = "",
    @SerializedName("confidenceTh")
    var confidenceTh: String = "",
    @SerializedName("confidenceVi")
    var confidenceVi: String = "",
    @SerializedName("explainCn")
    var explainCn: String = "",
    @SerializedName("explainEn")
    var explainEn: String = "",
    @SerializedName("explainId")
    var explainId: String = "",
    @SerializedName("explainTh")
    var explainTh: String = "",
    @SerializedName("explainVi")
    var explainVi: String = "",
    @SerializedName("headToHeadCn")
    var headToHeadCn: String = "",
    @SerializedName("headToHeadEn")
    var headToHeadEn: String = "",
    @SerializedName("headToHeadId")
    var headToHeadId: String = "",
    @SerializedName("headToHeadTh")
    var headToHeadTh: String = "",
    @SerializedName("headToHeadVi")
    var headToHeadVi: String = "",
    @SerializedName("homeRecentFive")
    var homeRecentFive: String = "",
    @SerializedName("homeRecentFiveSpread")
    var homeRecentFiveSpread: String = "",
    @SerializedName("homeRecentFiveTotal")
    var homeRecentFiveTotal: String = "",
    @SerializedName("injuryCn")
    var injuryCn: String = "",
    @SerializedName("injuryEn")
    var injuryEn: String = "",
    @SerializedName("injuryId")
    var injuryId: String = "",
    @SerializedName("injuryTh")
    var injuryTh: String = "",
    @SerializedName("injuryVi")
    var injuryVi: String = "",
    @SerializedName("matchId")
    var matchId: Int = 0
)