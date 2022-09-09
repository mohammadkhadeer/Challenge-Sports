package com.example.myapplication.model.data.standings.sorted

import com.example.myapplication.model.data.standings.LeagueInfo
import com.example.myapplication.model.data.standings.TeamInfo

class SortedStandings(var leagueInfo: LeagueInfo,var hasStandings:Boolean,
                      var teamInfo: ArrayList<TeamInfo>,var standings:ArrayList<StandingObject>) {

}