package com.example.currencytest.dagger

import android.content.Context
import androidx.room.Room
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideDataFromNetwork(): RetrofitServices {
        val baseUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitServices::class.java)
    }
}
