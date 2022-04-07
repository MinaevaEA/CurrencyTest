package com.example.currencytest.db

import androidx.room.*

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    suspend fun getAll(): List<Currency>

    @Insert
    suspend fun insertAll(currency: Currency)

    @Update
    suspend fun update(currency: Currency)

    @Delete
    suspend fun delete(currency: Currency)
}