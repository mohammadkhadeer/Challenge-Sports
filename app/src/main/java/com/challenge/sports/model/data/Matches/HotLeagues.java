package com.challenge.sports.model.data.Matches;

//
//  HotLeagues.java
//
//  Generated using https://jsonmaster.github.io
//  Created on May 02, 2024
//

import java.util.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HotLeagues {

    @SerializedName("id")
    private String id;
    @SerializedName("nameEn")
    private String nameEn;
    @SerializedName("nameEnShort")
    private String nameEnShort;
    @SerializedName("nameCn")
    private String nameCn;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public void setNameEnShort(String nameEnShort) {
        this.nameEnShort = nameEnShort;
    }

    public String getNameEnShort() {
        return this.nameEnShort;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameCn() {
        return this.nameCn;
    }


    public static HotLeagues create(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, HotLeagues.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}