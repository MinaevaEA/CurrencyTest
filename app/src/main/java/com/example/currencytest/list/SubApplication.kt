package com.example.currencytest.list

import android.app.Application
import androidx.room.Room
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import retrofit2.*


class SubApplication : Application() {
    private lateinit var dataBase: AppDatabase
    private lateinit var dataFromNetwork: Retrofit
    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database-name"
        ).build()
        dataFromNetwork = RetrofitServices.getClient()
    }

    fun provideDataFromNetwork(): Retrofit {
        return dataFromNetwork
    }

    fun provideDataBase(): AppDatabase {
        return dataBase


    }
//TODO retrofit инициализируется через subapplication
}
