package com.corescore.myapplication.model.data.generalized

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class OtpResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("uniqueId")
    @Expose
    var uniqueId: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("token")
    @Expose
    var token: String? = null
}