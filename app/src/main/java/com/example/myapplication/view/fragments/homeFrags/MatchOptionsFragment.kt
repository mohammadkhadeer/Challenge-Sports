package com.example.myapplication.view.fragments.homeFrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.model.data.homepage.new2.Match
import com.example.myapplication.utils.GeneralTools
import com.example.myapplication.view.fragments.homeFrags.adapter.MainAdapter
import com.example.myapplication.view.fragments.homeFrags.detailFragment.AnalysisFragment
import com.example.myapplication.view.fragments.homeFrags.detailFragment.EventFragment
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MatchOptionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var matchId: String? = null
    private var type: String? = null
    private var match: Match?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            matchId = it.getString(ARG_PARAM1)
            type = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var leagueNameShort=view.findViewById<TextView>(R.id.group_indicator)
        var homeNameTv=view.findViewById<TextView>(R.id.team_1_name)
        var awayNameTv=view.findViewById<TextView>(R.id.team_2_name)
        var scoreIndicator=view.findViewById<TextView>(R.id.score_indicator)
        var stateNdate=view.findViewById<TextView>(R.id.match_date)
        var matchTimeTv=view.findViewById<TextView>(R.id.match_time)
        var indexC1R1=view.findViewById<TextView>(R.id.index_c1_r1)
        var indexC1R2=view.findViewById<TextView>(R.id.index_c1_r2)
        var indexC2R1=view.findViewById<TextView>(R.id.index_c2_r1)
        var indexC2R2=view.findViewById<TextView>(R.id.index_c2_r2)
        var indexC3R1=view.findViewById<TextView>(R.id.index_c3_r1)
        var indexC3R2=view.findViewById<TextView>(R.id.index_c3_r2)
        var cornerRatio=view.findViewById<TextView>(R.id.c_ratio)
        var halfRatio=view.findViewById<TextView>(R.id.ht_ratio)
        var index_btn=view.findViewById<View>(R.id.index_bt)
        leagueNameShort.text=match?.leagueNameShort

        homeNameTv.text=match?.homeName
        awayNameTv.text=match?.awayName
        if (match?.state==0){
            scoreIndicator.text=context?.getString(R.string.soon)
            view.findViewById<View>(R.id.set_reminder).setOnClickListener {
                GeneralTools.setAlarmFor(requireContext(),returnTimeMs(returnTime(match!!)))
                Toast.makeText(requireContext(),"Alarm Set for"+returnTimeMs(returnTime(match!!))+" : "+returnTime(match!!),Toast.LENGTH_LONG).show()
            }

        }else{
            scoreIndicator.text=match?.homeScore.toString() + " : "+match?.awayScore.toString()
        }


        if (!match?.startTime.isNullOrEmpty()){
            // "matchTime": "2022/8/25 16:30:00",
            try {
                stateNdate.text= returnStateDate(match!!)
            }
            catch (e:Exception){
                Log.d("EXCEPTION!!!",e.toString())

            }
        }
        else{
            try {
                val sdf= SimpleDateFormat("yyyy/M/dd HH:mm:ss")
                val sdf2= SimpleDateFormat("EEE, dd MMM")
                stateNdate.text= sdf2.format(sdf.parse(match?.matchTime).time)
            }catch (e:Exception){

            }

        }
        matchTimeTv.text=return24HrsOnly(returnTime(match!!))

        if (match?.havOdds!!){
            try {
                indexC1R1.text= match?.odds?.handicap!![6].toString()
                indexC2R1.text=match?.odds?.handicap!![5].toString()
                indexC3R1.text=match?.odds?.handicap!![7].toString()
                indexC1R2.text= match?.odds?.overUnder!![6].toString()
                indexC2R2.text=match?.odds?.overUnder!![5].toString()
                indexC3R2.text=match?.odds?.overUnder!![7].toString()
                cornerRatio.text=match?.homeCorner.toString()+":"+match?.awayCorner.toString()
                halfRatio.text=match?.homeHalfScore.toString()+":"+match?.awayHalfScore.toString()
            }
            catch (e:Exception){

            }
        }
        inflateFragment(returnUsableFragment(),R.id.detailFragContainer)

    }
    private fun inflateFragment(fragment: Fragment, resourceId: Int) {
        val ft=requireActivity().supportFragmentManager.beginTransaction()
        ft.replace(R.id.detailFragContainer,fragment)
        ft.commit()
        view?.findViewById<View>(resourceId)?.visibility=View.VISIBLE
    }
    private fun returnUsableFragment():Fragment{
        return when(type){
            INDEX_TYPE->{
                IndexDisplayFragmnet.newInstance(matchId!!,"")
            }
            ANALYSIS_TYPE->{
                AnalysisFragment.newInstance(matchId!!,"")

            }
            EVENT_TYPE->{
                EventFragment.newInstance(matchId!!,"")
            }
            BRIEFING_FRAGMENT->{
                BriefingFragment.newInstance(matchId!!,"")
            }
            else -> {
                AnalysisFragment.newInstance(matchId!!,"")
            }
        }
    }

    private fun return24HrsOnly(matchTime:String):String{
        val splitDateTime=matchTime.split(" ")[1].split(":")
        return splitDateTime[0]+":"+splitDateTime[1]
    }


    private fun returnTime(dataObject: Match):String{
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        val time=returnGMTToCurrentTimezone(returnGMTTimeInMs(dataObject.matchTime))
        return sdf.format(time)
    }
    private fun returnTimeMs(time:String):Long{
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        return sdf.parse(time).time
    }

    private fun returnMinutes(dataObject: Match):String
    {
        val startTimeMs=returnGMTTimeInMs(dataObject.startTime)
        val matchTimeMs=returnGMTTimeInMs(dataObject.matchTime)
        var minutesElapsed=returnTimeInGmtCurrentLocale()-startTimeMs
        minutesElapsed /= 60000
        return minutesElapsed.toString()
    }

    private fun returnTimeInGmtCurrentLocale():Long{
        val timeZone= TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
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
    private fun returnGMTToCurrentTimezone(timeMs:Long):Long{

        val timeZone= TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
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

    private fun returnGMTTimeInMs(time: String): Long {
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        return sdf.parse(time).time - MainAdapter.GMT_OFFSET_IN_MS
    }

    private fun returnStateDate(dataObject:Match):String{
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


    fun setData(match:Match){
        this.match=match
    }

    companion object {
        @JvmStatic
        fun newInstance(matchId: String, type: String) =
            MatchOptionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, matchId)
                    putString(ARG_PARAM2, type)
                }
            }
        const val INDEX_TYPE="index_frag"
        const val ANALYSIS_TYPE= "analysis_frag"
        const val EVENT_TYPE= "event_type"
        const val BRIEFING_FRAGMENT= "brief_frag"
    }
}