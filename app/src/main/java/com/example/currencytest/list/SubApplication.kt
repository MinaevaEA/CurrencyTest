package com.example.currencytest.list

import android.app.Application
import androidx.room.Room
import com.example.currencytest.db.AppDatabase


class SubApplication : Application() {
    private lateinit var dataSource: DataSource
    private lateinit var dataBase: AppDatabase
    override fun onCreate() {
        super.onCreate()
        dataSource = DataSource()
        dataBase = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    //иммитация API(удаленного источника данных)
    fun provideDataSource(): DataSource {
        return dataSource

    }
    //иммитация Базы данных(бд записывает только в кэш)
    fun provideDataBase(): AppDatabase {
        return dataBase
    }

}
