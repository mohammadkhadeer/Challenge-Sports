package com.challenge.sports.model.data.Matches;//
//  AwayInfo.java
//
//  Generated using https://jsonmaster.github.io
//  Created on May 02, 2024
//

import java.util.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AwayInfo {

    @SerializedName("en_name")
    @Expose
    private String enName;
    @SerializedName("cn_name")
    @Expose
    private String cnName;
    @SerializedName("en_short_name")
    @Expose
    private String enShortName;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("away_score")
    @Expose
    private int awayScore;
    @SerializedName("half_time_score")
    @Expose
    private int halfTimeScore;
    @SerializedName("red_cards")
    @Expose
    private int redCards;
    @SerializedName("yellow_cards")
    @Expose
    private int yellowCards;
    @SerializedName("corner_score")
    @Expose
    private int cornerScore;
    @SerializedName("overtime_score")
    @Expose
    private int overtimeScore;
    @SerializedName("penalty_score")
    @Expose
    private int penaltyScore;

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

    public void setEnShortName(String enShortName) {
        this.enShortName = enShortName;
    }

    public String getEnShortName() {
        return this.enShortName;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getAwayScore() {
        return this.awayScore;
    }

    public void setHalfTimeScore(int halfTimeScore) {
        this.halfTimeScore = halfTimeScore;
    }

    public int getHalfTimeScore() {
        return this.halfTimeScore;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public int getRedCards() {
        return this.redCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getYellowCards() {
        return this.yellowCards;
    }

    public void setCornerScore(int cornerScore) {
        this.cornerScore = cornerScore;
    }

    public int getCornerScore() {
        return this.cornerScore;
    }

    public void setOvertimeScore(int overtimeScore) {
        this.overtimeScore = overtimeScore;
    }

    public int getOvertimeScore() {
        return this.overtimeScore;
    }

    public void setPenaltyScore(int penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    public int getPenaltyScore() {
        return this.penaltyScore;
    }


}