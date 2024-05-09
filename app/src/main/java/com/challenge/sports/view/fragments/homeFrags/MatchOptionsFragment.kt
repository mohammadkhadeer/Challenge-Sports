package com.challenge.sports.view.fragments.homeFrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import score.pro.R
import com.challenge.sports.utils.GeneralTools
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapter
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapterCommunicator
import com.challenge.sports.view.fragments.homeFrags.detailFragment.AnalysisFragment
import com.challenge.sports.view.fragments.homeFrags.detailFragment.EventFragment
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

class MatchOptionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var matchId: String? = null
    private var type: String? = null
    private var adapterType:Int?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            matchId = it.getString(ARG_PARAM1)
            type = it.getString(ARG_PARAM2)
            adapterType=it.getInt(ARG_PARAM3)
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


    }

    private fun inflateFragment(fragment: Fragment, resourceId: Int) {
        val ft = requireActivity().supportFragmentManager.beginTransaction()
        ft.replace(R.id.detailFragContainer, fragment)
        ft.commit()
        view?.findViewById<View>(resourceId)?.visibility = View.VISIBLE
    }


    private fun return24HrsOnly(matchTime: String): String {
        val splitDateTime = matchTime.split(" ")[1].split(":")
        return splitDateTime[0] + ":" + splitDateTime[1]
    }




    private fun returnTimeMs(time: String): Long {
        val sdf = SimpleDateFormat("yyyy/M/dd HH:mm:ss")
        return sdf.parse(time).time
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


    companion object {
        @JvmStatic
        fun newInstance(matchId: String, type: String, adapterType: Int) =
            MatchOptionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, matchId)
                    putString(ARG_PARAM2, type)
                    putInt(ARG_PARAM3,adapterType)
                }
            }

        const val INDEX_TYPE = "index_frag"
        const val ANALYSIS_TYPE = "analysis_frag"
        const val EVENT_TYPE = "event_type"
        const val BRIEFING_FRAGMENT = "brief_frag"
        const val LEAGUE_FRAGMENT = "league_fragment"
    }
}