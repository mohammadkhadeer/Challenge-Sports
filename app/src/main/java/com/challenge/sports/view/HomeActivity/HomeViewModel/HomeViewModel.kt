//package com.challenge.sports.view.HomeActivity.HomeViewModel
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.challenge.sports.model.api.ApiHelper
//import com.challenge.sports.model.data.Matches.MatchesRoot
//import com.challenge.sports.model.data.videos.VideosListBase
//import com.challenge.sports.utils.Resource
//import kotlinx.coroutines.launch
//
//class HomeViewModel (private val apiHelper: ApiHelper) : ViewModel() {
//    var matches_root = MutableLiveData<Resource<MatchesRoot>>()
//
//     fun getMatchesList() {
//        viewModelScope.launch {
//            matches_root.postValue(Resource.loading(null))
//            try {
//                val matchesList = apiHelper.getMatches("en", "1")
////                maxPageNumberNews = news.meta.lastPage
//                matches_root.postValue(Resource.success(matchesList))
//            } catch (e: Exception) {
//                matches_root.postValue(Resource.error(e.toString(), null))
//            }
//        }
//    }
//}