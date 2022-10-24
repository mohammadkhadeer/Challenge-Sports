package com.score.pro.model.data.standings.sorted

import com.score.pro.model.data.standings.LeagueInfo
import com.score.pro.model.data.standings.TeamInfo

class SortedStandings(var leagueInfo: LeagueInfo,var hasStandings:Boolean,
                      var teamInfo: ArrayList<TeamInfo>,var standings:ArrayList<StandingObject>) {

}