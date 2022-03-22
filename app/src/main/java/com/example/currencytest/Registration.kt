package com.example.currencytest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.currencytest.databinding.ActivityRegistrationBinding

class Registration : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener {
            checkInputData()
        }
    }

    private fun checkInputData() {
        if (binding.editUser.text.toString() == "admin" &&
            binding.editPassword.text.toString() == "admin"
        ) {
            Toast.makeText(applicationContext, "Вход выполнен!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "Неправильные данные!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, Registration::class.java)
    }
}