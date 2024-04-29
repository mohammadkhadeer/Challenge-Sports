package com.challenge.sports.model.api

import com.challenge.sports.model.data.basketball.analysis.AnalysisBasktetballBase
import com.challenge.sports.model.data.basketball.briefing.BasketballBriefingBase
import com.challenge.sports.model.data.basketball.homepage.BaseIndexBasketball
import com.challenge.sports.model.data.basketball.homepage.past.future.PastFutureBasketBall
import com.challenge.sports.model.data.basketball.league.LeagueBaseInfo
import com.challenge.sports.model.data.basketball.odds.BasketballOddsBase
import com.challenge.sports.model.data.generalized.GeneralTokenResponse
import com.challenge.sports.model.data.generalized.OtpResponse
import com.challenge.sports.model.data.homepage.analysis.AnalysisBase
import com.challenge.sports.model.data.homepage.briefing.BriefingBase
import com.challenge.sports.model.data.homepage.event.EventBase
import com.challenge.sports.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.challenge.sports.model.data.homepage.liveOdds.BaseLiveOdds
import com.challenge.sports.model.data.homepage.new2.BaseClassIndexNew
import com.challenge.sports.model.data.homepage.past.future.PastFutureBaseCall
import com.challenge.sports.model.data.livescorepin.LiveScorePin
import com.challenge.sports.model.data.news.NewsBase
import com.challenge.sports.model.data.news.details.NewsPostBase
import com.challenge.sports.model.data.raw.LoginRequestBody
import com.challenge.sports.model.data.raw.OTPRequestBody
import com.challenge.sports.model.data.standings.StandingsBase
import com.challenge.sports.model.data.standings.player.PlayerStandingBase
import com.challenge.sports.model.data.videos.VideosListBase


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