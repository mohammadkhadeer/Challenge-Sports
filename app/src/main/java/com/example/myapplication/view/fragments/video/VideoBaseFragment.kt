package com.example.myapplication.view.fragments.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.api.ApiHelperImpl
import com.example.myapplication.model.api.RetroInstance
import com.example.myapplication.utils.Status
import com.example.myapplication.utils.ViewModelFactory
import com.example.myapplication.view.adapters.MultipurposeAdapter
import com.example.myapplication.view.adapters.RecyclerViewOnclick
import com.example.myapplication.view.fragments.OnBackPressedListener
import com.example.myapplication.view.fragments.OnDetailListener
import com.example.myapplication.viewmodel.MainViewModel
import kotlin.math.max

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VideoBaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideoBaseFragment : Fragment() {
    private var onDetailListener: OnDetailListener?=null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var onBackPressedListener: OnBackPressedListener? = null

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

        return inflater.inflate(R.layout.fragment_video_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val moreVidsRecyclerView = view.findViewById<RecyclerView>(R.id.more_vids_recycler_view)
        val viewModel = ViewModelProvider(
            requireActivity().viewModelStore,
            ViewModelFactory(ApiHelperImpl(RetroInstance.apiService))
        ).get(MainViewModel::class.java)
        viewModel.videosLiveData.observe(requireActivity()) {
            if (it.status == Status.SUCCESS) {
                moreVidsRecyclerView.adapter = object : MultipurposeAdapter(
                    requireContext(),
                    R.layout.video_item_view,
                    object : RecyclerViewOnclick {
                        override fun onClick(position: Int) {
                            val link = it.data?.list?.get(position)?.path
                            val maxPage = it.data?.meta?.lastPage
                            val titleDate=it.data?.list?.get(position)?.title+getString(R.string.titledateSeperator)+it.data?.list?.get(position)?.createTime
                            if (maxPage != null) {
                                if (link != null) {
                                    val list=ArrayList<String>()
                                    list.add(link)
                                    list.add(titleDate)
                                    list.add(maxPage.toString())
                                    onDetailListener?.onDetail(list)
                                }
                            }
                        }
                    }) {
                    override fun onBindViewHolder(holder: viewHolder, position: Int) {
                        holder.headline.text = it.data?.list?.get(position)?.title
                        holder.tag.text = it.data?.list?.get(position)?.createTime
                        Glide.with(requireContext())
                            .load(it.data?.list?.get(position)?.thumbnailPath)
                            .into(holder.imageContainer)
                    }

                    override fun getItemCount(): Int {
                        return it.data?.list?.size ?: 0
                    }

                }
                moreVidsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
            }
        }
        viewModel.makeVideosListCall("1")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoBaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun setOnDetailListener(onDetailListener: OnDetailListener) {
        this.onDetailListener=onDetailListener
    }
}