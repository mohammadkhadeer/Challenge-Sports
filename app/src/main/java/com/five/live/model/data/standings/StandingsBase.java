
package com.five.live.model.data.standings;

import java.util.List;
  

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 
public class StandingsBase {

    @SerializedName("leagueInfo")
    @Expose
    private LeagueInfo leagueInfo;
    @SerializedName("teamInfo")
    @Expose
    private List<TeamInfo> teamInfo = null;
    @SerializedName("totalStandings")
    @Expose
    private List<TotalStanding> totalStandings = null;
    @SerializedName("halfStandings")
    @Expose
    private List<HalfStanding> halfStandings = null;
    @SerializedName("homeStandings")
    @Expose
    private List<HomeStanding> homeStandings = null;
    @SerializedName("awayStandings")
    @Expose
    private List<AwayStanding> awayStandings = null;
    @SerializedName("homeHalfStandings")
    @Expose
    private List<HomeHalfStanding> homeHalfStandings = null;
    @SerializedName("awayHalfStandings")
    @Expose
    private List<AwayHalfStanding> awayHalfStandings = null;
    @SerializedName("leagueColorInfos")
    @Expose
    private List<LeagueColorInfo> leagueColorInfos = null;
    @SerializedName("isConference")
    @Expose
    private Boolean isConference;
    @SerializedName("lastUpdateTime")
    @Expose
    private String lastUpdateTime;
    @SerializedName("scoreCountType")
    @Expose
    private Integer scoreCountType;

    public LeagueInfo getLeagueInfo() {
        return leagueInfo;
    }

    public void setLeagueInfo(LeagueInfo leagueInfo) {
        this.leagueInfo = leagueInfo;
    }

    public List<TeamInfo> getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(List<TeamInfo> teamInfo) {
        this.teamInfo = teamInfo;
    }

    public List<TotalStanding> getTotalStandings() {
        return totalStandings;
    }

    public void setTotalStandings(List<TotalStanding> totalStandings) {
        this.totalStandings = totalStandings;
    }

    public List<HalfStanding> getHalfStandings() {
        return halfStandings;
    }

    public void setHalfStandings(List<HalfStanding> halfStandings) {
        this.halfStandings = halfStandings;
    }

    public List<HomeStanding> getHomeStandings() {
        return homeStandings;
    }

    public void setHomeStandings(List<HomeStanding> homeStandings) {
        this.homeStandings = homeStandings;
    }

    public List<AwayStanding> getAwayStandings() {
        return awayStandings;
    }

    public void setAwayStandings(List<AwayStanding> awayStandings) {
        this.awayStandings = awayStandings;
    }

    public List<HomeHalfStanding> getHomeHalfStandings() {
        return homeHalfStandings;
    }

    public void setHomeHalfStandings(List<HomeHalfStanding> homeHalfStandings) {
        this.homeHalfStandings = homeHalfStandings;
    }

    public List<AwayHalfStanding> getAwayHalfStandings() {
        return awayHalfStandings;
    }

    public void setAwayHalfStandings(List<AwayHalfStanding> awayHalfStandings) {
        this.awayHalfStandings = awayHalfStandings;
    }

    public List<LeagueColorInfo> getLeagueColorInfos() {
        return leagueColorInfos;
    }

    public void setLeagueColorInfos(List<LeagueColorInfo> leagueColorInfos) {
        this.leagueColorInfos = leagueColorInfos;
    }

    public Boolean getIsConference() {
        return isConference;
    }

    public void setIsConference(Boolean isConference) {
        this.isConference = isConference;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getScoreCountType() {
        return scoreCountType;
    }

    public void setScoreCountType(Integer scoreCountType) {
        this.scoreCountType = scoreCountType;
    }

}
