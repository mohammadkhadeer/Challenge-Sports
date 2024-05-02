package com.challenge.sports.model.data.Matches;//
//  Round.java
//
//  Generated using https://jsonmaster.github.io
//  Created on May 02, 2024
//

import java.util.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Round {

    @SerializedName("stage_id")
    private String stageId;
    @SerializedName("round_num")
    private int roundNum;
    @SerializedName("group_num")
    private int groupNum;

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageId() {
        return this.stageId;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    public int getRoundNum() {
        return this.roundNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public int getGroupNum() {
        return this.groupNum;
    }


    public static Round create(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Round.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}