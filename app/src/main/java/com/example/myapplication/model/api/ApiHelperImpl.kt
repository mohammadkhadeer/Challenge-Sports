package com.example.myapplication.model.api

import com.example.myapplication.model.data.generalized.GeneralTokenResponse
import com.example.myapplication.model.data.generalized.OtpResponse
import com.example.myapplication.model.data.homepage.analysis.AnalysisBase
import com.example.myapplication.model.data.homepage.event.EventBase
import com.example.myapplication.model.data.homepage.liveOdds.BaseLiveOdds
import com.example.myapplication.model.data.homepage.new2.BaseClassIndexNew
import com.example.myapplication.model.data.news.NewsBase
import com.example.myapplication.model.data.news.details.NewsPostBase
import com.example.myapplication.model.data.raw.LoginRequestBody
import com.example.myapplication.model.data.raw.OTPRequestBody
import com.example.myapplication.model.data.standings.StandingsBase
import com.example.myapplication.model.data.standings.player.PlayerStandingBase
import com.example.myapplication.model.data.videos.VideosListBase


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

}