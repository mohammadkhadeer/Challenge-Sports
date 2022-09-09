package com.example.myapplication.view.fragments.homeFrags.adapter

interface MainAdapterCommunicator {
   fun onMessageFromAdapter(message:MainAdapterMessages,position:Int,resourceId:Int)
}
enum class MainAdapterMessages{
    OPEN_INDEX,
   CLOSE_INDEX,
   OPEN_ANALYSIS,
   OPEN_LEAGUE,
   CLOSE_LEAGUE,
   OPEN_EVENT
}