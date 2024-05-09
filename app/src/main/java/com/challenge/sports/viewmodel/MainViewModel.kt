package com.challenge.sports.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.sports.model.api.ApiHelper
import com.challenge.sports.model.data.MatchesKotlin.MatchesRootK

import com.challenge.sports.model.data.news.NewsBase
import com.challenge.sports.model.data.news.details.NewsPostBase
import com.challenge.sports.model.data.news.details.OnPostDetailResponse

import com.challenge.sports.utils.Resource
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.utils.SubLeagueException
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.launch
import kotlin.Exception
import kotlin.math.log10

class MainViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    var matches_root = MutableLiveData<Resource<MatchesRootK>>()


    var newsLiveData = MutableLiveData<Resource<NewsBase>>()
    var newsLiveData2 = MutableLiveData<Resource<NewsBase>>()

    var currentPageNumberNews=1
    var lastPageNumberNews=100
    var lastPageNumberVideos=100
    var currentPageNumberVideos=1
    var maxPageNumberNews = 100
    var maxPageNumberVideos = 1


    var lastPageMatchesBase=0
    var currentPageMatches=0

    init {
  /*      makeNewsCall()
        makeVideosListCall()*/
    }

    fun getMatchesList() {
        viewModelScope.launch {
            matches_root.postValue(Resource.loading(null))
            try {
                val matchesList = apiHelper.getMatches(true)

                Log.i("TAG","matchesList "+matchesList)
                matches_root.postValue(Resource.success(matchesList))

            } catch (e: Exception) {
                matches_root.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun makeNewsCallHorizontal(pageNumber: String,lang:String) {
        viewModelScope.launch {
            newsLiveData.postValue(Resource.loading(null))
            try {
                val news = apiHelper.getNews(lang, pageNumber)
                newsLiveData.postValue(Resource.success(news))
            } catch (e: Exception) {
                newsLiveData.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    private fun makeNewsCall() {
        viewModelScope.launch {
            newsLiveData.postValue(Resource.loading(null))
            try {
                val news = apiHelper.getNews("en", "1")
                maxPageNumberNews = news.meta.lastPage
                newsLiveData.postValue(Resource.success(news))
            } catch (e: Exception) {
                newsLiveData.postValue(Resource.error(e.toString(), null))
            }
        }
    }



    fun makeNewsCall(pageNumber: String,lang:String) {
        viewModelScope.launch {
            newsLiveData2.postValue(Resource.loading(null))
            try {
                val news = apiHelper.getNews(lang, pageNumber)
                newsLiveData2.postValue(Resource.success(news))
            } catch (e: Exception) {
                newsLiveData2.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun makeNewsCall(listener: OnPostDetailResponse<NewsBase>,lang: String) {
        viewModelScope.launch {
            listener.onLoading("Loading")
            try {
                currentPageNumberNews++
                val news = apiHelper.getNews(lang, currentPageNumberNews.toString())
               listener.onSuccess(news)
                currentPageNumberNews=news.meta.currentPage
                lastPageNumberNews=news.meta.lastPage
            } catch (e: Exception) {
                listener.onFailure(e.toString())
            }
        }
    }



    private fun makeVideosListCall() {
      //  makeVideosListCall("1")
    }


}
