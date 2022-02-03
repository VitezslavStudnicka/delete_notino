package com.vs.notino.main.product.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.vs.notino.models.Product
import com.vs.notino.networking.RestRepository
import com.vs.notino.roomdb.DBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: RestRepository,
    private val dbRepository: DBRepository
) : ViewModel() {

    val productList =
        Pager(PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = ADAPTER_PREFETCH_SIZE)
        ) {
            ProductDataSource(repository, dbRepository)
        }.liveData.cachedIn(viewModelScope)

    val message: MutableLiveData<String> = MutableLiveData()

    fun doFavItem(product: Product) {
        val current = product.favored // with val preventing against fast repeated async calls to call with wrong parameter
        product.favored = !current
        Log.d(TAG, "Success id=${product.productId} newFavoredState=${product.favored}")
        viewModelScope.launch {
            val response = repository.postFavProduct(product.productId, !current)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Success")
                    message.postValue("Success")
                } else {
                    Log.d(TAG, "NotLikeThis")
                    message.postValue("Oh no, could not favorite")
                }
            }
        }
    }

    fun addItemToBasket(product: Product) {
        Log.d(TAG, "OmegaLul")
        viewModelScope.launch {
            val response = repository.addItemToBasket(product.productId, 1)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d(TAG, "OmegaLul")
                    message.postValue("OmegaLul - Success")
                } else {
                    Log.d(TAG, "NotLikeThis")
                    message.postValue("Failed KekW")
                }
            }
        }
    }

    companion object {
        val TAG = ProductListViewModel::class.java.enclosingClass?.simpleName
        const val PAGE_SIZE = 10
        const val ADAPTER_PREFETCH_SIZE = 6
    }
}