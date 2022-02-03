package com.vs.notino.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val productId: Int,
    val annotation: String?,
    val attributes: Attributes?,
    val brand: Brand?,
    val imageUrl: String?,
    val masterId: Int?,
    val name: String?,
    val orderUnit: String?,
    val price: Price?,
    val productCode: String?,
    val reviewSummary: ReviewSummary?,
    val stockAvailability: StockAvailability?,
    val url: String?
) {
    var favored: Boolean = false
}



