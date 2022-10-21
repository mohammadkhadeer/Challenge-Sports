package com.five.live.model.api

import com.five.live.model.data.basketball.analysis.AnalysisBasktetballBase
import com.five.live.model.data.basketball.briefing.BasketballBriefingBase
import com.five.live.model.data.basketball.homepage.BaseIndexBasketball
import com.five.live.model.data.basketball.homepage.past.future.PastFutureBasketBall
import com.five.live.model.data.basketball.league.LeagueBaseInfo
import com.five.live.model.data.basketball.odds.BasketballOddsBase
import com.five.live.model.data.generalized.GeneralTokenResponse
import com.five.live.model.data.generalized.OtpResponse
import com.five.live.model.data.homepage.analysis.AnalysisBase
import com.five.live.model.data.homepage.briefing.BriefingBase
import com.five.live.model.data.homepage.event.EventBase
import com.five.live.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.five.live.model.data.homepage.liveOdds.BaseLiveOdds
import com.five.live.model.data.homepage.new2.BaseClassIndexNew
import com.five.live.model.data.homepage.past.future.PastFutureBaseCall
import com.five.live.model.data.livescorepin.LiveScorePin
import com.five.live.model.data.news.NewsBase
import com.five.live.model.data.news.details.NewsPostBase
import com.five.live.model.data.raw.LoginRequestBody
import com.five.live.model.data.raw.OTPRequestBody
import com.five.live.model.data.standings.StandingsBase
import com.five.live.model.data.standings.player.PlayerStandingBase
import com.five.live.model.data.videos.VideosListBase


interface ApiHelper {
    suspend fun getNews(locale: String, page: String) : NewsBase
    suspend fun getPostDetail(locale: String,postID: String): NewsPostBase
    suspend fun getVideos(locale: String,page: String):VideosListBase
    suspend fun getStandings(leagueID:String,sub: String): StandingsBase
    suspend fun getPlayerStanding(leagueID: String,season:String):PlayerStandingBase
    suspend fun authenticateUser(body: LoginRequestBody):GeneralTokenResponse
    suspend fun sendOTP(body:OTPRequestBody):OtpResponse
    suspend fun getHomeMatches(locale: String, pageNumber: String): BaseClassIndexNew
    suspend fun getLiveOdds(): BaseLiveOdds
    suspend fun getAnalysisForMatch(matchId:String):AnalysisBase
    suspend fun getEvents(): EventBase
    suspend fun getBriefing(matchId: String): BriefingBase
    suspend fun getLeagueInfo(leagueId: String, subLeagueId: String, groupId: String): BaseLeagueInfoHomePage
    suspend fun getBasketballMatches(): BaseIndexBasketball
    suspend fun getMatchUpdate(matchId: String): LiveScorePin
    suspend fun getBasketballLiveOdds(): BasketballOddsBase
    suspend fun getAnalysisForMatchBasketball(matchId: String): AnalysisBasktetballBase
    suspend fun getBasketballLeague(leagueId: String):LeagueBaseInfo
    suspend fun getBasketBallBriefing(matchId: String): BasketballBriefingBase
    suspend fun getPastFutureMatches(date: String): PastFutureBaseCall
    suspend fun getPastFutureMatchesBasketball(date: String): PastFutureBasketBall
}