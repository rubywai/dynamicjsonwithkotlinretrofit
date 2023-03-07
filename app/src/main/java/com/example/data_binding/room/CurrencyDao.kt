package com.example.data_binding.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data_binding.model.CurrencyModel
import com.example.data_binding.model.Quotes

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: CurrencyModel)

    @Query("SELECT * FROM currency")
    fun getCurrencies() : LiveData<CurrencyModel>

    @Query("DELETE FROM currency")
    fun deleteCurrencies()
}