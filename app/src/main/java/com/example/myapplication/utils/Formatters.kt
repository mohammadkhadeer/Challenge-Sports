package com.example.myapplication.utils

import com.example.myapplication.model.data.homepage.event.Event
import com.example.myapplication.model.data.homepage.event.EventBase
import com.example.myapplication.model.data.homepage.event.EventX
import com.example.myapplication.model.data.homepage.event.Technic
import com.example.myapplication.model.data.homepage.event.formatted.EventKind
import com.example.myapplication.model.data.homepage.event.formatted.EventKind.*
import com.example.myapplication.model.data.homepage.event.formatted.FormattedEventG_S_F
import com.example.myapplication.model.data.homepage.event.formatted.FormattedTecnicEvent
import java.security.spec.ECField

object Formatters {

    fun formatTechnic(technicString: String): ArrayList<FormattedTecnicEvent> {
        val technicEventList=ArrayList<FormattedTecnicEvent>()

        val technics=technicString.split(";")
        for (technic in technics){
            val values=technic.split(",")
            technicEventList.add(FormattedTecnicEvent(values[0],values[1],values[2]))
        }
        return technicEventList
    }

    fun formatEvents(matchId:String,eventBase:EventBase): ArrayList<FormattedEventG_S_F> {
        val list=ArrayList<FormattedEventG_S_F>()
        val goals=ArrayList<FormattedEventG_S_F>()
        val subs=ArrayList<FormattedEventG_S_F>()
        val fouls=ArrayList<FormattedEventG_S_F>()
        for (event in eventBase.eventList){
            if (event.matchId.toString()==matchId){
                for (eventX in event.event){
                    when(returnItemTypeForKind(eventX.kind.toString())){
                        GOAL -> {
                            goals.add(FormattedEventG_S_F(GOAL,eventX))
                        }
                        GOAL_HEADING ->{}
                        FOUL -> {
                            fouls.add(FormattedEventG_S_F(FOUL,eventX))
                        }
                        FOUL_HEADING -> {

                        }
                        SUBSTITUTION_HEADING -> {

                        }
                        SUBSTITUTION -> {
                            subs.add(FormattedEventG_S_F(FOUL,eventX))
                        }
                    }
                }
            }
        }
        val emptyEvent=EventX()
        if (goals.isNotEmpty()){
            list.add(FormattedEventG_S_F(GOAL_HEADING, emptyEvent))
        }
        list.addAll(goals)
        if (subs.isNotEmpty()){
            list.add(FormattedEventG_S_F(SUBSTITUTION_HEADING,emptyEvent))
        }
        list.addAll(subs)

        if (fouls.isNotEmpty()){
            list.add(FormattedEventG_S_F(FOUL_HEADING,emptyEvent))

        }
        list.addAll(fouls)

        return list

    }


    fun filterEvents(matchId:String,eventBase:EventBase): EventBase {

        val filteredEventBase=EventBase()

        val eventList=eventBase.eventList
        val eventArrayList=ArrayList<Event>()
        val technicArrayList= ArrayList<Technic>()
        for (event in eventList){
            if (event.matchId.toString() == matchId){
                eventArrayList.add(event)
            }
        }
        for (technic in eventBase.technic){
            if (technic.matchId.toString()==matchId){
                technicArrayList.add(technic)
            }
        }
        
        filteredEventBase.eventList= eventArrayList
        filteredEventBase.technic=technicArrayList
        
        return filteredEventBase
    }

    fun returnItemTypeForKind(kindIndex: String):EventKind{
        return when(kindIndex){
            "1"->{
                GOAL
            }
            "2"->{
                FOUL
            }
            "3"->{
                FOUL
            }
            "7"->{
                GOAL
            }
            "8"->{
                GOAL
            }
            "9"->{
                FOUL
            }
            "11"->{
                SUBSTITUTION
            }
            "13"->{
                GOAL
            }
            else->{
                FOUL
            }
        }
    }

     fun returnKind(kindIndex:String):String{
        return when(kindIndex){
            "1"->{
              "Goal"
            }
            "2"->{
                "Red Card"
            }
            "3"->{
                "Yellow Card"
            }
            "7"->{
                "Penalty Kick"
            }
            "8"->{
                "Own Goal"
            }
            "9"->{
                "Two Yellow to Red"
            }
            "11"->{
                "Substitution"
            }
            "13"->{
                "Missed Penalty"
            }
            else->{
                "Other Event"
            }
        }
    }

     fun returnTechnicName(technicIndex:String):String{
        return when(technicIndex){
            "1"->{
                "Tee off First"
            }
            "2"->{
                "First Corner Kick"
            }
            "3"->{
                "First Yellow Card"
            }
            "4"->{
                "Number of Shots"
            }
            "5"->{
                "Number of Shots on target"
            }
            "6"->{
                "Number of Fouls"
            }
            "7"->{
                "Number of Corners"
            }
            "8"->{
                "Number of Corners (Overtime)"
            }
            "9"->{
                "Free Kicks"
            }
            "10"->{
                "Number of Offsides"
            }
            "11"->{
                "Own Goals"
            }
            "12"->{
                "Yellow Cards"
            }
            "13"->{
                "Yellow Cards (Overtime)"
            }
            "14"->{
                "Red Cards"
            }
            "15"->{
                "Ball Control"
            }
            "16"->{
                "Header"
            }
            "17"->{
                "Save the Ball"
            }
            "18"->{
                "Goalkeeper Strikes"
            }
            "19"->{
                "Lose the Ball"
            }
            "20"->{
                "Successful Steal"
            }
            "21"->{
                "Block"
            }
            "22"->{
                "Long Pass"
            }
            "23"->{
                "Short Pass"
            }
            "24"->{
                "Assist"
            }
            "25"->{
                "Successful Pass"
            }
            "26"->{
                "First Substitution"
            }
            "27"->{
                "Last Substitution"
            }
            "28"->{
                "First Offside"
            }
            "29"->{
                "Last Offside"
            }
            "30"->{
                "Change the number of players"
            }
            "31"->{
                "Last Corner Kick"
            }
            "32"->{
                "Last Yellow Card"
            }
            "33"->{
                "Change The number of players (Overtime)"
            }
            "34"->{
                "Number of Offsides (Overtime)"
            }
            "35"->{
                "Missing a Goal"
            }
            "36"->{
                "Middle Column"
            }
            "37"->{
                "Number of Successful headers"
            }
            "38"->{
                "Blocked Shots"
            }
            "39"->{
                "Tackles"
            }
            "40"->{
                "Exceeding Times"
            }
            "41"->{
                "Out-of-Bounds"
            }
            "42"->{
                "Number of Passes"
            }
            "43"->{
                "Pass Success Rate"
            }
            "44"->{
                "Number of Attacks"
            }
            "45"->{
                "Number of Dangerous Attacks"
            }
            "46"->{
                "Half-Time Corner Kick"
            }
            "47"->{
                "Half Court Possession"
            }
            else -> {
                "other"
            }
        }
    }

}
