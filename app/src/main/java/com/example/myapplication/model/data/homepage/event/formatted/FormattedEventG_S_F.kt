package com.example.myapplication.model.data.homepage.event.formatted

import com.example.myapplication.model.data.homepage.event.EventBase
import com.example.myapplication.model.data.homepage.event.EventX

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