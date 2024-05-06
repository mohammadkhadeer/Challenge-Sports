
package com.challenge.sports.model.data.Matches;
//  HotMatches1.java
//
//  Generated using https://jsonmaster.github.io
//  Created on May 02, 2024
//

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HotMatches {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("season_id")
    @Expose
    private String seasonId;
    @SerializedName("competition_id")
    @Expose
    private String competitionId;
    @SerializedName("home_team_id")
    @Expose
    private String homeTeamId;
    @SerializedName("away_team_id")
    @Expose
    private String awayTeamId;
    @SerializedName("status_id")
    @Expose
    private int statusId;
    @SerializedName("match_time")
    @Expose
    private int matchTime;
    @SerializedName("venue_id")
    @Expose
    private String venueId;
    @SerializedName("referee_id")
    @Expose
    private String refereeId;
    @SerializedName("neutral")
    @Expose
    private int neutral;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("home_position")
    @Expose
    private String homePosition;
    @SerializedName("away_position")
    @Expose
    private String awayPosition;
    @SerializedName("coverage")
    @Expose
    private Coverage coverage;
    @SerializedName("round")
    @Expose
    private Round round;
    @SerializedName("updated_at")
    @Expose
    private int updatedAt;
    @SerializedName("match_timing")
    @Expose
    private String matchTiming;
    @SerializedName("update_timing")
    @Expose
    private String updateTiming;
    @SerializedName("home_Info")
    @Expose
    private HomeInfo homeInfo;
    @SerializedName("away_Info")
    @Expose
    private AwayInfo awayInfo;
    @SerializedName("league_Info")
    @Expose
    private LeagueInfo leagueInfo;
    @SerializedName("venueDetails")
    @Expose
    private VenueDetails venueDetails;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonId() {
        return this.seasonId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionId() {
        return this.competitionId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getHomeTeamId() {
        return this.homeTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getAwayTeamId() {
        return this.awayTeamId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return this.statusId;
    }

    public void setMatchTime(int matchTime) {
        this.matchTime = matchTime;
    }

    public int getMatchTime() {
        return this.matchTime;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }

    public String getRefereeId() {
        return this.refereeId;
    }

    public void setNeutral(int neutral) {
        this.neutral = neutral;
    }

    public int getNeutral() {
        return this.neutral;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return this.note;
    }

    public void setHomePosition(String homePosition) {
        this.homePosition = homePosition;
    }

    public String getHomePosition() {
        return this.homePosition;
    }

    public void setAwayPosition(String awayPosition) {
        this.awayPosition = awayPosition;
    }

    public String getAwayPosition() {
        return this.awayPosition;
    }

    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    public Coverage getCoverage() {
        return this.coverage;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Round getRound() {
        return this.round;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUpdatedAt() {
        return this.updatedAt;
    }

    public void setMatchTiming(String matchTiming) {
        this.matchTiming = matchTiming;
    }

    public String getMatchTiming() {
        return this.matchTiming;
    }

    public void setUpdateTiming(String updateTiming) {
        this.updateTiming = updateTiming;
    }

    public String getUpdateTiming() {
        return this.updateTiming;
    }

    public void setHomeInfo(HomeInfo homeInfo) {
        this.homeInfo = homeInfo;
    }

    public HomeInfo getHomeInfo() {
        return this.homeInfo;
    }

    public void setAwayInfo(AwayInfo awayInfo) {
        this.awayInfo = awayInfo;
    }

    public AwayInfo getAwayInfo() {
        return this.awayInfo;
    }


    public LeagueInfo getLeagueInfo() {
        return this.leagueInfo;
    }

    public void setVenueDetails(VenueDetails venueDetails) {
        this.venueDetails = venueDetails;
    }

    public VenueDetails getVenueDetails() {
        return this.venueDetails;
    }


}