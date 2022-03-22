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

    fun provideDataSource(): DataSource {
        return dataSource

    }
    fun provideDataBase(): DataBase{
        return dataBase
    }

}
