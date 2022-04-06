package com.example.currencytest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.currencytest.databinding.ActivitySplashOneBinding
import java.util.*
import kotlin.concurrent.timerTask


@SuppressLint("CustomSplashScreen")
class SplashActivityOne : AppCompatActivity() {
    private lateinit var binding: ActivitySplashOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)
        binding.ivNote.startAnimation(sideAnimation)
        val timer = Timer()
        timer.schedule(timerTask {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}
