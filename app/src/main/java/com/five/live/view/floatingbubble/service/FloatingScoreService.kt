package com.five.live.view.floatingbubble.service

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sports.myapplication.R
import com.five.live.model.api.ApiHelperImpl
import com.five.live.model.api.RetroInstance
import com.five.live.model.data.homepage.event.EventBase
import com.five.live.model.data.livescorepin.Match
import com.five.live.utils.Formatters
import com.five.live.utils.SharedPreference
import com.five.live.view.fragments.homeFrags.adapter.EventsAdapter
import com.five.live.view.fragments.homeFrags.adapter.MainAdapter
import com.five.live.view.fragments.homeFrags.adapter.TechnicAdapter
import com.torrydo.floatingbubbleview.ExpandableView
import com.torrydo.floatingbubbleview.FloatingBubble
import com.torrydo.floatingbubbleview.FloatingBubbleService
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


class FloatingScoreService:FloatingBubbleService() {
    private var countdownTimer: CountDownTimer?=null
    var matchId:String?=null
    var bubble:FloatingBubble.Builder?=null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        matchId=intent?.getStringExtra(SharedPreference.MATCH_ID_TOKEN)
        return super.onStartCommand(intent, flags, startId)
    }
    override fun setupBubble(action: FloatingBubble.Action): FloatingBubble.Builder {
        val display = (this.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val bubble= FloatingBubble.Builder()
            .with(this)
            .setIcon(R.drawable.new_app_icon)
            .setRemoveIcon(R.drawable.ic_cross)
            .addFloatingBubbleTouchListener(object : FloatingBubble.TouchEvent {
                override fun onDestroy() {
                    println("on Destroy")
                }

                override fun onClick() {
                    action.navigateToExpandableView();
                }

                override fun onMove(x: Int, y: Int) {
                    println("onMove")
                }

                override fun onUp(x: Int, y: Int) {
                    println("onUp")
                }

                override fun onDown(x: Int, y: Int) {
                    println("onDown")
                }
            })
            .setBubbleSizeDp(60)
            .setStartPoint(200,0)
            .setAlpha(1f)



        this.bubble=bubble

        return bubble
    }
    override fun setupExpandableView(action: ExpandableView.Action): ExpandableView.Builder {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.layout_match_pin, null)

      /*  layout.findViewById<View>(R.id.card_view).setOnClickListener { view ->
            action.popToBubble()
        }*/


        countdownTimer=object : CountDownTimer(200000,2000){
            override fun onTick(p0: Long) {
                try {
                    updateData(view)
                }catch (e:Exception){
                    cancel()
                }

            }

            override fun onFinish() {
            }

        }.start()


        view.findViewById<View>(R.id.close_popup).setOnClickListener {
            action.popToBubble()
        }
        return ExpandableView.Builder()
            .with(this)
            .setExpandableView(view)
            .setDimAmount(0.8f)
    }

    private fun populateEvent(view: View, event: EventBase) {

        val technicRv=view.findViewById<RecyclerView>(R.id.technic_rv)
        val eventsRv=view.findViewById<RecyclerView>(R.id.event_rv)
        val eventList= Formatters.filterEvents(matchId!!, event)
        val formattedEvents= Formatters.formatEvents(matchId!!,eventList)
        eventsRv.adapter= EventsAdapter(this,formattedEvents)
        eventsRv.layoutManager= LinearLayoutManager(this)
        val technicList= Formatters.formatTechnic(Formatters.filterEvents(matchId!!, event).technic[0].technicCount)
        technicRv.adapter= TechnicAdapter(this,technicList)
        technicRv.layoutManager= LinearLayoutManager(this)

    }






    private fun return24HrsOnly(matchTime: String): String {
        val splitDateTime = matchTime.split(" ")[1].split(":")
        return splitDateTime[0] + ":" + splitDateTime[1]
    }


    private fun returnTime(dataObject: Match): String {
        var sdf: SimpleDateFormat? =null
        try{
            sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        }catch(e: ArithmeticException){
            println(e)
        }
        val time = returnGMTToCurrentTimezone(returnGMTTimeInMs(dataObject.matchTime))
        return sdf?.format(time) ?: ""
    }

    private fun returnTimeMs(time: String): Long {
        var sdf: SimpleDateFormat? =null
        try{
            sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        }catch(e: ArithmeticException){
            println(e)
        }
        return (sdf?.parse(time)?.time ?: "") as Long
    }

    private fun returnMinutes(dataObject: Match): String {
        val startTimeMs = returnGMTTimeInMs(dataObject.startTime)
        val matchTimeMs = returnGMTTimeInMs(dataObject.matchTime)
        var minutesElapsed = returnTimeInGmtCurrentLocale() - startTimeMs
        minutesElapsed /= 60000
        return minutesElapsed.toString()
    }

    private fun returnTimeInGmtCurrentLocale(): Long {
        val timeZone = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val time = timeZone.substringAfter("+")
        var hours = time.substringBefore(":").toLong()
        var minutes = time.substringAfter(":").toLong()
        hours *= MainAdapter.HOUR_CONSTANT
        minutes *= MainAdapter.MINS_CONSTANT
        val timeFinal = if (timeZone.contains("+")) {
            System.currentTimeMillis() - (hours + minutes)
        } else {
            System.currentTimeMillis() + (hours + minutes)
        }
        return timeFinal
    }

    private fun returnGMTToCurrentTimezone(timeMs: Long): Long {

        val timeZone = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val time = timeZone.substringAfter("+")
        var hours = time.substringBefore(":").toLong()
        var minutes = time.substringAfter(":").toLong()
        hours *= MainAdapter.HOUR_CONSTANT
        minutes *= MainAdapter.MINS_CONSTANT
        val timeFinal = if (timeZone.contains("+")) {
            timeMs + (hours + minutes)
        } else {
            timeMs - (hours + minutes)
        }
        return timeFinal
    }

    private fun returnGMTTimeInMs(time: String): Long {
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        return sdf.parse(time).time - MainAdapter.GMT_OFFSET_IN_MS
    }

    private fun returnStateDate(dataObject: Match): String {
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        val sdf2 = SimpleDateFormat("EEE, dd MMM")
        return when (dataObject.state) {
            0 -> {
                sdf2.format(sdf.parse(dataObject.matchTime).time)
            }
            1 -> {
                "FH " + returnMinutes(dataObject) + " \'"
            }
            2 -> {
                "HT " + returnMinutes(dataObject) + " \'"
            }
            3 -> {
                "SH " + returnMinutes(dataObject) + " \'"
            }
            4 -> {
                "OT " + returnMinutes(dataObject) + " \'"
            }
            5 -> {
                "PT " + returnMinutes(dataObject) + " \'"
            }
            -1 -> {
                "FT"
            }
            -10 -> {
                "CL"
            }
            -11 -> {
                "TBD"
            }
            -12 -> {
                "CIH"
            }
            -13 -> {
                "INT"
            }
            -14 -> {
                "DEL"
            }
            else -> {
                "Soon"
            }
        }
    }

    override fun onDestroy() {
       // countdownTimer?.cancel()
        super.onDestroy()
    }

    fun populateMatch(view:View,match:com.five.live.model.data.livescorepin.Match){
        try {
            var leagueNameShort = view.findViewById<TextView>(R.id.group_indicator)
            var homeNameTv = view.findViewById<TextView>(R.id.team_1_name)
            var awayNameTv = view.findViewById<TextView>(R.id.team_2_name)
            var scoreIndicator = view.findViewById<TextView>(R.id.score_indicator)
            var stateNdate = view.findViewById<TextView>(R.id.match_date)
            var matchTimeTv = view.findViewById<TextView>(R.id.match_time)
            var indexC1R1 = view.findViewById<TextView>(R.id.index_c1_r1)
            var indexC1R2 = view.findViewById<TextView>(R.id.index_c1_r2)
            var indexC2R1 = view.findViewById<TextView>(R.id.index_c2_r1)
            var indexC2R2 = view.findViewById<TextView>(R.id.index_c2_r2)
            var indexC3R1 = view.findViewById<TextView>(R.id.index_c3_r1)
            var indexC3R2 = view.findViewById<TextView>(R.id.index_c3_r2)
            var cornerRatio = view.findViewById<TextView>(R.id.c_ratio)
            var halfRatio = view.findViewById<TextView>(R.id.ht_ratio)
            var index_btn = view.findViewById<View>(R.id.index_bt)
            leagueNameShort.text = match?.leagueNameShort

            homeNameTv.text = match?.homeName
            awayNameTv.text = match?.awayName
            if (match?.state == 0) {
                scoreIndicator.text = this.getString(R.string.soon)

            } else {
                scoreIndicator.text =
                    match?.homeScore.toString() + " : " + match?.awayScore.toString()
            }


            if (!match?.startTime.isNullOrEmpty()) {
                // "matchTime": "2022/8/25 16:30:00",
                try {
                    stateNdate.text = returnStateDate(match!!)
                } catch (e: Exception) {
                    Log.d("EXCEPTION!!!", e.toString())

                }
            } else {
                try {
                    val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
                    val sdf2 = SimpleDateFormat("EEE, dd MMM")
                    stateNdate.text = sdf2.format(sdf.parse(match?.matchTime).time)
                } catch (e: Exception) {

                }

            }
            matchTimeTv.text = return24HrsOnly(returnTime(match!!))

            if (match?.havOdds!!) {
                try {
                    indexC1R1.text = match?.odds?.handicap!![6].toString()
                    indexC2R1.text = match?.odds?.handicap!![5].toString()
                    indexC3R1.text = match?.odds?.handicap!![7].toString()
                    indexC1R2.text = match?.odds?.overUnder!![6].toString()
                    indexC2R2.text = match?.odds?.overUnder!![5].toString()
                    indexC3R2.text = match?.odds?.overUnder!![7].toString()
                    cornerRatio.text =
                        match?.homeCorner.toString() + ":" + match?.awayCorner.toString()
                    halfRatio.text =
                        match?.homeHalfScore.toString() + ":" + match?.awayHalfScore.toString()
                } catch (e: Exception) {

                }
            }
            //          inflateFragment(returnUsableFragment(), R.id.detailFragContainer)

        } catch (e: Exception) {

        }
    }
    fun updateData(view:View){
        val api=ApiHelperImpl(RetroInstance.apiService)
        val runThis=GlobalScope.async {
            val event= async {
                api.getEvents()
            }
            val match=async {
                api.getMatchUpdate(matchId!!).matchList[0]
            }
            withContext(Dispatchers.Main){
                populateMatch(view,match.await())
                populateEvent(view,event.await())
            }

        }
    }

}