package com.vs.notino.roomdb

import com.vs.notino.models.Product
import com.vs.notino.roomdb.daos.ProductDAO
import javax.inject.Inject

class DBRepository @Inject constructor(
    private val productDAO: ProductDAO
) {
    suspend fun getProduct(productId: Int) = productDAO.getProduct(productId)
    suspend fun getAllProducts() = productDAO.getAll()
    fun getPagedProducts() = productDAO.getAllPaged()
    suspend fun insertProducts(vararg products: Product) = productDAO.insertAll(*products)
}