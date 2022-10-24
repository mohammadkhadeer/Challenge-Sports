
package com.score.pro.model.data.standings;

  
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
 
public class TotalStanding {

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
    @SerializedName("deduction")
    @Expose
    private Double deduction;
    @SerializedName("deductionExplainCn")
    @Expose
    private String deductionExplainCn;
    @SerializedName("recentFirstResult")
    @Expose
    private String recentFirstResult;
    @SerializedName("recentSecondResult")
    @Expose
    private String recentSecondResult;
    @SerializedName("recentThirdResult")
    @Expose
    private String recentThirdResult;
    @SerializedName("recentFourthResult")
    @Expose
    private String recentFourthResult;
    @SerializedName("recentFifthResult")
    @Expose
    private String recentFifthResult;
    @SerializedName("recentSixthResult")
    @Expose
    private String recentSixthResult;
    @SerializedName("deductionExplainEn")
    @Expose
    private String deductionExplainEn;
    @SerializedName("color")
    @Expose
    private Integer color;
    @SerializedName("redCard")
    @Expose
    private Integer redCard;
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
    @SerializedName("totalAddScore")
    @Expose
    private Integer totalAddScore;

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

    public Double getDeduction() {
        return deduction;
    }

    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }

    public String getDeductionExplainCn() {
        return deductionExplainCn;
    }

    public void setDeductionExplainCn(String deductionExplainCn) {
        this.deductionExplainCn = deductionExplainCn;
    }

    public String getRecentFirstResult() {
        return recentFirstResult;
    }

    public void setRecentFirstResult(String recentFirstResult) {
        this.recentFirstResult = recentFirstResult;
    }

    public String getRecentSecondResult() {
        return recentSecondResult;
    }

    public void setRecentSecondResult(String recentSecondResult) {
        this.recentSecondResult = recentSecondResult;
    }

    public String getRecentThirdResult() {
        return recentThirdResult;
    }

    public void setRecentThirdResult(String recentThirdResult) {
        this.recentThirdResult = recentThirdResult;
    }

    public String getRecentFourthResult() {
        return recentFourthResult;
    }

    public void setRecentFourthResult(String recentFourthResult) {
        this.recentFourthResult = recentFourthResult;
    }

    public String getRecentFifthResult() {
        return recentFifthResult;
    }

    public void setRecentFifthResult(String recentFifthResult) {
        this.recentFifthResult = recentFifthResult;
    }

    public String getRecentSixthResult() {
        return recentSixthResult;
    }

    public void setRecentSixthResult(String recentSixthResult) {
        this.recentSixthResult = recentSixthResult;
    }

    public String getDeductionExplainEn() {
        return deductionExplainEn;
    }

    public void setDeductionExplainEn(String deductionExplainEn) {
        this.deductionExplainEn = deductionExplainEn;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getRedCard() {
        return redCard;
    }

    public void setRedCard(Integer redCard) {
        this.redCard = redCard;
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

    public Integer getTotalAddScore() {
        return totalAddScore;
    }

    public void setTotalAddScore(Integer totalAddScore) {
        this.totalAddScore = totalAddScore;
    }

}
