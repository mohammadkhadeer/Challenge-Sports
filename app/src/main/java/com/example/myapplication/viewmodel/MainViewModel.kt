package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.api.ApiHelper
import com.example.myapplication.model.data.homepage.analysis.AnalysisBase
import com.example.myapplication.model.data.homepage.event.EventBase
import com.example.myapplication.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.example.myapplication.model.data.homepage.leagueInfo.LeagueInfo
import com.example.myapplication.model.data.homepage.leagueInfo.LeagueStanding
import com.example.myapplication.model.data.homepage.leagueInfo.any.LeagueStandingTypeGroupBase
import com.example.myapplication.model.data.homepage.liveOdds.BaseLiveOdds
import com.example.myapplication.model.data.homepage. new2.BaseClassIndexNew
import com.example.myapplication.model.data.news.NewsBase
import com.example.myapplication.model.data.news.details.NewsPostBase
import com.example.myapplication.model.data.news.details.OnPostDetailResponse
import com.example.myapplication.model.data.standings.StandingsBase
import com.example.myapplication.model.data.standings.player.PlayerStandingBase
import com.example.myapplication.model.data.standings.sorted.SortedStandings
import com.example.myapplication.model.data.videos.VideosListBase
import com.example.myapplication.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlin.Exception

class MainViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    var newsLiveData = MutableLiveData<Resource<NewsBase>>()
    var videosLiveData = MutableLiveData<Resource<VideosListBase>>()
    var maxPageNumberNews = 100
    var maxPageNumberVideos = 1
    var standingsLiveData=MutableLiveData<Resource<StandingsBase>>()
    var sortedStandings=ArrayList<SortedStandings>()
    var baseHomePageLiveData=MutableLiveData<Resource<BaseClassIndexNew>>()
    var playerStandingsLiveData=MutableLiveData<Resource<PlayerStandingBase>>()
    var liveOddsLiveData=MutableLiveData<Resource<BaseLiveOdds>>()
    var analysisLiveData=MutableLiveData<Resource<AnalysisBase>>()
    var eventsLiveData=MutableLiveData<Resource<EventBase>>()
    var briefingLiveData=MutableLiveData<Resource<String>>()
    var leagueInfoLiveData=MutableLiveData<Resource<BaseLeagueInfoHomePage>>()

    init {
  /*      makeNewsCall()
        makeVideosListCall()*/
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

    fun makeNewsCall(pageNumber: String) {
        viewModelScope.launch {
            newsLiveData.postValue(Resource.loading(null))
            try {
                val news = apiHelper.getNews("en", pageNumber)
                newsLiveData.postValue(Resource.success(news))
            } catch (e: Exception) {
                newsLiveData.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun makeNewsPostCall(postID: String, onResponseListener: OnPostDetailResponse<NewsPostBase>) {
        viewModelScope.launch {
            onResponseListener.onLoading("fetching data")
            try {
                val newsDetail = apiHelper.getPostDetail("en", postID)
                onResponseListener.onSuccess(newsDetail)
            } catch (e: Exception) {
                onResponseListener.onFailure(e.toString())
            }
        }
    }

    private fun makeVideosListCall() {
        makeVideosListCall("1")
    }

    fun makeVideosListCall(pageNumber: String) {
        viewModelScope.launch {
            videosLiveData.postValue(Resource.loading(null))
            try {
                val vidsBase = apiHelper.getVideos("en", pageNumber)
                maxPageNumberVideos = vidsBase.meta.lastPage
                videosLiveData.postValue(Resource.success(vidsBase))
            } catch (e: Exception) {
                videosLiveData.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun makeIndexNetworkCall(pageNumber: String){
        viewModelScope.launch {
            baseHomePageLiveData.postValue(Resource.loading(null))
            try {
                val homeBase=apiHelper.getHomeMatches("en",pageNumber)
                baseHomePageLiveData.postValue(Resource.success(homeBase))
            }catch (e:Exception){
                baseHomePageLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun makeStandingsCall(leagueId:String){
        viewModelScope.launch {
            try {
                standingsLiveData.postValue(Resource.loading(null))
                val standingsBase=apiHelper.getStandings(leagueId,"0")
                standingsLiveData.postValue(Resource.success(standingsBase))
            }catch (e:Exception){
                standingsLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }
    fun makePlayerStandingCall(leagueId: String){
      viewModelScope.launch {
          try {
              playerStandingsLiveData.postValue(Resource.loading(null))

              val playerStandingBase=apiHelper.getPlayerStanding(leagueId,"0")
              playerStandingsLiveData.postValue(Resource.success(playerStandingBase))
          }catch (e:Exception){
              playerStandingsLiveData.postValue(Resource.error(e.toString(),null))
          }
      }
    }

    fun makeLiveOddsCall(matchId:String){
        viewModelScope.launch {
            try {
                liveOddsLiveData.postValue(Resource.loading(null))

                val playerStandingBase=apiHelper.getLiveOdds()
               val sorted= sortTheListOfOdds(playerStandingBase,matchId)
                liveOddsLiveData.postValue(Resource.success(sorted))
            }catch (e:Exception){
                liveOddsLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun makeUpdateCall(matchId: String){
        viewModelScope.launch {
            try {
                liveOddsLiveData.postValue(Resource.loading(null))

                val playerStandingBase=apiHelper.getLiveOdds()
                val sorted= sortTheListOfOdds(playerStandingBase,matchId)
                liveOddsLiveData.postValue(Resource.success(sorted))
            }catch (e:Exception){
                liveOddsLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    private fun sortTheListOfOdds(odds: BaseLiveOdds,matchId:String): BaseLiveOdds {
        val filteredBaseLiveOdds=BaseLiveOdds()

        val handicap=ArrayList<Any>()
        val europeOdds=ArrayList<Any>()
        val overUnder=ArrayList<Any>()
        val handicapHalf=ArrayList<Any>()
        val overUnderHalf=ArrayList<Any>()
        for (item in odds.list[0].handicap){
            if ((item[0] as Double).toInt().toString()==matchId){
                handicap.add(item)
            }
        }
        for (item in odds.list[0].europeOdds){
            if ((item[0] as Double).toInt().toString()==matchId){
                europeOdds.add(item)
            }
        }
        for (item in odds.list[0].overUnder){
            if ((item[0] as Double).toInt().toString()==matchId){
                overUnder.add(item)
            }
        }
        for (item in odds.list[0].overUnderHalf){
            if ((item[0] as Double).toInt().toString()==matchId){
                overUnderHalf.add(item)
            }
        }
        for (item in odds.list[0].handicapHalf){
            if ((item[0] as Double).toInt().toString()==matchId){
                handicapHalf.add(item)
            }
        }
        odds.list[0].handicap= listOf(handicap)
        odds.list[0].europeOdds= listOf(europeOdds)
        odds.list[0].overUnder= listOf(overUnder)
        odds.list[0].overUnderHalf= listOf(overUnderHalf)
        odds.list[0].handicapHalf= listOf(handicapHalf)
        return odds
    }

    fun makeAnalysisCall(matchId:String){
        viewModelScope.launch {
            try {
                analysisLiveData.postValue(Resource.loading(null))
                val analysisBase=apiHelper.getAnalysisForMatch(matchId)
                analysisLiveData.postValue(Resource.success(analysisBase))
            }catch (e:Exception){
                analysisLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun makeEventListCall(){
        viewModelScope.launch {
            try {
                eventsLiveData.postValue(Resource.loading(null))
                val eventBase=apiHelper.getEvents()
                eventsLiveData.postValue(Resource.success(eventBase))
            }catch (e:Exception){
                eventsLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun getBriefing(matchId:String){
        viewModelScope.launch {
            try {
                briefingLiveData.postValue(Resource.loading(null))
                val briefingBase=apiHelper.getBriefing(matchId)
                briefingLiveData.postValue(Resource.success(briefingBase.recommendEn))
            }catch (e:Exception){
                briefingLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }
    fun getLeagueInfoForMatch(leagueId:String,subLeague:String?,groupId:String?){
        viewModelScope.launch {
            try {
                leagueInfoLiveData.postValue(Resource.loading(null))
               val leagueInfoBase=apiHelper.getLeagueInfo(leagueId,subLeague?:"0",groupId?:"0")
                leagueInfoLiveData.postValue(Resource.success(testCasting(leagueInfoBase)))
            }catch (e:Exception){
                briefingLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    private fun testCasting(leagueInfoBase: BaseLeagueInfoHomePage): BaseLeagueInfoHomePage {

        val obj=leagueInfoBase.leagueStanding[0]
        val jsonObj=Gson().toJson(obj)
        val groupObj=Gson().fromJson(jsonObj,LeagueStandingTypeGroupBase::class.java)
        try {
            println(groupObj.list[0].leagueId)
            leagueInfoBase.leagueStanding= listOf<LeagueStandingTypeGroupBase>(groupObj)
        }catch (e:Exception){
            val groupObjOriginal=Gson().fromJson(jsonObj,LeagueStanding::class.java)
            leagueInfoBase.leagueStanding= listOf<LeagueStanding>(groupObjOriginal)
            println(leagueInfoBase)
        }
        when (leagueInfoBase.leagueStanding[0]){
            is LeagueStandingTypeGroupBase->{
                println("Groups Base")
            }
            is LeagueStanding->{
                println("Standard Base")
            }
            else->{
                println("Bad News!")
            }
        }
        return leagueInfoBase
    }


}
