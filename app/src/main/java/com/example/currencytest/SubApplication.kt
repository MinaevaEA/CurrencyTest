package com.example.currencytest

import android.app.Application
import androidx.room.Room
import com.example.currencytest.dagger.AppComponent
import com.example.currencytest.dagger.AppModule
import com.example.currencytest.dagger.DaggerAppComponent
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import retrofit2.*


class SubApplication : Application() {
    private lateinit var dataBase: AppDatabase
    private lateinit var dataFromNetwork: Retrofit
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .buildAppComp()
            //.appModule(AppModule(this)).build()
            //на удаление
        dataBase = Room.databaseBuilder(
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
    }
}
