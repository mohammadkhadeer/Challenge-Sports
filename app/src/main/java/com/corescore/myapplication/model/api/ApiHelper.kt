package com.corescore.myapplication.model.api

import com.corescore.myapplication.model.data.basketball.analysis.AnalysisBasktetballBase
import com.corescore.myapplication.model.data.basketball.briefing.BasketballBriefingBase
import com.corescore.myapplication.model.data.basketball.homepage.BaseIndexBasketball
import com.corescore.myapplication.model.data.basketball.homepage.past.future.PastFutureBasketBall
import com.corescore.myapplication.model.data.basketball.league.LeagueBaseInfo
import com.corescore.myapplication.model.data.basketball.odds.BasketballOddsBase
import com.corescore.myapplication.model.data.generalized.GeneralTokenResponse
import com.corescore.myapplication.model.data.generalized.OtpResponse
import com.corescore.myapplication.model.data.homepage.analysis.AnalysisBase
import com.corescore.myapplication.model.data.homepage.briefing.BriefingBase
import com.corescore.myapplication.model.data.homepage.event.EventBase
import com.corescore.myapplication.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.corescore.myapplication.model.data.homepage.liveOdds.BaseLiveOdds
import com.corescore.myapplication.model.data.homepage.new2.BaseClassIndexNew
import com.corescore.myapplication.model.data.homepage.past.future.PastFutureBaseCall
import com.corescore.myapplication.model.data.livescorepin.LiveScorePin
import com.corescore.myapplication.model.data.news.NewsBase
import com.corescore.myapplication.model.data.news.details.NewsPostBase
import com.corescore.myapplication.model.data.raw.LoginRequestBody
import com.corescore.myapplication.model.data.raw.OTPRequestBody
import com.corescore.myapplication.model.data.standings.StandingsBase
import com.corescore.myapplication.model.data.standings.player.PlayerStandingBase
import com.corescore.myapplication.model.data.videos.VideosListBase


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