package com.challenge.sports.model.data.standings.sorted


class StandingObject(var standingType: StandingType,var Standing:ArrayList<Any> ) {
     enum class StandingType{
        TOTAL,
         HALF,
         HOME,
         AWAY,
         HOME_HALF,
         AWAY_HALF
    }
}