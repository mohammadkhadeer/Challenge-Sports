package com.challenge.sports.view.fragments.homeFrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import score.pro.R
import com.challenge.sports.utils.SpewViewModel
import com.challenge.sports.utils.Status
import com.challenge.sports.view.fragments.homeFrags.adapter.MainAdapterCommunicator


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BriefingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BriefingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var matchId: String? = null
    private var adapterType: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            matchId = it.getString(ARG_PARAM1)
            adapterType = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_briefing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm=SpewViewModel.giveMeViewModel(requireActivity())
       // val briefTv=view.findViewById<TextView>(R.id.briefing_container_tv)
        if (adapterType==MainAdapterCommunicator.BASKETBALL_TYPE){


        }else{
            view.findViewById<View>(R.id.baskteball_briefing).visibility=View.GONE


        }



    }

    companion object {
        @JvmStatic
        fun newInstance(matchId: String, adapterType: Int) =
            BriefingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, matchId)
                    putInt(ARG_PARAM2, adapterType)
                }
            }
    }
}