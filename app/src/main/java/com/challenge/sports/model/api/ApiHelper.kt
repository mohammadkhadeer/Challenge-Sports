package com.challenge.sports.model.api


import com.challenge.sports.model.data.MatchesKotlin.MatchesRootK
import com.challenge.sports.model.data.news.NewsBase



interface ApiHelper {
    suspend fun getMatches(hasHot:Boolean) : MatchesRootK

    suspend fun getNews(locale: String, page: String) : NewsBase

}