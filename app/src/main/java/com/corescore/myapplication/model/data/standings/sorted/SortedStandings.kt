package com.corescore.myapplication.model.data.standings.sorted

import com.corescore.myapplication.model.data.standings.LeagueInfo
import com.corescore.myapplication.model.data.standings.TeamInfo

class SortedStandings(var leagueInfo: LeagueInfo,var hasStandings:Boolean,
                      var teamInfo: ArrayList<TeamInfo>,var standings:ArrayList<StandingObject>) {

}