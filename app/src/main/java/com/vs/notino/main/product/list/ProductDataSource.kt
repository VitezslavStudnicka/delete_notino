package com.vs.notino.main.product.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vs.notino.models.Product
import com.vs.notino.networking.RestRepository
import com.vs.notino.roomdb.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ProductDataSource constructor(
    private val repository: RestRepository,
    private val dbRepository: DBRepository
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentLoadingPageKey = params.key ?: PAGE_START
            val response = repository.getCountries(currentLoadingPageKey, params.loadSize)
            val responseData = mutableListOf<Product>()
            val products = response.body()?.vpProductByIds ?: emptyList()
            responseData.addAll(products)
            addToDB(products)

            LoadResult.Page(
                responseData,
                prevKey = null,
                nextKey = if (responseData.isNotEmpty()) currentLoadingPageKey + 1 else null // kdyz neni co, prestane nacitat.
                // V tomto pripade ale nema endpoint paging a vzdy vrati odpoved s daty -> se nacita do nekonecna... aspon je to fajn pro ukazku
            )
        } catch (e: IOException) { // chyby site
            LoadResult.Error(e)
        } catch (e: HttpException) { // chyby serveru
            LoadResult.Error(e)
        }
    }

    private fun addToDB(products: List<Product>) {
        GlobalScope.launch(Dispatchers.IO){
            dbRepository.insertProducts(*products.toTypedArray())
        }
    }

    companion object {
        private const val PAGE_START = 1
    }
}