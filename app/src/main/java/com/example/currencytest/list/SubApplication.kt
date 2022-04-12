package com.example.currencytest.list

import android.app.Application
import androidx.room.Room
import com.example.currencytest.db.AppDatabase


class SubApplication : Application() {
    private lateinit var dataBase: AppDatabase
    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    fun provideDataBase(): AppDatabase {
        return dataBase
    }

}
