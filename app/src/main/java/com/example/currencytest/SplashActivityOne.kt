package com.example.currencytest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView


@SuppressLint("CustomSplashScreen")
class SplashActivityOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_one)

        val backgroundImg: ImageView = findViewById(R.id.iv_note)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)

        backgroundImg.startAnimation(sideAnimation)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)

    }
}