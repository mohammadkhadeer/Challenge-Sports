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


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

   override suspend fun getNews(locale: String, page: String): NewsBase =apiService.getNews(locale,page)
   override suspend fun getPostDetail(locale: String,postID: String): NewsPostBase=apiService.getPostDetail(locale,postID)
   override suspend fun getVideos(locale: String, page: String): VideosListBase =apiService.getVideos(locale,page)
   override suspend fun getStandings(leagueID: String, sub: String): StandingsBase =apiService.getStandings(leagueID,sub)
   override suspend fun getPlayerStanding(leagueID: String, season: String): PlayerStandingBase=apiService.getPlayerStanding(leagueID,season)
   override suspend fun authenticateUser(body:LoginRequestBody): GeneralTokenResponse =apiService.authenticateUser(body)
   override suspend fun sendOTP(body: OTPRequestBody): OtpResponse =apiService.sendOTP(body)
   override suspend fun getHomeMatches(locale: String, pageNumber: String): BaseClassIndexNew =apiService.getHomeMatches(locale,pageNumber)
   override suspend fun getLiveOdds(): BaseLiveOdds =apiService.getLiveOdds()
   override suspend fun getAnalysisForMatch(matchId: String): AnalysisBase =apiService.getAnalysisForMatch(matchId)
   override suspend fun getEvents(): EventBase=apiService.getEvents()
   override suspend fun getBriefing(matchId: String): BriefingBase =apiService.getBrief(matchId)
   override suspend fun getLeagueInfo(leagueId: String, subLeagueId: String, groupId: String): BaseLeagueInfoHomePage =apiService.getLeagueInfo(leagueId,subLeagueId,groupId)
   override suspend fun getBasketballMatches(): BaseIndexBasketball = apiService.getBasketballMatches()
   override suspend fun getMatchUpdate(matchId: String): LiveScorePin=apiService.getMatchUpdate(matchId)
   override suspend fun getBasketballLiveOdds(): BasketballOddsBase= apiService.getBasketballLiveOdds()
   override suspend fun getAnalysisForMatchBasketball(matchId: String): AnalysisBasktetballBase =apiService.getAnalysisForMatchBasketball(matchId)
   override suspend fun getBasketballLeague(leagueId: String): LeagueBaseInfo =apiService.getBasketballLeague(leagueId)
   override suspend fun getBasketBallBriefing(matchId: String): BasketballBriefingBase=apiService.getBasktetBallBriefing(matchId)
   override suspend fun getPastFutureMatches(date: String): PastFutureBaseCall=apiService.getPastFutureMatches(date)
   override suspend fun getPastFutureMatchesBasketball(date: String): PastFutureBasketBall = apiService.getPastFutureMatchesBasketball(date)


}