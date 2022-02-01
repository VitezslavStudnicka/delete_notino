package com.vs.notino.main.product.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.vs.notino.models.Product
import com.vs.notino.networking.RestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: RestRepository
) : ViewModel() {

    val productList =
        Pager(PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = ADAPTER_PREFETCH_SIZE)
        ) {
            ProductDataSource(repository)
        }.liveData.cachedIn(viewModelScope)

    fun doFavItem(product: Product) {
        viewModelScope.launch {
            val response = repository.postFavProduct(product.productId)
            if (response.isSuccessful) {
                Log.d(TAG, "Success")
            } else {
                Log.d(TAG, "NotLikeThis")
            }
        }
    }

    companion object {
        val TAG = ProductListViewModel::class.java.enclosingClass?.simpleName
        const val PAGE_SIZE = 10
        const val ADAPTER_PREFETCH_SIZE = 6
    }
}