package com.challenge.sports.model.api


import com.challenge.sports.model.data.MatchesKotlin.MatchesRootK
import com.challenge.sports.model.data.generalized.GeneralTokenResponse
import com.challenge.sports.model.data.generalized.OtpResponse
import com.challenge.sports.model.data.news.NewsBase
import com.challenge.sports.model.data.news.details.NewsPostBase


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

//   override suspend fun getMatches(hasHot:Boolean): MatchesRootK = apiService.getHotMatches()
   override suspend fun getMatches(hasHot:Boolean): MatchesRootK = apiService.getHotMatches(hasHot)

   override suspend fun getNews(locale: String, page: String): NewsBase =apiService.getNews(locale,page)

}