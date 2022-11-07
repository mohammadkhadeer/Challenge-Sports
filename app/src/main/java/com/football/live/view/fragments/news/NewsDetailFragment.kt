package com.football.live.view.fragments.news

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
import com.football.live.model.api.ApiHelperImpl
import com.football.live.model.api.RetroInstance
import com.football.live.model.data.news.details.NewsPostBase
import com.football.live.model.data.news.details.OnPostDetailResponse
import com.football.live.utils.SharedPreference
import com.football.live.utils.ViewModelFactory
import com.football.live.view.BaseActivity
import com.football.live.viewmodel.MainViewModel

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

        viewModel.makeNewsPostCall(postID!!,object : OnPostDetailResponse<NewsPostBase>{
            override fun onSuccess(responseBody: NewsPostBase) {
                (activity as BaseActivity?)?.makeBackButtonVISIBLE()

                Glide.with(requireContext())
                    .load(responseBody.path)
                    .into(view.findViewById(R.id.cover))

                view.findViewById<TextView>(R.id.heading).text=responseBody.title
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.findViewById<TextView>(R.id.body).text=Html.fromHtml(responseBody.content,Html.FROM_HTML_MODE_COMPACT)
                }
                val rv= view.findViewById<RecyclerView>(R.id.tags_rv)
                val keywords= responseBody.keywords.split(",")

                rv.adapter=object : RecyclerView.Adapter<viewHolder>() {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
                        return viewHolder(LayoutInflater.from(context).inflate(R.layout.tags_item,parent,false))
                    }

                    override fun onBindViewHolder(holder: viewHolder, position: Int) {
                        holder.tag.text=keywords[position]
                    }


                    override fun getItemCount(): Int {
                        return keywords.size
                    }
                }
                rv.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)
            }
            override fun onFailure(message: String) {

            }

            override fun onLoading(message: String) {

            }
        },SharedPreference.getInstance().getStringValueFromPreference(SharedPreference.LOCALE_KEY,SharedPreference.ENGLISH,requireContext()))

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