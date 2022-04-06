package com.example.currencytest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.currencytest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main, RegistrationFragment.newInstance())
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("act","onStart-act")
    }

    override fun onResume() {
        super.onResume()
        Log.d("act","onResume-act")
    }

    override fun onPause() {
        super.onPause()
        Log.d("act","onPause-act")
    }

    override fun onStop() {
        super.onStop()
        Log.d("act","onStop-act")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("act","onDestroy-act")
    }
}