package com.challenge.sports.model.data.Matches;//
//  LeagueInfo.java
//
//  Generated using https://jsonmaster.github.io
//  Created on May 02, 2024
//

import java.util.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LeagueInfo {

    @SerializedName("en_name")
    @Expose
    private String enName;
    @SerializedName("cn_name")
    @Expose
    private String cnName;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("primary_color")
    @Expose
    private String primaryColor;
    @SerializedName("secondary_color")
    @Expose
    private String secondaryColor;
    @SerializedName("logo")
    @Expose
    private String logo;

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnName() {
        return this.enName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return this.logo;
    }

}