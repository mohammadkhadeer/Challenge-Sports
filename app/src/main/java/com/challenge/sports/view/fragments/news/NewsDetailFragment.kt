package com.challenge.sports.view.fragments.news

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import score.pro.R
import com.challenge.sports.model.api.ApiHelperImpl
import com.challenge.sports.model.api.RetroInstance
import com.challenge.sports.model.data.news.details.NewsPostBase
import com.challenge.sports.model.data.news.details.OnPostDetailResponse
import com.challenge.sports.utils.SharedPreference
import com.challenge.sports.utils.ViewModelFactory
import com.challenge.sports.viewmodel.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewsDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var postID: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            postID = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel= ViewModelProvider(requireActivity().viewModelStore,
            ViewModelFactory(ApiHelperImpl(RetroInstance.apiService))
        ).get(MainViewModel::class.java)


    }
    class viewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var tag=itemview.findViewById<TextView>(R.id.tag)
        init {
            itemview.setOnClickListener{

            }
        }
    }

    companion object {
        @JvmStatic fun newInstance(postID: String, unused: String) =
                NewsDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, postID)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}