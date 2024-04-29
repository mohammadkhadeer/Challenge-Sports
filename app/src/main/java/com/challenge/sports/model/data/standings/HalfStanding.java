
package com.challenge.sports.model.data.standings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HalfStanding {

    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("winRate")
    @Expose
    private String winRate;
    @SerializedName("drawRate")
    @Expose
    private String drawRate;
    @SerializedName("loseRate")
    @Expose
    private String loseRate;
    @SerializedName("winAverage")
    @Expose
    private Double winAverage;
    @SerializedName("loseAverage")
    @Expose
    private Double loseAverage;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("winCount")
    @Expose
    private Integer winCount;
    @SerializedName("drawCount")
    @Expose
    private Integer drawCount;
    @SerializedName("loseCount")
    @Expose
    private Integer loseCount;
    @SerializedName("getScore")
    @Expose
    private Integer getScore;
    @SerializedName("loseScore")
    @Expose
    private Integer loseScore;
    @SerializedName("goalDifference")
    @Expose
    private Integer goalDifference;
    @SerializedName("integral")
    @Expose
    private Integer integral;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getWinRate() {
        return winRate;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
    }

    public String getDrawRate() {
        return drawRate;
    }

    public void setDrawRate(String drawRate) {
        this.drawRate = drawRate;
    }

    public String getLoseRate() {
        return loseRate;
    }

    public void setLoseRate(String loseRate) {
        this.loseRate = loseRate;
    }

    public Double getWinAverage() {
        return winAverage;
    }

    public void setWinAverage(Double winAverage) {
        this.winAverage = winAverage;
    }

    public Double getLoseAverage() {
        return loseAverage;
    }

    public void setLoseAverage(Double loseAverage) {
        this.loseAverage = loseAverage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }

    public Integer getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(Integer drawCount) {
        this.drawCount = drawCount;
    }

    public Integer getLoseCount() {
        return loseCount;
    }

    public void setLoseCount(Integer loseCount) {
        this.loseCount = loseCount;
    }

    public Integer getGetScore() {
        return getScore;
    }

    public void setGetScore(Integer getScore) {
        this.getScore = getScore;
    }

    public Integer getLoseScore() {
        return loseScore;
    }

    public void setLoseScore(Integer loseScore) {
        this.loseScore = loseScore;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

}
