package com.challenge.sports.model.api

import com.challenge.sports.model.data.MatchesKotlin.MatchesRootK
import com.challenge.sports.model.data.generalized.GeneralTokenResponse
import com.challenge.sports.model.data.generalized.OtpResponse
import com.challenge.sports.model.data.news.NewsBase
import com.challenge.sports.model.data.news.details.NewsPostBase
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
//    @GET(" api/football/matchlist/today?hotMatches=true")
//    suspend fun getHotMatches(): MatchesRootK

    @GET(" api/football/matchlist/today")
    suspend fun getHotMatches(@Query("hotMatches") hasHot:Boolean): MatchesRootK

    @GET(" /api/post-list/{locale}/{page}")
    suspend fun getNews(@Path("locale") locale:String,@Path("page") page:String): NewsBase

    @GET("/api/post/{locale}/{postId}")
    suspend fun getPostDetail(@Path("locale") locale:String,@Path("postId") postId:String): NewsPostBase



}