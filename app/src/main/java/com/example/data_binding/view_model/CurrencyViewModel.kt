package com.example.data_binding.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data_binding.model.CurrencyModel
import com.example.data_binding.model.CurrencyPair
import com.example.data_binding.model.Quotes
import com.example.data_binding.repository.CurrencyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyViewModel(private val repository: CurrencyRepository) : ViewModel() {

    val currenciesLiveData = MutableLiveData<List<CurrencyPair>>()

    fun getCurrencies() = CoroutineScope(Dispatchers.IO).launch {
        val response = repository.getCurrencies().body()
        withContext(Dispatchers.Main){
            if(response != null){
                //currenciesLiveData.postValue(response)
                val quotes  = response.quotes
                   val quotesList = quotes.toList()
                    val currencyPair = quotesList.map {
                        CurrencyPair(name = it.first.toString(),value = it.second.toString())
                    }
                    currenciesLiveData.value = currencyPair


            }
        }
    }

    val currenciesData = viewModelScope.launch {
        repository.getCurrency()
    }

    fun insertCurrencies(currencies: CurrencyModel) = viewModelScope.launch {
        repository.insertCurrencies(currencies)
    }

    fun deleteArticle() = viewModelScope.launch {
        repository.deleteCurrency()
    }
}