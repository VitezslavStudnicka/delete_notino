package com.vs.notino.main.product.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs.notino.models.Product
import com.vs.notino.roomdb.DBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val dbRepository: DBRepository
) : ViewModel() {

    val product: MutableLiveData<Product?> = MutableLiveData()

    fun loadProduct(productId: Long) {
        viewModelScope.launch {
            val result = dbRepository.getProduct(productId.toInt())
            product.postValue(result)
        }
    }

}