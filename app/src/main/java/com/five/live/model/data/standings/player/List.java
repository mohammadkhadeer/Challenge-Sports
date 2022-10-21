
package com.five.live.model.data.standings.player;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("playerId")
    @Expose
    private Integer playerId;
    @SerializedName("playerNameEn")
    @Expose
    private String playerNameEn;
    @SerializedName("playerNameChs")
    @Expose
    private String playerNameChs;
    @SerializedName("playerNameCht")
    @Expose
    private String playerNameCht;
    @SerializedName("countryEn")
    @Expose
    private String countryEn;
    @SerializedName("countryCn")
    @Expose
    private String countryCn;
    @SerializedName("teamID")
    @Expose
    private Integer teamID;
    @SerializedName("teamNameEn")
    @Expose
    private String teamNameEn;
    @SerializedName("teamNameChs")
    @Expose
    private String teamNameChs;
    @SerializedName("teamNameCht")
    @Expose
    private String teamNameCht;
    @SerializedName("goals")
    @Expose
    private Integer goals;
    @SerializedName("homeGoals")
    @Expose
    private Integer homeGoals;
    @SerializedName("awayGoals")
    @Expose
    private Integer awayGoals;
    @SerializedName("homePenalty")
    @Expose
    private Integer homePenalty;
    @SerializedName("awayPenalty")
    @Expose
    private Integer awayPenalty;
    @SerializedName("matchNum")
    @Expose
    private Integer matchNum;
    @SerializedName("subNum")
    @Expose
    private Integer subNum;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerNameEn() {
        return playerNameEn;
    }

    public void setPlayerNameEn(String playerNameEn) {
        this.playerNameEn = playerNameEn;
    }

    public String getPlayerNameChs() {
        return playerNameChs;
    }

    public void setPlayerNameChs(String playerNameChs) {
        this.playerNameChs = playerNameChs;
    }

    public String getPlayerNameCht() {
        return playerNameCht;
    }

    public void setPlayerNameCht(String playerNameCht) {
        this.playerNameCht = playerNameCht;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCountryCn() {
        return countryCn;
    }

    public void setCountryCn(String countryCn) {
        this.countryCn = countryCn;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public String getTeamNameEn() {
        return teamNameEn;
    }

    public void setTeamNameEn(String teamNameEn) {
        this.teamNameEn = teamNameEn;
    }

    public String getTeamNameChs() {
        return teamNameChs;
    }

    public void setTeamNameChs(String teamNameChs) {
        this.teamNameChs = teamNameChs;
    }

    public String getTeamNameCht() {
        return teamNameCht;
    }

    public void setTeamNameCht(String teamNameCht) {
        this.teamNameCht = teamNameCht;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Integer getHomePenalty() {
        return homePenalty;
    }

    public void setHomePenalty(Integer homePenalty) {
        this.homePenalty = homePenalty;
    }

    public Integer getAwayPenalty() {
        return awayPenalty;
    }

    public void setAwayPenalty(Integer awayPenalty) {
        this.awayPenalty = awayPenalty;
    }

    public Integer getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(Integer matchNum) {
        this.matchNum = matchNum;
    }

    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

}
