package com.example.myapplication.utils

import android.animation.Animator
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


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

}