package com.football.live.model.data.standings.sorted

import com.football.live.model.data.standings.LeagueInfo
import com.football.live.model.data.standings.TeamInfo

class SortedStandings(var leagueInfo: LeagueInfo,var hasStandings:Boolean,
                      var teamInfo: ArrayList<TeamInfo>,var standings:ArrayList<StandingObject>) {

}