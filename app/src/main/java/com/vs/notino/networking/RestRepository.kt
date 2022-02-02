package com.vs.notino.networking

class RestRepository(private val apiService: ApiService) {

    suspend fun getCountries(page: Int? = null, pageSize: Int? = null) = apiService.getProducts(page, pageSize)
    suspend fun postFavProduct(id: Int, favored: Boolean) = apiService.postFavProduct(id, favored)
    suspend fun addItemToBasket(id: Int, count: Int) = apiService.putProduct(id, count)

    companion object {
        const val BASE_IMAGE_URL = "https://i.notino.com/detail_zoom/"
        const val BASE_URL = "https://my-json-server.typicode.com/cernfr1993/notino-assignment/"
    }
}