package com.example.data_binding.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quotes: Map<String,String>,
    val source: String,
    val success: Boolean,
    val timestamp: Int
)