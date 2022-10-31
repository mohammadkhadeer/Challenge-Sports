package com.score.pro.view.fragments.video

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import score.pro.R
import com.score.pro.model.api.ApiHelperImpl
import com.score.pro.model.api.RetroInstance
import com.score.pro.model.data.videos.List
import com.score.pro.utils.SharedPreference
import com.score.pro.utils.Status
import com.score.pro.utils.ViewModelFactory
import com.score.pro.view.FullscreenActivity
import com.score.pro.view.adapters.MultipurposeAdapter
import com.score.pro.view.adapters.RecyclerViewOnclick
import com.score.pro.viewmodel.MainViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.material.switchmaterial.SwitchMaterial
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
class VideosDetailFragment : Fragment() {
    private var videoLink: String? = null
    private var titleDate: String? = null
    private var maxPage: Int? = null
    private var exo:ExoPlayer?=null
    private var data:kotlin.collections.List<List>?=null
    private var recyclerView:RecyclerView?=null
    private var previousPosition=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoLink = it.getString(ARG_PARAM1)
            titleDate = it.getString(ARG_PARAM2)
            maxPage = it.getInt(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_videos_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val detailRecyclerview=view.findViewById<RecyclerView>(R.id.details_recommended_recycler)
        recyclerView=detailRecyclerview
        val player: ExoPlayer = ExoPlayer.Builder(requireContext()).build()
        exo=player
        // Set the media item to be played.
        player.setMediaItem(MediaItem.fromUri(videoLink!!))
        // Prepare the player.
        player.prepare()
        val videoPlayer=view.findViewById<StyledPlayerView>(R.id.video_player)
        val videoTitle=view.findViewById<TextView>(R.id.video_title)
        val dateView=view.findViewById<TextView>(R.id.date_time)
        val title=titleDate?.substringBefore(getString(R.string.titledateSeperator))
        val date=titleDate?.substringAfter(getString(R.string.titledateSeperator))
        videoTitle.text=title

        dateView.text=date
        videoPlayer.player=player

        view.findViewById<View>(R.id.full_screen_bt).setOnClickListener {
            requireContext().startActivity(Intent(requireContext(),FullscreenActivity::class.java).putExtra("vid_link",videoLink))
            player.pause()
        }

        val viewModel= ViewModelProvider(requireActivity().viewModelStore,
            ViewModelFactory(ApiHelperImpl(RetroInstance.apiService))
        ).get(MainViewModel::class.java)

        viewModel.videosLiveData.observe(requireActivity()){
            if (it.status==Status.SUCCESS){
                val data=it?.data
                this.data=data?.list
                populateRv(detailRecyclerview,data?.list)
                viewModel.videosLiveData.removeObservers(requireActivity())
            }
        }
        var pageNumber=maxPage?.div(10)

        pageNumber=(1..pageNumber!!).random()
        viewModel.makeVideosListCall(pageNumber.toString(),SharedPreference.getInstance().getStringValueFromPreference(SharedPreference.LOCALE_KEY,SharedPreference.ENGLISH,requireContext()))
        player.addListener(object: Player.Listener{
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState==Player.STATE_READY){
                    videoPlayer.hideController()
                }
                if (playbackState==Player.STATE_ENDED){
                    if(view.findViewById<SwitchMaterial>(R.id.autoplay_switch).isChecked){
                        val link=getNextLink(data)
                        playThisVideo(link,geNextTitle(data),getNextTime(data))
                        previousPosition++
                    }
                }
            }
        })
        player.playWhenReady=true


    }

    private fun getNextTime(data: kotlin.collections.List<List>?): String {
        val prev=  previousPosition+1
        return if (prev<data!!.size ){

            data[prev].createTime
        } else{
            ""
        }
    }

    private fun geNextTitle(data: kotlin.collections.List<List>?): String {
        val prev=  previousPosition+1
        return if (prev<data!!.size ){

            data[prev].title
        } else{
            ""
        }
    }

    private fun getNextLink(data: kotlin.collections.List<List>?): String {
      val prev=  previousPosition+1
        return if (prev<data!!.size ){
            data[prev].path
        } else{
            ""
        }
    }
    fun playThisVideo(videoLink:String,title:String,date:String){
       exo?.setMediaItem(MediaItem.fromUri(videoLink))
       exo?.seekToNextMediaItem()
       view?.findViewById<TextView>(R.id.video_title)?.text=title
       view?.findViewById<TextView>(R.id.date_time)?.text=date
   }

    fun populateRv(detailRecyclerview: RecyclerView, list: MutableList<List>?){
        detailRecyclerview.adapter=object : MultipurposeAdapter(requireContext(),R.layout.videos_recommended_item,object : RecyclerViewOnclick{
            override fun onClick(position: Int) {
                playThisVideo(list?.get(position)?.path!!, list[position].title!!, list[position].createTime!!)
            }
        }){
            override fun onBindViewHolder(holder: viewHolder, position: Int) {
                holder.headline.text= list?.get(position)?.title
                holder.tag.text=list?.get(position)?.createTime
                Glide.with(requireContext())
                    .load(list?.get(position)?.thumbnailPath)
                    .into(holder.imageContainer)
            }

            override fun getItemCount(): Int {
                return try {
                    list?.size!!
                } catch (e:Exception){
                    0
                }
            }
        }
        detailRecyclerview.layoutManager=LinearLayoutManager(requireContext())
    }

    companion object {
        @JvmStatic
        fun newInstance(videoLink: String, titleDate: String,maxPage:Int) =
            VideosDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, videoLink)
                    putString(ARG_PARAM2, titleDate)
                    putInt(ARG_PARAM3, maxPage)
                }
            }
    }

    override fun onDetach() {
        super.onDetach()
        exo?.release()
    }
}