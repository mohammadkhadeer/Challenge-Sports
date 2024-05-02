package com.challenge.sports.model.data.Matches;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class MatchesRoot {
    @SerializedName("hotMatches")
    private HotMatches[] hotMatches;
    @SerializedName("hotLeagues")
    private HotLeagues[] hotLeagues;

    public void setHotMatches(HotMatches[] hotMatches) {
        this.hotMatches = hotMatches;
    }

    public HotMatches[] getHotMatches() {
        return this.hotMatches;
    }

    public void setHotLeagues(HotLeagues[] hotLeagues) {
        this.hotLeagues = hotLeagues;
    }

    public HotLeagues[] getHotLeagues() {
        return this.hotLeagues;
    }


    public static MatchesRoot create(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, MatchesRoot.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
