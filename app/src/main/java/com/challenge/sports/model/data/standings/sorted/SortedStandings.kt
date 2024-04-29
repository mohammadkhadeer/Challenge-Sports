package com.challenge.sports.model.data.standings.sorted

import com.challenge.sports.model.data.standings.LeagueInfo
import com.challenge.sports.model.data.standings.TeamInfo

class SortedStandings(var leagueInfo: LeagueInfo,var hasStandings:Boolean,
                      var teamInfo: ArrayList<TeamInfo>,var standings:ArrayList<StandingObject>) {

}