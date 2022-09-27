package com.example.myapplication.view.fragments.homeFrags.adapter

interface MainAdapterCommunicator {
   companion object{
      const val BASKETBALL_TYPE=0
      const val FOOTBALL_TYPE=1
   }
   fun onMessageFromAdapter(message:MainAdapterMessages,position:Int,adapterType:Int)
}
enum class MainAdapterMessages{
    OPEN_INDEX,
   CLOSE_INDEX,
   OPEN_ANALYSIS,
   OPEN_LEAGUE,
   CLOSE_LEAGUE,
   OPEN_EVENT,
   OPEN_BRIEF,
   LONG_PRESS_ITEM,
   LOAD_MORE
}