
package com.football.live.model.data.generalized;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralTokenResponse {

    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}

