package com.example.meowlib

import android.content.Intent
import android.media.MediaPlayer
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val anim = findViewById<LottieAnimationView>(R.id.catAnim)

        Handler(Looper.getMainLooper()).postDelayed(Runnable{
            anim.visibility= View.VISIBLE
            anim.playAnimation()
        }, 128)

        val mp = MediaPlayer.create(this, R.raw.meow)
        val btn = findViewById<Button>(R.id.splashBtn)
        btn.setOnClickListener{
         val intent = Intent(this, MainActivity::class.java)
            startActivity( intent )
            mp.start()
        }
    }
}