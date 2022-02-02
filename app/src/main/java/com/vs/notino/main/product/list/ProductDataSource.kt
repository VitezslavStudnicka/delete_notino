package com.vs.notino.main.product.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vs.notino.models.Product
import com.vs.notino.networking.RestRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductDataSource constructor(
    private val repository: RestRepository
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentLoadingPageKey = params.key ?: PAGE_START
            val response = repository.getCountries(currentLoadingPageKey, params.loadSize)
            val responseData = mutableListOf<Product>()
            responseData.addAll(response.body()?.vpProductByIds ?: emptyList())

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

    companion object {
        private const val PAGE_START = 1
    }
}