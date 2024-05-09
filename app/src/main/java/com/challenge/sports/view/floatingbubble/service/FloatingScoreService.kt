package com.challenge.sports.view.floatingbubble.service

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
import score.pro.R
import com.challenge.sports.model.api.ApiHelperImpl
import com.challenge.sports.model.api.RetroInstance
import com.challenge.sports.utils.Formatters
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.view.fragments.homeFrags.adapter.EventsAdapter
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapter
import com.challenge.sports.view.fragments.homeFrags.adapter.TechnicAdapter
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
//                    updateData(view)
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

    private fun populateEvent(view: View) {
        val technicRv=view.findViewById<RecyclerView>(R.id.technic_rv)
        val eventsRv=view.findViewById<RecyclerView>(R.id.event_rv)
        eventsRv.layoutManager= LinearLayoutManager(this)
        technicRv.layoutManager= LinearLayoutManager(this)
    }

    private fun return24HrsOnly(matchTime: String): String {
        val splitDateTime = matchTime.split(" ")[1].split(":")
        return splitDateTime[0] + ":" + splitDateTime[1]
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

    override fun onDestroy() {
       // countdownTimer?.cancel()
        super.onDestroy()
    }


}