package com.challenge.sports.utils

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
import android.widget.Toast
import com.challenge.sports.model.data.matchStatus.MatchStatusJ
import com.challenge.sports.sharedPreferences.FootballOrBasketball.cleanFootballOrBasketball
import com.challenge.sports.sharedPreferences.OpenWebView
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import score.pro.BuildConfig
import score.pro.R
import java.text.SimpleDateFormat
import java.util.*


object GeneralTools {

    fun  fillMatchesStatus(context: Context):ArrayList<MatchStatusJ>{

        var match_status_list: ArrayList<MatchStatusJ> = ArrayList()


        match_status_list.add(MatchStatusJ(context.getString(R.string.hot), true))
        match_status_list.add(MatchStatusJ(context.getString(R.string.live), false))
        match_status_list.add(MatchStatusJ(context.getString(R.string.up_coming), false))
        match_status_list.add(MatchStatusJ(context.getString(R.string.finish), false))
//        match_status_list.add(context.getString(R.string.hot))
//        match_status_list.add(context.getString(R.string.live))
//        match_status_list.add(context.getString(R.string.up_coming))
//        match_status_list.add(context.getString(R.string.finish))

        return match_status_list
    }

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

//    fun flipReplaceAnimation(toBeReplaced:View,toReplaceWith:View){
//        YoYo.with(Techniques.FlipOutX)
//            .apply {
//                duration(200)
//                withListener(object : Animator.AnimatorListener{
//                    override fun onAnimationStart(animation: Animator) {
//
//                    }
//
//                    override fun onAnimationEnd(p0: Animator?) {
//                        toBeReplaced.visibility=View.GONE
//                        toReplaceWith.visibility=View.VISIBLE
//                        YoYo.with(Techniques.FlipInX)
//                            .apply {
//                                duration(200)
//                                playOn(toReplaceWith)
//                            }
//                    }
//
//                    override fun onAnimationCancel(p0: Animator?) {
//
//                    }
//
//                    override fun onAnimationRepeat(p0: Animator?) {
//
//                    }
//                })
//                playOn(toBeReplaced)
//            }
//    }



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


    fun getLocale(context: Context): String {
       return SharedPreference.getInstance().getStringValueFromPreference(SharedPreference.LOCALE_KEY,SharedPreference.CHINESE,context)
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

//    fun messageDialog(activity:Activity){
//        val dialog=Dialog(activity,android.R.style.ThemeOverlay)
//        dialog.setContentView(R.layout.popup_dialog)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        dialog.findViewById<TextView>(R.id.title_message).setText(prompt_title)
//        dialog.findViewById<TextView>(R.id.message).setText(prompt_message)
//
//
//        dialog.findViewById<View>(R.id.pro_fre).setOnClickListener {
//            openWebView(activity)
//        }
//
//        dialog.findViewById<View>(R.id.ok_bt).setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }

//    public fun openWebView(activity:Activity) {
//        if (open_type.equals("1")) {
//            (activity as BaseActivity?)?.showDialogWebView(redirect_url)
//        }else{
//            openAdsOnBrowser(activity.applicationContext,redirect_url)
//        }
//    }


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

    fun openBrowser(context: Context,url:String,open_type:String,from_where:String) {
        if (!url.isNullOrEmpty())
        {
            if (!from_where.equals("from_init"))
                OpenWebView.saveWebViewOnOrOffInSP(context, "on")

            OpenWebView.saveLastWebLinkOpenedOnSP(context, url,open_type)

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(browserIntent)
        }
    }

    fun privacyPolicy(context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.privacy_policy_link)))
        context.startActivity(browserIntent)
    }

    fun openAdsOnBrowser(context: Context,url:String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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