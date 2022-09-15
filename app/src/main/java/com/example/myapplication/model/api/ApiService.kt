package com.example.myapplication.model.api

import com.example.myapplication.model.data.generalized.GeneralTokenResponse
import com.example.myapplication.model.data.generalized.OtpResponse
import com.example.myapplication.model.data.homepage.analysis.AnalysisBase
import com.example.myapplication.model.data.homepage.briefing.BriefingBase
import com.example.myapplication.model.data.homepage.event.EventBase
import com.example.myapplication.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.example.myapplication.model.data.homepage.liveOdds.BaseLiveOdds
import com.example.myapplication.model.data.homepage. new2.BaseClassIndexNew
import com.example.myapplication.model.data.news.NewsBase
import com.example.myapplication.model.data.news.details.NewsPostBase
import com.example.myapplication.model.data.raw.LoginRequestBody
import com.example.myapplication.model.data.raw.OTPRequestBody
import com.example.myapplication.model.data.standings.StandingsBase
import com.example.myapplication.model.data.standings.player.PlayerStandingBase
import com.example.myapplication.model.data.videos.VideosListBase
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET(" /api/post-list/{locale}/{page}")
    suspend fun getNews(@Path("locale") locale:String,@Path("page") page:String): NewsBase

    @GET("/api/post/{locale}/{postId}")
    suspend fun getPostDetail(@Path("locale") locale:String,@Path("postId") postId:String): NewsPostBase

    @GET("/api/video-list/{locale}/{page}")
    suspend fun getVideos(@Path("locale") locale:String,@Path("page") page:String): VideosListBase

    @GET("/api/zqbf-list-jifen/{leagueId}/sub/{subId}")
    suspend fun getStandings(@Path("leagueId") leagueID: String,@Path("subId") sub: String): StandingsBase

    @GET("/api/zqbf-list-jifen-top-scorer/{leagueId}/season/{season}")
    suspend fun getPlayerStanding(@Path("leagueId") leagueID: String,@Path("season")season:String): PlayerStandingBase

    @GET("/api/zqbf-list-page/{locale}/{page}")
    suspend fun getHomeMatches(@Path("locale") locale: String,@Path("page") pageNumber: String): BaseClassIndexNew

    @POST("/api/v2/login")
    suspend fun authenticateUser( @Body body:LoginRequestBody): GeneralTokenResponse

    @POST("/api/v2/send-otp")
    suspend fun sendOTP(@Body body: OTPRequestBody): OtpResponse

    @GET("/api/zqbf-live-odds")
    suspend fun getLiveOdds(): BaseLiveOdds

    @GET("/api/zqbf-match-analysis/{matchId}")
    suspend fun getAnalysisForMatch(@Path("matchId") matchId: String): AnalysisBase

    @GET("/api/zqbf-list-event")
    suspend fun getEvents(): EventBase

    @GET("/api/zqbf-match-briefing-en/{matchId}")
    suspend fun getBrief(@Path("matchId") matchId:String): BriefingBase

    @GET("/api/zqbf-list-league/{leagueId}/{subleagueId}/{groupId}")
    suspend fun getLeagueInfo(@Path("leagueId")leagueId: String, @Path("subleagueId") subLeagueId: String,@Path("groupId") groupId: String): BaseLeagueInfoHomePage

}