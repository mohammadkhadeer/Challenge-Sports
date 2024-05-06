package com.challenge.sports.model.data.Matches;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchesRoot {

    @SerializedName("hotMatches")
    @Expose
    private HotMatches[] hotMatches;
    @SerializedName("hotLeagues")
    @Expose
    private HotLeagues[] hotLeagues;

    public HotMatches[] getHotMatches() {
        return this.hotMatches;
    }

    public HotLeagues[] getHotLeagues() {
        return this.hotLeagues;
    }

}
