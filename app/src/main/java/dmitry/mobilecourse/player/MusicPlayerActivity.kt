package dmitry.mobilecourse.player

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.R

class MusicPlayerActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private var isSeekBarTracking = false
    private var currentTrackIndex = 0

    private val trackList = listOf(
        R.raw.instasamka_za_dengi_da,
        R.raw.eminem_without_me,
        R.raw.avaria_pey_pivo
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        val playButton = findViewById<Button>(R.id.playButton)
        val prevButton = findViewById<Button>(R.id.prevButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        seekBar = findViewById(R.id.seekBar)

        initializeMediaPlayer()

        playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                updateSeekBar()
                playButton.text = "Pause"
            } else {
                mediaPlayer.pause()
                playButton.text = "Play"
            }
        }

        prevButton.setOnClickListener {
            if (currentTrackIndex > 0) {
                currentTrackIndex--
                playTrack()
            }
        }

        nextButton.setOnClickListener {
            if (currentTrackIndex < trackList.size - 1) {
                currentTrackIndex++
                playTrack()
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isSeekBarTracking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isSeekBarTracking = false
            }
        })
    }

    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, trackList[currentTrackIndex])
        seekBar.max = mediaPlayer.duration

        mediaPlayer.setOnCompletionListener {
            if (currentTrackIndex < trackList.size - 1) {
                currentTrackIndex++
                playTrack()
            }
        }
    }

    private fun playTrack() {
        mediaPlayer.reset()
        mediaPlayer = MediaPlayer.create(this, trackList[currentTrackIndex])
        mediaPlayer.start()
        seekBar.max = mediaPlayer.duration
        updateSeekBar()
    }

    private fun updateSeekBar() {
        Thread {
            while (mediaPlayer.isPlaying) {
                if (!isSeekBarTracking) {
                    seekBar.progress = mediaPlayer.currentPosition
                }
                Thread.sleep(1000)
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
}