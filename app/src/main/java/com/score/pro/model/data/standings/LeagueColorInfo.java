
package com.score.pro.model.data.standings;

  
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 
public class LeagueColorInfo {

    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("leagueNameEn")
    @Expose
    private String leagueNameEn;
    @SerializedName("leagueNameChs")
    @Expose
    private String leagueNameChs;
    @SerializedName("leagueNameCht")
    @Expose
    private String leagueNameCht;
    @SerializedName("beginRank")
    @Expose
    private Integer beginRank;
    @SerializedName("endRank")
    @Expose
    private Integer endRank;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLeagueNameEn() {
        return leagueNameEn;
    }

    public void setLeagueNameEn(String leagueNameEn) {
        this.leagueNameEn = leagueNameEn;
    }

    public String getLeagueNameChs() {
        return leagueNameChs;
    }

    public void setLeagueNameChs(String leagueNameChs) {
        this.leagueNameChs = leagueNameChs;
    }

    public String getLeagueNameCht() {
        return leagueNameCht;
    }

    public void setLeagueNameCht(String leagueNameCht) {
        this.leagueNameCht = leagueNameCht;
    }

    public Integer getBeginRank() {
        return beginRank;
    }

    public void setBeginRank(Integer beginRank) {
        this.beginRank = beginRank;
    }

    public Integer getEndRank() {
        return endRank;
    }

    public void setEndRank(Integer endRank) {
        this.endRank = endRank;
    }

}
