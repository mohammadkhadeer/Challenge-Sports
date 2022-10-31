package com.score.pro.model.test_exo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import score.pro.R
import com.score.pro.view.fragments.homeFrags.IndexDisplayFragmnet
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.hls.HlsManifest


class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        findViewById<View>(R.id.test_bt).setOnClickListener {
           //vm.getLeagueInfoForMatch("140","44","0")
        }
        val player: ExoPlayer = ExoPlayer.Builder(this).build()
        // Set the media item to be played.
        player.setMediaItem(MediaItem.fromUri("https://wasilatv.com:5866/hls/wasilive.m3u8"))
        // Prepare the player.
        player.prepare()
        player.addListener(
            object : Player.Listener {
                override fun onTimelineChanged(
                    timeline: Timeline, reason: @Player.TimelineChangeReason Int
                ) {
                    val manifest = player.currentManifest
                    if (manifest != null) {
                        val hlsManifest = manifest as HlsManifest
                        // Do something with the manifest.
                    }
                }
            })

        player.setVideoSurfaceView(findViewById(R.id.exo_surface_view))
        player.play()


    }

    private fun inflateFragment(newInstance: IndexDisplayFragmnet, resourceId: Int) {
        val ft=supportFragmentManager.beginTransaction()
        ft.replace(resourceId,newInstance)
        ft.commit()
    }


}