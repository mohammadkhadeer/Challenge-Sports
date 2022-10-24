package com.score.pro.model.data.basketball.league


import com.google.gson.annotations.SerializedName

data class LeagueData(
    @SerializedName("color")
    var color: String = "",
    @SerializedName("countryCn")
    var countryCn: String = "",
    @SerializedName("countryEn")
    var countryEn: String = "",
    @SerializedName("countryId")
    var countryId: String = "",
    @SerializedName("countryNameCn")
    var countryNameCn: String = "",
    @SerializedName("countryNameEn")
    var countryNameEn: String = "",
    @SerializedName("countryNameId")
    var countryNameId: String = "",
    @SerializedName("countryNameVi")
    var countryNameVi: String = "",
    @SerializedName("currentSeason")
    var currentSeason: String = "",
    @SerializedName("leagueId")
    var leagueId: Int = 0,
    @SerializedName("leagueKind")
    var leagueKind: String = "",
    @SerializedName("leagueType")
    var leagueType: String = "",
    @SerializedName("logo")
    var logo: String = "",
    @SerializedName("nameChs")
    var nameChs: String = "",
    @SerializedName("nameChsShort")
    var nameChsShort: String = "",
    @SerializedName("nameCht")
    var nameCht: String = "",
    @SerializedName("nameChtShort")
    var nameChtShort: String = "",
    @SerializedName("nameCn")
    var nameCn: String = "",
    @SerializedName("nameCnShort")
    var nameCnShort: String = "",
    @SerializedName("nameEn")
    var nameEn: String = "",
    @SerializedName("nameEnShort")
    var nameEnShort: String = "",
    @SerializedName("nameId")
    var nameId: String = "",
    @SerializedName("nameIdShort")
    var nameIdShort: String = "",
    @SerializedName("nameVi")
    var nameVi: String = "",
    @SerializedName("nameViShort")
    var nameViShort: String = "",
    @SerializedName("partTime")
    var partTime: String = "",
    @SerializedName("ruleCn")
    var ruleCn: String = "",
    @SerializedName("ruleEn")
    var ruleEn: String = "",
    @SerializedName("ruleId")
    var ruleId: String = "",
    @SerializedName("ruleVi")
    var ruleVi: String = ""
)