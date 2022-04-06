package com.example.currencytest.list

import android.app.Application
import com.example.currencytest.currency.DataBase


class SubApplication : Application() {
    private lateinit var dataSource: DataSource
    private lateinit var dataBase: DataBase
    override fun onCreate() {
        super.onCreate()
        dataSource = DataSource()
        dataBase = DataBase()
    }

    //иммитация API(удаленного источника данных)
    fun provideDataSource(): DataSource {
        return dataSource

    }
    //иммитация Базы данных(бд записывает только в кэш)
    fun provideDataBase(): DataBase {
        return dataBase
    }

}
