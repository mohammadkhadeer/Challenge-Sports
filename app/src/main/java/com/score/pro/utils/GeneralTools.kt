package com.score.pro.utils

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.score.pro.model.data.homepage.new2.Match
import com.score.pro.view.fragments.homeFrags.adapter.MainAdapter
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.score.pro.sharedPreferences.PromptFrequency.getPrompt_messageFromSP
import com.score.pro.sharedPreferences.PromptFrequency.getPrompt_titleFromSP
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.score.pro.sharedPreferences.FootballOrBasketball.cleanFootballOrBasketball
import sports.myapplication.BuildConfig
import sports.myapplication.R
import java.text.SimpleDateFormat
import java.util.*


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
            datesList.add(getCalculatedDate("yyyy-MM-dd",i))
//            datesList.add(getCalculatedDate("yyyy-MM-dd",i))
        }
        return datesList
    }
    fun getFutureDates():List<String>{
        val datesList=ArrayList<String>()
        for (i in 1 .. 8){
            datesList.add(getCalculatedDate("yyyy-MM-dd",i))
//            datesList.add(getCalculatedDate("yyyy-MM-dd",i))
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

    fun getLocale(context: Context): String {
       return SharedPreference.getInstance().getStringValueFromPreference(SharedPreference.LOCALE_KEY,SharedPreference.ENGLISH,context)
    }
    fun setLocale(context: Context,locale:String) {
        SharedPreference.getInstance().saveStringToPreferences(SharedPreference.LOCALE_KEY,locale,context)
    }

    fun exitDialog(activity:Activity){
        val dialog=Dialog(activity,android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.exit_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<View>(R.id.yes_bt).setOnClickListener {
            cleanFootballOrBasketball(activity)
            activity.finish()
        }
        dialog.findViewById<View>(R.id.no_bt).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun messageDialog(activity:Activity){

        val dialog=Dialog(activity,android.R.style.ThemeOverlay)
        dialog.setContentView(R.layout.popup_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<TextView>(R.id.title_message).setText(getPrompt_titleFromSP(activity.applicationContext))
        dialog.findViewById<TextView>(R.id.message).setText(getPrompt_messageFromSP(activity.applicationContext))

        dialog.findViewById<View>(R.id.ok_bt).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun shareApp(activity: Activity){
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                activity.getResources().getString(R.string.app_name).toString() + " App"
            )
            var shareMessage = """
                 
                 Let me recommend you this application
                 
                 
                 """.trimIndent()
            shareMessage =
                """
                 ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                 
                 
                 """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            activity.startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun feedback(activity: Activity){

        val i = Intent(Intent.ACTION_SEND)
        i.type = "*/*"
        // i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(crashLogFile));
        // i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(crashLogFile));
        i.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf<String>(activity.getResources().getString(R.string.account_email))
        )
        i.putExtra(
            Intent.EXTRA_SUBJECT,
            activity.getResources().getString(R.string.app_name).toString() + " FeedBack"
        )

        try {
            activity.startActivity(createEmailOnlyChooserIntent(activity,i, "Send via email"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                activity,
                "There is no email client installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun createEmailOnlyChooserIntent(
        activity: Activity,
        source: Intent?,
        chooserTitle: CharSequence?
    ): Intent? {
        val intents = Stack<Intent>()
        val i = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",
                "", null
            )
        )
        val activities: List<ResolveInfo> =activity.getPackageManager()
            .queryIntentActivities(i, 0)
        for (ri in activities) {
            val target = Intent(source)
            target.setPackage(ri.activityInfo.packageName)
            intents.add(target)
        }
        return if (!intents.isEmpty()) {
            val chooserIntent = Intent.createChooser(
                intents.removeAt(0),
                chooserTitle
            )
            chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS,
                intents.toTypedArray<Parcelable>()
            )
            chooserIntent
        } else {
            Intent.createChooser(source, chooserTitle)
        }
    }

    fun privacyPolicy(context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.privacy_policy_link)))
        context.startActivity(browserIntent)
    }

    fun rateUs(activity: Activity) {

        val appPackageName: String = activity.getPackageName()
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri
                        .parse("market://details?id=$appPackageName")
                )
            )
        } catch (anfe: ActivityNotFoundException) {
           activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "http://play.google.com/store/apps/details?id="
                                + appPackageName
                    )
                )
            )
        }
    }
}