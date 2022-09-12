package com.example.myapplication.model.test_exo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.utils.GeneralTools
import com.example.myapplication.view.fragments.homeFrags.IndexDisplayFragmnet
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.TimelineChangeReason
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.hls.HlsManifest


class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        findViewById<View>(R.id.test_bt).setOnClickListener {
        GeneralTools.setAlarmFor(this,System.currentTimeMillis()+60000)
        }


/*        inflateFragment(
            IndexDisplayFragmnet.newInstance(
            intent.getStringExtra("matchid")?:"0","")
            ,R.id.frag_container_test)*/
/*        val player: ExoPlayer = ExoPlayer.Builder(this).build()
        // Set the media item to be played.
        player.setMediaItem(MediaItem.fromUri("https://460598949f82.ap-northeast-1.playback.live-video.net/api/video/v1/ap-northeast-1.726782747956.channel.rCQIS5HEVrLo.m3u8"))
        // Prepare the player.
        player.prepare()
        player.addListener(
            object : Player.Listener {
                override fun onTimelineChanged(
                    timeline: Timeline, reason: @TimelineChangeReason Int
                ) {
                    val manifest = player.currentManifest
                    if (manifest != null) {
                        val hlsManifest = manifest as HlsManifest
                        // Do something with the manifest.
                    }
                }
            })

        player.setVideoSurfaceView(findViewById(R.id.exo_surface_view))
        player.play()*/


    }

    private fun inflateFragment(newInstance: IndexDisplayFragmnet, resourceId: Int) {
        val ft=supportFragmentManager.beginTransaction()
        ft.replace(resourceId,newInstance)
        ft.commit()
    }


}