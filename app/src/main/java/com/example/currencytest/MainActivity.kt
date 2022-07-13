package com.example.currencytest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencytest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
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
         /*   val navController = this.findNavController(R.id.fragment)
            binding.bottomNavView.setupWithNavController(navController)
            binding.bottomNavView.setOnItemReselectedListener { item ->
                navController.popBackStack(item.itemId, true)*/
                //TODO убрать отображение меню при регистрации


        }
    }
}


