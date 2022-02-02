package com.vs.notino.networking

import com.vs.notino.networking.reponses.RespProducts
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("db")
    suspend fun getProducts(
        @Query("page") page: Int? = 1,
        @Query("pageSize") pageSize: Int? = 20,
    ): Response<RespProducts>

    @POST("db/{id}/fav") // example na Endpoint, ktery by favorizoval product
    suspend fun postFavProduct(
        @Path("id") id: Int,
        @Query("favored") favored: Boolean
    ): Response<Void>

    @PUT("basket") // example na Endpoint, ktery by pridaval do kosiku
    suspend fun putProduct(
        @Query("id") id: Int,
        @Query("count") count: Int
    ): Response<Void>
}