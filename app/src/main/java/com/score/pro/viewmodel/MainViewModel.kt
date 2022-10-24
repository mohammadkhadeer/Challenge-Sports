package com.score.pro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.score.pro.model.api.ApiHelper
import com.score.pro.model.data.basketball.analysis.AnalysisBasktetballBase
import com.score.pro.model.data.basketball.briefing.BasketballBriefingBase
import com.score.pro.model.data.basketball.homepage.BaseIndexBasketball
import com.score.pro.model.data.basketball.homepage.past.future.PastFutureBasketBall
import com.score.pro.model.data.basketball.league.LeagueBaseInfo
import com.score.pro.model.data.basketball.odds.BasketballOddsBase
import com.score.pro.model.data.homepage.analysis.AnalysisBase
import com.score.pro.model.data.homepage.event.EventBase
import com.score.pro.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.score.pro.model.data.homepage.leagueInfo.LeagueStanding
import com.score.pro.model.data.homepage.leagueInfo.any.LeagueStandingTypeGroupBase
import com.score.pro.model.data.homepage.liveOdds.BaseLiveOdds
import com.score.pro.model.data.homepage.new2.BaseClassIndexNew
import com.score.pro.model.data.homepage.new2.Match
import com.score.pro.model.data.homepage.past.future.PastFutureBaseCall
import com.score.pro.model.data.livescorepin.LiveScorePin
import com.score.pro.model.data.news.NewsBase
import com.score.pro.model.data.news.details.NewsPostBase
import com.score.pro.model.data.news.details.OnPostDetailResponse
import com.score.pro.model.data.standings.StandingsBase
import com.score.pro.model.data.standings.player.PlayerStandingBase
import com.score.pro.model.data.standings.sorted.SortedStandings
import com.score.pro.model.data.videos.VideosListBase
import com.score.pro.utils.Resource
import com.score.pro.utils.SharedPreference
import com.score.pro.utils.SubLeagueException
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.launch
import kotlin.Exception

class MainViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    val pastFutureLiveData=MutableLiveData<Resource<PastFutureBaseCall>>()
    val pastFutureLiveDataBasketball=MutableLiveData<Resource<PastFutureBasketBall>>()
    val basketballBriefingLiveData=MutableLiveData<Resource<BasketballBriefingBase>>()
    val analysisLiveDataBasketball=MutableLiveData<Resource<AnalysisBasktetballBase>>()
    var basketballLiveOdds= MutableLiveData<Resource<BasketballOddsBase>>()
    var newsLiveData = MutableLiveData<Resource<NewsBase>>()
    var videosLiveData = MutableLiveData<Resource<VideosListBase>>()
    var currentPageNumberNews=1
    var lastPageNumberNews=100
    var lastPageNumberVideos=100
    var currentPageNumberVideos=1
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
    var basketBallLiveData=MutableLiveData<Resource<BaseIndexBasketball>>()
    var updateMatchLiveData=MutableLiveData<Resource<LiveScorePin>>()
    var baskteballLeagueLiveData=MutableLiveData<Resource<LeagueBaseInfo>>()
    var lastPageMatchesBase=0
    var currentPageMatches=0

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

    fun makeNewsCall(pageNumber: String,lang:String) {
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

    fun makeNewsPostCall(postID: String, onResponseListener: OnPostDetailResponse<NewsPostBase>,lang: String) {
        viewModelScope.launch {
            onResponseListener.onLoading("fetching data")
            try {
                val newsDetail = apiHelper.getPostDetail(lang, postID)
                onResponseListener.onSuccess(newsDetail)
            } catch (e: Exception) {
                onResponseListener.onFailure(e.toString())
            }
        }
    }

    private fun makeVideosListCall() {
      //  makeVideosListCall("1")
    }
    fun makeVideosListCall(listener: OnPostDetailResponse<VideosListBase>,lang: String){
        viewModelScope.launch {
            listener.onLoading("Loading")
            try {
                currentPageNumberVideos++
                val news = apiHelper.getVideos(lang, currentPageNumberVideos.toString())
                listener.onSuccess(news)
                currentPageNumberVideos=news.meta.currentPage
                lastPageNumberVideos=news.meta.lastPage
            } catch (e: Exception) {
                listener.onFailure(e.toString())
            }
        }
    }

    fun makeVideosListCall(pageNumber: String,lang: String) {
        viewModelScope.launch {
            videosLiveData.postValue(Resource.loading(null))
            try {
                val vidsBase = apiHelper.getVideos(lang, pageNumber)
                maxPageNumberVideos = vidsBase.meta.lastPage
                videosLiveData.postValue(Resource.success(vidsBase))
            } catch (e: Exception) {
                videosLiveData.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun makeIndexNetworkCall(pageNumber: String,lang: String){
        viewModelScope.launch {
            baseHomePageLiveData.postValue(Resource.loading(null))
            try {
                val homeBase=apiHelper.getHomeMatches(lang,pageNumber)
                lastPageMatchesBase=homeBase.meta.lastPage
                currentPageMatches=homeBase.meta.currentPage
                baseHomePageLiveData.postValue(Resource.success(homeBase))
            }catch (e:Exception){
                baseHomePageLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun makeIndexNetworkCall(onResponseListener: OnPostDetailResponse<List<Match>>,lang: String){

        if (currentPageMatches==lastPageMatchesBase){
            onResponseListener.onFailure(SharedPreference.MAX_PAGE_REACHED)
            return
        }
        viewModelScope.launch {
            onResponseListener.onLoading("Loading")
            try {
                currentPageMatches++
                val homeBase=apiHelper.getHomeMatches(lang,currentPageMatches.toString())
                lastPageMatchesBase=homeBase.meta.lastPage
                currentPageMatches=homeBase.meta.currentPage
                onResponseListener.onSuccess(homeBase.matchList)
            }catch (e:Exception){
                onResponseListener.onFailure(e.toString())
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

    private fun sortTheListOfOdds(odds:BasketballOddsBase,matchId:String): BasketballOddsBase {


        val spread=ArrayList<Any>()
        val total=ArrayList<Any>()
        for (item in odds.list[0].spread){
            if ((item[0] as Double).toInt().toString()==matchId){
                spread.add(item)
            }
        }
        for (item in odds.list[0].total){
            if ((item[0] as Double).toInt().toString()==matchId){
                total.add(item)
            }
        }
        odds.list[0].spread= listOf(spread) as List<List<Double>>
        odds.list[0].total= listOf(total) as List<List<Double>>
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
            var leagueBaseInfo:BaseLeagueInfoHomePage?=null
            try {
                leagueInfoLiveData.postValue(Resource.loading(null))
               val leagueInfoBase=apiHelper.getLeagueInfo(leagueId,subLeague?:"0",groupId?:"0")
                leagueBaseInfo=leagueInfoBase
                leagueInfoLiveData.postValue(Resource.success(testCasting(leagueInfoBase)))
            }catch (e:Exception){
                if (e is SubLeagueException){
                    val list=leagueBaseInfo?.leagueStanding?.get(0) as LinkedTreeMap<Any,Any>
                   val map= list["list"] as ArrayList<LinkedTreeMap<Any,Any>>
                    val value=(map[0]["subId"] as Double).toInt()
                    println(value.toInt())

                    getLeagueInfoForMatch(leagueId,value.toString(),null)
                }else{
                    briefingLiveData.postValue(Resource.error(e.toString(),null))
                }

            }
        }
    }

    fun makeIndexBasketBallCall(){
        viewModelScope.launch {
            try {
                basketBallLiveData.postValue(Resource.loading(null))
                val basketBallMatchesList=apiHelper.getBasketballMatches()
                basketBallLiveData.postValue(Resource.success(basketBallMatchesList))
            }catch (e:Exception){
                basketBallLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun makeOddsBasketballCall(matchId:String){
        viewModelScope.launch {
            try {
                basketballLiveOdds.postValue(Resource.loading(null))
                val basketballOddsBase=apiHelper.getBasketballLiveOdds()
                basketballLiveOdds.postValue(Resource.success(sortTheListOfOdds(basketballOddsBase, matchId)))
            }catch (e:Exception){
                basketballLiveOdds.postValue(Resource.error(e.toString(),null))
            }
        }

    }


    private fun testCasting(leagueInfoBase: BaseLeagueInfoHomePage): BaseLeagueInfoHomePage{
        try {
            val obj=leagueInfoBase.leagueStanding[0]
            val jsonObj=Gson().toJson(obj)
            val groupObj=Gson().fromJson(jsonObj,LeagueStandingTypeGroupBase::class.java)
            try {
                println(groupObj.list[0].leagueId)
                if (groupObj.list[0].leagueId==0){
                      throw SubLeagueException("Call SubLeague Please")
                }
                leagueInfoBase.leagueStanding= listOf<LeagueStandingTypeGroupBase>(groupObj)
            }catch (e:Exception){
                if (e is SubLeagueException){
                    throw SubLeagueException(e.message?:"Subleague Please")
                }
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
        }catch (e:Exception){
            if (e is SubLeagueException){
                throw SubLeagueException(e.message?:"Subleage Exception")
            }
        }
        return leagueInfoBase
    }

    fun makeAnalysisCallBasketball(matchId: String) {
        viewModelScope.launch {
            try {
                analysisLiveDataBasketball.postValue(Resource.loading(null))
                val analysisBase=apiHelper.getAnalysisForMatchBasketball(matchId)
                analysisLiveDataBasketball.postValue(Resource.success(analysisBase))
            }catch (e:Exception){
                analysisLiveDataBasketball.postValue(Resource.error(e.toString(),null))
            }
        }
    }
    fun makeLeagueInfoCall(leagueId: String){
        viewModelScope.launch {
            try {
                baskteballLeagueLiveData.postValue(Resource.loading(null))
                val bBallLeague=apiHelper.getBasketballLeague(leagueId)
                baskteballLeagueLiveData.postValue(Resource.success(bBallLeague))
            }catch (e:Exception){
                baskteballLeagueLiveData.postValue(Resource.error(e.toString(),null))

            }
        }
    }

    fun makeBasketBallBriefingCall(matchId: String){
        viewModelScope.launch {
            try {
                basketballBriefingLiveData.postValue(Resource.loading(null))
                val briefingBase=apiHelper.getBasketBallBriefing(matchId)
                basketballBriefingLiveData.postValue(Resource.success(briefingBase))
            }catch (e:Exception){
                basketballBriefingLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun makePastFutureCall(date:String){
        viewModelScope.launch {
            try {
                pastFutureLiveData.postValue(Resource.loading(null))
                val pasFutureBase=apiHelper.getPastFutureMatches(date)
                pastFutureLiveData.postValue(Resource.success(pasFutureBase))
            }catch (e:Exception){
                pastFutureLiveData.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun makePastFutureCallBasketball(date: String) {
        viewModelScope.launch {
            try {
                pastFutureLiveDataBasketball.postValue(Resource.loading(null))
                val pasFutureBase=apiHelper.getPastFutureMatchesBasketball(date)
                pastFutureLiveDataBasketball.postValue(Resource.success(pasFutureBase))
            }catch (e:Exception){
                pastFutureLiveDataBasketball.postValue(Resource.error(e.toString(),null))
            }
        }
    }


}
