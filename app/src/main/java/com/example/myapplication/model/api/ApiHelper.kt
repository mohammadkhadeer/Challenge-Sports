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
}