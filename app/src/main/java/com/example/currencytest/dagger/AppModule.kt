package com.example.currencytest.dagger

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.db.CurrencyDao
import com.example.currencytest.retrofit.RetrofitServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Module
class AppModule (private val application: Application) {
    private lateinit var dataFromNetwork: Retrofit
    @Provides
    fun providesApplicationContext(): Context = application

    @Provides
    fun providesApplication(): Application = application

    @Provides
    fun provideDataBase(context: Context): AppDatabase {
      val dataBase = Room.databaseBuilder(
          context,
            AppDatabase::class.java, "database-name"
        ).build()
        return dataBase
    }

    @Provides
    fun provideDataFromNetwork(): Retrofit {
        val baseUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"
            dataFromNetwork = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return dataFromNetwork

    }
    @Provides
    fun provideApi(retrofit: Retrofit): RetrofitServices {
        return retrofit.create(RetrofitServices::class.java)
    }
}