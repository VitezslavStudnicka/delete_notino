package com.vs.notino.roomdb.daos

import androidx.paging.PagingSource
import androidx.room.*
import com.vs.notino.models.Product

@Dao
interface  ProductDAO {

    @Query("SELECT * FROM Product WHERE productId=:id")
    suspend fun getProduct(id: Int): Product?

    @Query("SELECT * FROM Product")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM Product")
    fun getAllPaged(): PagingSource<Int, Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg products: Product)

    @Delete
    suspend fun delete(product: Product)
}