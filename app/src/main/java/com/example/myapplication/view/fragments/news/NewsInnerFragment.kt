package com.example.myapplication.view.fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.api.ApiHelperImpl
import com.example.myapplication.model.api.RetroInstance
import com.example.myapplication.model.data.news.NewsBase
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.Status
import com.example.myapplication.utils.ViewModelFactory
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.view.adapters.MultipurposeAdapter
import com.example.myapplication.view.adapters.RecyclerViewOnclick
import com.example.myapplication.view.fragments.OnDetailListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsInnerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsInnerFragment : Fragment() {
    private lateinit var recommendedRV: RecyclerView
    private lateinit var seeAllRecommended: View
    private lateinit var trendingRv: RecyclerView
    private lateinit var seeAllTrending: View
    private var onDetailListener: OnDetailListener?=null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_news_inner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         seeAllTrending=view.findViewById(R.id.see_all_top)
         trendingRv=view.findViewById(R.id.trending_recycler_view)
         seeAllRecommended=view.findViewById(R.id.see_all_bottom)
         recommendedRV=view.findViewById(R.id.recommended_recycler)

        val viewModel=ViewModelProvider(requireActivity().viewModelStore,ViewModelFactory(ApiHelperImpl(RetroInstance.apiService))).get(MainViewModel::class.java)
        viewModel.makeNewsCall("1")
        val data=viewModel.newsLiveData.value
        if (data!=null){
           if (data.status==Status.SUCCESS){
               populateRecyclerViews(data)
           }else{
               viewModel.newsLiveData.observe(requireActivity()){
                   if (it.status==Status.SUCCESS){
                       populateRecyclerViews(it)
                   }
               }
           }
        }else{
            viewModel.newsLiveData.observe(requireActivity()){
                if (it.status==Status.SUCCESS){
                    populateRecyclerViews(it)
                }
            }
        }
    }


    fun setOnDetailListener(onDetailListener: OnDetailListener) {
        this.onDetailListener=onDetailListener
    }
    private fun populateRecyclerViews(data: Resource<NewsBase>?) {
        val newsList=data?.data?.list
        trendingRv.adapter=object : MultipurposeAdapter(requireContext(),R.layout.trending_item_view,object : RecyclerViewOnclick{
            override fun onClick(position: Int) {
                val list=ArrayList<String>()
                list.add(newsList?.get(position)?.id.toString())
                onDetailListener?.onDetail(list)
            }
        }){
            override fun onBindViewHolder(holder: viewHolder, position: Int) {
                holder.headline.text= newsList?.get(position)?.title
                holder.tag.text= newsList?.get(position)?.keywords?.substringBefore(",")
                Glide.with(context).load(newsList?.get(position)?.path).into(holder.imageContainer)
            }

            override fun getItemCount(): Int {
                return newsList?.size ?: 0
            }

        }
        trendingRv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        recommendedRV.adapter=object : MultipurposeAdapter(requireContext(),R.layout.recommended_item_view,object : RecyclerViewOnclick{
            override fun onClick(position: Int) {
                val list=ArrayList<String>()
                list.add(newsList?.get(position)?.id.toString())
                onDetailListener?.onDetail(list)
            }
        }){
            override fun onBindViewHolder(holder: viewHolder, position: Int) {
                holder.headline.text= newsList?.get(position)?.title
                holder.tag.text= newsList?.get(position)?.createTime?.substringBefore("T")
                Glide.with(context).load(newsList?.get(position)?.path).into(holder.imageContainer)
            }

            override fun getItemCount(): Int {
                return newsList?.size ?:0
            }
        }
        recommendedRV.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,true)


    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsInnerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}