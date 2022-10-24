
package com.score.pro.model.data.standings;

  
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 
public class LeagueInfo {

    @SerializedName("countRound")
    @Expose
    private Integer countRound;
    @SerializedName("currRound")
    @Expose
    private Integer currRound;
    @SerializedName("leagueId")
    @Expose
    private Integer leagueId;
    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("nameChs")
    @Expose
    private String nameChs;
    @SerializedName("nameCht")
    @Expose
    private String nameCht;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("nameEnShort")
    @Expose
    private String nameEnShort;
    @SerializedName("nameChsShort")
    @Expose
    private String nameChsShort;
    @SerializedName("nameChtShort")
    @Expose
    private String nameChtShort;

    public Integer getCountRound() {
        return countRound;
    }

    public void setCountRound(Integer countRound) {
        this.countRound = countRound;
    }

    public Integer getCurrRound() {
        return currRound;
    }

    public void setCurrRound(Integer currRound) {
        this.currRound = currRound;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameChs() {
        return nameChs;
    }

    public void setNameChs(String nameChs) {
        this.nameChs = nameChs;
    }

    public String getNameCht() {
        return nameCht;
    }

    public void setNameCht(String nameCht) {
        this.nameCht = nameCht;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNameEnShort() {
        return nameEnShort;
    }

    public void setNameEnShort(String nameEnShort) {
        this.nameEnShort = nameEnShort;
    }

    public String getNameChsShort() {
        return nameChsShort;
    }

    public void setNameChsShort(String nameChsShort) {
        this.nameChsShort = nameChsShort;
    }

    public String getNameChtShort() {
        return nameChtShort;
    }

    public void setNameChtShort(String nameChtShort) {
        this.nameChtShort = nameChtShort;
    }

}
