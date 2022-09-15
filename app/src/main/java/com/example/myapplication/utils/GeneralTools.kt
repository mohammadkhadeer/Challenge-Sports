package com.example.myapplication.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.widget.Toast


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

}