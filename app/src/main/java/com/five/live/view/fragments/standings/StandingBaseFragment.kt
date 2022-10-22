package com.five.live.view.fragments.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.five.live.view.BaseActivity
import sports.myapplication.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StandingBaseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
//    private var selectedLeague= PREMIERE_LEAGUE
    var leaguesList= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val language_rl = view.findViewById<RelativeLayout>(R.id.language_rl)
        val privacy_policy_rl = view.findViewById<RelativeLayout>(R.id.privacy_policy_rl)
        val share_app_rl = view.findViewById<RelativeLayout>(R.id.share_app_rl)
        val feed_back_rl = view.findViewById<RelativeLayout>(R.id.feed_back_rl)
        val rate_us_rl = view.findViewById<RelativeLayout>(R.id.rate_us_rl)
        val exit_app_rl = view.findViewById<RelativeLayout>(R.id.exit_app_rl)

        language_rl.setOnClickListener{
            (activity as BaseActivity?)?.showDialogForLanguages()
        }

        privacy_policy_rl.setOnClickListener(View.OnClickListener {
            (activity as BaseActivity?)?.getToPrivacyPolicy()
        })

        share_app_rl.setOnClickListener(View.OnClickListener {
            (activity as BaseActivity?)?.goToShareApp()
        })

        feed_back_rl.setOnClickListener(View.OnClickListener {
            (activity as BaseActivity?)?.goToFeedBack()
        })

        rate_us_rl.setOnClickListener(View.OnClickListener {
            (activity as BaseActivity?)?.goToRateUs()
        })

        exit_app_rl.setOnClickListener(View.OnClickListener {
            (activity as BaseActivity?)?.exatApp()
        })
    }

//    private fun showDialogForLanguages() {
//        var shouldRefresh=false
//        val dialog= Dialog(this,android.R.style.ThemeOverlay)
//        dialog.setContentView(R.layout.language_dialog)
//        val english=dialog.findViewById<View>(R.id.english)
//        val chinese=dialog.findViewById<View>(R.id.chinese)
//        val indonesian=dialog.findViewById<View>(R.id.indonesian)
//        val vietnam=dialog.findViewById<View>(R.id.vietnamese)
//        val thai=dialog.findViewById<View>(R.id.thai)
//
//        val english_text=dialog.findViewById<TextView>(R.id.english_text)
//        val chinese_text=dialog.findViewById<TextView>(R.id.chinese_text)
//        val indonesian_text=dialog.findViewById<TextView>(R.id.indonesian_text)
//        val vietnamese_text=dialog.findViewById<TextView>(R.id.vietnamese_text)
//        val thai_text=dialog.findViewById<TextView>(R.id.thai_text)
//
//        dialog.findViewById<View>(R.id.back_btn).setOnClickListener {
//            dialog.dismiss()
//            if (shouldRefresh)
//                recreate()
//        }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//
//        val leagueSelector=view.findViewById<TextView>(R.id.league_selector)
//
//        val spinnerLeague=view.findViewById<Spinner>(R.id.league_selector_dropdown)
//        leaguesList.add(requireContext().getString(R.string.premiere_league_string))
//        leaguesList.add(requireContext().getString(R.string.la_liga_string))
//        leaguesList.add(requireContext().getString(R.string.serie_a_string))
//        leaguesList.add(requireContext().getString(R.string.bundesliga_string))
//        leaguesList.add(requireContext().getString(R.string.ligue_1))
//        leaguesList.add(requireContext().getString(R.string.chinese_super_league_string))
//        leaguesList.add(requireContext().getString(R.string.afc_champions_league_string))
//        leaguesList.add(requireContext().getString(R.string.asian_qual_string))
//        leaguesList.add(requireContext().getString(R.string.south_amer_qual_string))
//        leaguesList.add(requireContext().getString(R.string.european_qualifier_string))
//        leaguesList.add(requireContext().getString(R.string.world_cup_string))
//        leaguesList.add(requireContext().getString(R.string.european_cup_string))
//        leaguesList.add(requireContext().getString(R.string.confederation_cup))
//        leaguesList.add(requireContext().getString(R.string.americas_cup))
//        spinnerLeague.adapter= ArrayAdapter(requireContext(),R.layout.simple_spinner_item,leaguesList)
//        spinnerLeague.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                selectedLeague= returnLeagueForType(requireContext(),leaguesList[p2])
//                populateViewpager(view)
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//
//            }
//        }
//       populateViewpager(view)
//
//    }
//
//    fun populateViewpager(view: View){
//        val viewpager=view.findViewById<ViewPager2>(R.id.standings_viewpager)
//        val frags=ArrayList<Fragment>()
//        val fragsTitles=ArrayList<String>()
//        fragsTitles.add(getString(R.string.team_standing))
//        fragsTitles.add(getString(R.string.player_standing))
//        frags.add(StandingDetailFragment.newInstance(selectedLeague.toString(),true))
//        frags.add(StandingDetailFragment.newInstance(selectedLeague.toString(),false))
//        val tabLayout=view.findViewById<TabLayout>(R.id.tab_layout_standings)
//        viewpager.adapter=ViewPagerAdapter(requireActivity().supportFragmentManager,requireActivity().lifecycle,frags)
//        TabLayoutMediator(tabLayout,viewpager){ tab,position->
//            tab.text =fragsTitles[position]
//        }.attach()
//    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StandingBaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

//        const val PREMIERE_LEAGUE=36
//        const val LA_LIGA=31
//        const val SERIE_A=34
//        const val BUNDESLIGA=8
//        const val LIGUE_1=11
//        const val CHINESE_SUPER_LEAGUE=60
//        const val AFC_CHAMPIONS_LEAGUE=192
//        const val ASIAN_QUALIFIERS=648
//        const val SOUTH_AMERICAN_QUALIFIER=652
//        const val EUROPEAN_QUALIFIER=650
//        const val WORLD_CUP=75
//        const val EUROPEAN_CUP=67
//        const val CONFEDERATIONS_CUP=88
//        const val AMERICAS_CUP=224
//
//        fun returnLeagueForType(context:Context,type:String):Int{
//            return when(type){
//                context.getString(R.string.premiere_league_string)->{
//                    PREMIERE_LEAGUE
//                }  context.getString(R.string.la_liga_string)->{
//                    LA_LIGA
//                }  context.getString(R.string.serie_a_string)->{
//                    SERIE_A
//                }  context.getString(R.string.bundesliga_string)->{
//                    BUNDESLIGA
//                }  context.getString(R.string.ligue_1)->{
//                    LIGUE_1
//                }  context.getString(R.string.chinese_super_league_string)->{
//                    CHINESE_SUPER_LEAGUE
//                }  context.getString(R.string.afc_champions_league_string)->{
//                    AFC_CHAMPIONS_LEAGUE
//                }  context.getString(R.string.asian_qual_string)->{
//                    ASIAN_QUALIFIERS
//                }  context.getString(R.string.south_amer_qual_string)->{
//                    SOUTH_AMERICAN_QUALIFIER
//                }  context.getString(R.string.european_qualifier_string)->{
//                    EUROPEAN_QUALIFIER
//                }  context.getString(R.string.world_cup_string)->{
//                    WORLD_CUP
//                }  context.getString(R.string.european_cup_string)->{
//                    EUROPEAN_CUP
//                }  context.getString(R.string.confederation_cup)->{
//                    CONFEDERATIONS_CUP
//                }  context.getString(R.string.americas_cup)->{
//                    AMERICAS_CUP
//                }
//                else -> {
//                    PREMIERE_LEAGUE
//                }
//            }
//        }

    }

}