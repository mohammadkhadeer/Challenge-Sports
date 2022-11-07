
package com.football.live.model.data.standings;

  
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 
public class TeamInfo {

    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("conferenceFlg")
    @Expose
    private Integer conferenceFlg;
    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("nameChs")
    @Expose
    private String nameChs;
    @SerializedName("nameCht")
    @Expose
    private String nameCht;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getConferenceFlg() {
        return conferenceFlg;
    }

    public void setConferenceFlg(Integer conferenceFlg) {
        this.conferenceFlg = conferenceFlg;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

}
