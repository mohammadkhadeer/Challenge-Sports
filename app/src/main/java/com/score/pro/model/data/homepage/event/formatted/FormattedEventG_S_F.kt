package com.score.pro.model.data.homepage.event.formatted

import com.score.pro.model.data.homepage.event.EventX

class FormattedEventG_S_F(var eventType:EventKind,var eventDetail:EventX) {
}
enum class EventKind{
    GOAL,
    GOAL_HEADING,
    FOUL,
    FOUL_HEADING,
    SUBSTITUTION_HEADING,
    SUBSTITUTION
}