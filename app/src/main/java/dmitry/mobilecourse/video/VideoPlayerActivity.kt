package dmitry.mobilecourse.video

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.R

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val playButton = findViewById<Button>(R.id.playButton)
        val pauseButton = findViewById<Button>(R.id.pauseButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        videoView = findViewById(R.id.videoView)

        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.never_gonna_give_you_up) // Замените 'sample_video' на ваше видео
        videoView.setVideoURI(videoUri)

        playButton.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }

        pauseButton.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        stopButton.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.stopPlayback()
                videoView.resume() // После остановки проигрывания восстанавливаем видео в начальное состояние
            }
        }
    }
}