package com.hfad.metro2

import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val time = { formatter.format(LocalDateTime.now()) }

        var running: Boolean = false

        val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)

        /**
         *
         * EARLIER RUNNABLE WITH audio file

        //  check out the tones from tone generator
        val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)

        // playing a audio file from the resources
        val mediaPlayer = MediaPlayer.create(this, R.raw.al1)

        // this handles takes running in the background?
        val handler = Handler(Looper.getMainLooper())

        // the task to be run in the background
        val runnable = object : Runnable {
            override fun run() {
                Log.i("Runnable Started", "Tick")
                mediaPlayer.start()



               // toneGenerator.startTone(bloop)
               // toneGenerator.release()

                handler.postDelayed(this, 800)
            }
        }

        **/

        val textOne: TextView = findViewById(R.id.textView)

        suspend fun blooper() {
            while(running) {
                //mediaPlayer.start()
                Log.i("START", "${time.toString()}")
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
                delay(1000)
                Log.i("STOP", "HEY")

            }
        }
        // start and stop buttons.
        val buttonStart = findViewById<Button>(R.id.button)
        val buttonStop = findViewById<Button>(R.id.button2)

        buttonStart.setOnClickListener {
            running = true
            GlobalScope.launch {
                blooper()
            }

        }
        buttonStop.setOnClickListener {
            running = false
        }

    }

}