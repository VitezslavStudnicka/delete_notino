package com.vs.notino.networking

class RestRepository(private val apiService: ApiService) {

    suspend fun getCountries(page: Int? = null, pageSize: Int? = null) = apiService.getProducts(page, pageSize)
    suspend fun postFavProduct(id: Int) = apiService.postFavProduct(id)

    companion object {
        const val BASE_IMAGE_URL = "https://i.notino.com/detail_zoom/"
    }
}