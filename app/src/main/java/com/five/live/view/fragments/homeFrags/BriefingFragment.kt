package com.five.live.view.fragments.homeFrags

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
import sports.myapplication.R
import com.five.live.utils.SpewViewModel
import com.five.live.utils.Status
import com.five.live.view.fragments.homeFrags.adapter.MainAdapterCommunicator


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
            vm.basketballBriefingLiveData.observe(requireActivity()){
                when(it.status){
                    Status.SUCCESS -> {
                        view.findViewById<View>(R.id.baskteball_briefing).visibility=View.VISIBLE
                        val briefinBody=view.findViewById<TextView>(R.id.briefing_body)
                        val descriptionBody=view.findViewById<TextView>(R.id.description_body)
                        val confidenceIndex=view.findViewById<TextView>(R.id.confidence_index_body)
                        val matchTrackRecord=view.findViewById<TextView>(R.id.match_track_body)
                        it.data?.apply {
                            briefinBody.text=analyseEn
                            descriptionBody.text=explainEn
                            confidenceIndex.text=confidenceEn
                            matchTrackRecord.text=headToHeadEn
                        }
                    }
                    Status.ERROR -> {
                        println(it.message)
                    }
                    Status.LOADING -> {
                        view.findViewById<View>(R.id.baskteball_briefing).visibility=View.GONE
                    }
                }
            }
            vm.makeBasketBallBriefingCall(matchId!!)
        }else{
            view.findViewById<View>(R.id.baskteball_briefing).visibility=View.GONE
            vm.briefingLiveData.observe(requireActivity()){
                when(it.status){
                    Status.SUCCESS ->{

                        //     briefTv.visibility=View.VISIBLE
                        val data = it.data
                        val webview = view.findViewById<WebView>(R.id.briefing_container_tv)
                        webview.visibility=View.VISIBLE
                        webview.webViewClient = WebViewClient()
                        webview.settings.javaScriptCanOpenWindowsAutomatically = true
                        webview.settings.pluginState = WebSettings.PluginState.ON
                        webview.settings.mediaPlaybackRequiresUserGesture = false
                        webview.webChromeClient = WebChromeClient()
                        webview.loadDataWithBaseURL(null, data!!, "text/html", "UTF-8", null)
                        /*    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                briefTv.text= Html.fromHtml(it.data,Html.FROM_HTML_MODE_COMPACT)
                            }else{
                                briefTv.text= Html.fromHtml(it.data)
                            }*/
                    }
                    Status.ERROR ->{

                    }
                    Status.LOADING -> {
                        view.findViewById<WebView>(R.id.briefing_container_tv).visibility=View.GONE
                    }
                }
            }
            vm.getBriefing(matchId!!)
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