package com.example.myapplication.utils

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.myapplication.model.data.homepage.new2.Match
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


object GeneralTools {

    fun setAlarmFor(context: Context,time: Long){
        val intent = Intent(context, MatchReminder::class.java)
        intent.putExtra("heading","Match: Team1 vs Team2")
        val pendingIntent = PendingIntent.getBroadcast(
            context.applicationContext, 666, intent, 0)
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager?.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,time,pendingIntent)
        }else{
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP,time,pendingIntent)
        }
        Toast.makeText(context,"Done!",Toast.LENGTH_LONG).show()
    }

    fun flipReplaceAnimation(toBeReplaced:View,toReplaceWith:View){
        YoYo.with(Techniques.FlipOutX)
            .apply {
                duration(200)
                withListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {

                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        toBeReplaced.visibility=View.GONE
                        toReplaceWith.visibility=View.VISIBLE
                        YoYo.with(Techniques.FlipInX)
                            .apply {
                                duration(200)
                                playOn(toReplaceWith)
                            }
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                playOn(toBeReplaced)
            }
    }
    fun saveToHighlightedMatches(match:Match,context:Context){
        val currentMatchesJson=SharedPreference.getInstance().getStringValueFromPreference(SharedPreference.SAVED_MATCHES_TOKEN,null,context)
        val type=object : TypeToken<ArrayList<Match>>(){}.type
        var list=Gson().fromJson<ArrayList<Match>>(currentMatchesJson,type)
        if (list.isNullOrEmpty())
         list=ArrayList()
        for (item in list){
            if (item.matchId==match.matchId){
                return
            }
        }
        list.add(match)
        SharedPreference.getInstance().saveStringToPreferences(SharedPreference.SAVED_MATCHES_TOKEN,Gson().toJson(list),context)
    }
    fun getHighlightedMatches(context: Context):ArrayList<Match>?{
        val currentMatchesJson=SharedPreference.getInstance().getStringValueFromPreference(SharedPreference.SAVED_MATCHES_TOKEN,null,context)
        val type=object : TypeToken<ArrayList<Match>>(){}.type
        val list=Gson().fromJson<ArrayList<Match>>(currentMatchesJson,type)
        return list?:null
    }
    fun removeHighlightedMatches(match: Match,context: Context){
        val currentMatchesJson=SharedPreference.getInstance().getStringValueFromPreference(SharedPreference.SAVED_MATCHES_TOKEN,null,context)
        val type=object : TypeToken<ArrayList<Match>>(){}.type
        var list=Gson().fromJson<ArrayList<Match>>(currentMatchesJson,type)
        if (list.isEmpty()||list==null)
            list=ArrayList()
        for (item in list){
            if (item.matchId==match.matchId){
                list.remove(item)
            }
        }
        SharedPreference.getInstance().saveStringToPreferences(SharedPreference.SAVED_MATCHES_TOKEN,Gson().toJson(list),context)
    }

    @SuppressLint("SimpleDateFormat")
    fun getCalculatedDate(dateFormat: String?, days: Int): String{
        val cal: Calendar = Calendar.getInstance()
        val s = SimpleDateFormat(dateFormat)
        cal.add(Calendar.DAY_OF_YEAR, days)
        return s.format(Date(cal.timeInMillis))
    }
    fun getPastWeekDates():List<String>{
        val datesList=ArrayList<String>()
        for (i in -1 downTo -8){
            datesList.add(getCalculatedDate("E|yyyy-MM-dd",i))
        }
        return datesList
    }
    fun getFutureDates():List<String>{
        val datesList=ArrayList<String>()
        for (i in 1 .. 8){
            datesList.add(getCalculatedDate("E|yyyy-MM-dd",i))
        }
        return datesList
    }

      fun return24HrsOnly(matchTime:String):String{
        val splitDateTime=matchTime.split(" ")[1].split(":")
        return splitDateTime[0]+":"+splitDateTime[1]
    }


      fun returnTime(date:String):String{
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        val time=returnGMTToCurrentTimezone(returnGMTTimeInMs(date))
        return sdf.format(time)
    }

      fun returnMinutes(dataObject: Match):String
    {
        val startTimeMs=returnGMTTimeInMs(dataObject.startTime)
        val matchTimeMs=returnGMTTimeInMs(dataObject.matchTime)
        var minutesElapsed=returnTimeInGmtCurrentLocale()-startTimeMs
        minutesElapsed /= 60000
        return minutesElapsed.toString()
    }

      fun returnTimeInGmtCurrentLocale():Long{
        val timeZone= TimeZone.getDefault().getDisplayName(false,TimeZone.SHORT)
        val time=timeZone.substringAfter("+")
        var hours=time.substringBefore(":").toLong()
        var minutes=time.substringAfter(":").toLong()
        hours *= MainAdapter.HOUR_CONSTANT
        minutes *= MainAdapter.MINS_CONSTANT
        val timeFinal= if (timeZone.contains("+")){
            System.currentTimeMillis()-(hours+minutes)
        }else{
            System.currentTimeMillis()+(hours+minutes)
        }
        return timeFinal
    }
      fun returnGMTToCurrentTimezone(timeMs:Long):Long{

        val timeZone= TimeZone.getDefault().getDisplayName(false,TimeZone.SHORT)
        val time=timeZone.substringAfter("+")
        var hours=time.substringBefore(":").toLong()
        var minutes=time.substringAfter(":").toLong()
        hours *= MainAdapter.HOUR_CONSTANT
        minutes *= MainAdapter.MINS_CONSTANT
        val timeFinal= if (timeZone.contains("+")){
            timeMs+(hours+minutes)
        }else{
            timeMs-(hours+minutes)
        }
        return timeFinal
    }

      fun returnGMTTimeInMs(time: String): Long {
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        return sdf.parse(time).time - MainAdapter.GMT_OFFSET_IN_MS
    }

      fun returnStateDate(dataObject:Match):String{
        val sdf=SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        val sdf2=SimpleDateFormat("EEE, dd MMM")
        return when(dataObject.state){
            0->{
                sdf2.format(sdf.parse(dataObject.matchTime).time)
            }
            1->{
                "FH "+returnMinutes(dataObject)+" \'"
            }
            2->{
                "HT "+returnMinutes(dataObject)+" \'"
            }
            3->{
                "SH "+returnMinutes(dataObject)+" \'"
            }
            4-> {
                "OT "+returnMinutes(dataObject)+" \'"
            }
            5-> {
                "PT "+returnMinutes(dataObject)+" \'"
            }
            -1-> {
                "FT"
            }
            -10-> {
                "CL"
            }
            -11-> {
                "TBD"
            }
            -12-> {
                "CIH"
            }
            -13-> {
                "INT"
            }
            -14-> {
                "DEL"
            }
            else -> {
                "Soon"
            }
        }
    }


}