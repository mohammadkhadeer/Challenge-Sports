package com.five.live.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import sports.myapplication.R

class FullscreenActivity : AppCompatActivity() {
    lateinit var exo:ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        val player: ExoPlayer = ExoPlayer.Builder(this).build()
         exo=player
        // Set the media item to be played.
        val videoLink=intent.getStringExtra("vid_link")
        player.setMediaItem(MediaItem.fromUri(videoLink!!))
        // Prepare the player.
        player.prepare()
        val videoPlayer=findViewById<StyledPlayerView>(R.id.video_player)
        videoPlayer.player=player
        player.addListener(object: Player.Listener{
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState== Player.STATE_READY){
                    videoPlayer.hideController()

                }
            }
        })
        player.playWhenReady=true
    }

    override fun onBackPressed() {
        exo.release()
        super.onBackPressed()
    }
}