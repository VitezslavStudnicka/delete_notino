package com.vs.notino.main.product.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs.notino.networking.RestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: RestRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val response = repository.getCountries()
            if (response.isSuccessful) {
                val products = response.body()
                Log.d("blade", products.toString())
            }
        }
    }
}