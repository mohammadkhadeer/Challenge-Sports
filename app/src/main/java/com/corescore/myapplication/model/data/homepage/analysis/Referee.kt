package com.corescore.myapplication.model.data.homepage.analysis


import com.google.gson.annotations.SerializedName

data class Referee(
    @SerializedName("birthday")
    var birthday: String = "",
    @SerializedName("countryChs")
    var countryChs: String = "",
    @SerializedName("countryCht")
    var countryCht: String = "",
    @SerializedName("countryCn")
    var countryCn: String = "",
    @SerializedName("countryEn")
    var countryEn: String = "",
    @SerializedName("matchId")
    var matchId: Int = 0,
    @SerializedName("nameChs")
    var nameChs: String = "",
    @SerializedName("nameCht")
    var nameCht: String = "",
    @SerializedName("nameCn")
    var nameCn: String = "",
    @SerializedName("nameEn")
    var nameEn: String = "",
    @SerializedName("photo")
    var photo: String = "",
    @SerializedName("refereeId")
    var refereeId: Int = 0,
    @SerializedName("typeId")
    var typeId: Int = 0
)