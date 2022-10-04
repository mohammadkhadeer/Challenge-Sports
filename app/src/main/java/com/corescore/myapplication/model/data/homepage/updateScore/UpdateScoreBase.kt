package com.corescore.myapplication.model.data.homepage.updateScore


import com.google.gson.annotations.SerializedName

data class UpdateScoreBase(
    @SerializedName("changeList")
    var changeList: List<Change> = listOf(),
    @SerializedName("changeListCustom")
    var changeListCustom: List<ChangeCustom> = listOf(),
    @SerializedName("changeListCustomLatest")
    var changeListCustomLatest: List<ChangeCustomLatest> = listOf(),
    @SerializedName("changeListCustomOtherInfo")
    var changeListCustomOtherInfo: List<ChangeCustomOtherInfo> = listOf(),
    @SerializedName("eventList")
    var eventList: List<Event> = listOf(),
    @SerializedName("penalty")
    var penalty: List<Any> = listOf(),
    @SerializedName("technic")
    var technic: List<Technic> = listOf()
)