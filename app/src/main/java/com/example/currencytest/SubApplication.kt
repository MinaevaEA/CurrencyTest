package com.example.currencytest

import android.app.Application
import android.content.Context
import androidx.room.Room

import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import retrofit2.*
import javax.inject.Inject

@HiltAndroidApp
class SubApplication : Application() {
    private lateinit var dataBase: AppDatabase
    private lateinit var dataFromNetwork: Retrofit
    override fun onCreate() {
        super.onCreate()
        /*     dataBase = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database-name"
        ).build()
        dataFromNetwork = RetrofitServices.getClient()
    }

    fun provideDataFromNetwork(): Retrofit {
        return dataFromNetwork
    }

    //на удаление
    fun provideDataBase(): AppDatabase {
        return dataBase
    }*/
    }
}




