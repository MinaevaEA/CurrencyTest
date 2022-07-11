package com.example.currencytest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.currencytest.databinding.ActivityForNavBinding


class ActivityForNavFragments : AppCompatActivity() {
    private lateinit var binding: ActivityForNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
       /* if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_for_nav, MenuFragment.newInstance())
                .commit()
        }*/
        val navController = this.findNavController(R.id.fragment)
        binding.bottomNavView.setupWithNavController(navController)
        binding.bottomNavView.setOnItemReselectedListener { item ->
            navController.popBackStack(item.itemId, true)
        }
    }
}

