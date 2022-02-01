package com.vs.notino.networking

import com.vs.notino.networking.reponses.RespProducts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("db")
    suspend fun getProducts(
        @Query("page") page: Int? = 1,
        @Query("pageSize") pageSize: Int? = 20,
    ): Response<RespProducts>

    @POST("db/{id}/fav") // example na Endpoint, ktery by favorizoval product
    suspend fun postFavProduct(
        @Path("id") id: Int
    ): Response<Nothing>

}