package com.example.currencytest.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(

    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "price") var price: String,
    @ColumnInfo(name = "number") var number: Int = 0,
    @ColumnInfo(name = "date") var date : String,
    @ColumnInfo(name = "generalSumm") var generalSumm: Double? = 0.0,

){
    @PrimaryKey(autoGenerate = true) var currencyId: Int =0
}


