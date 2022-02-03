package com.vs.notino.roomdb

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vs.notino.models.*

// Pouzil bych moshi misto gsonu kvuli rychlosti, ale ted by to jen zneprehlednilo kod a neni to potreba
object Converters {

    @TypeConverter
    fun gsonToAttributes(json: String?): Attributes {
        return Gson().fromJson(json, Attributes::class.java)
    }

    @TypeConverter
    fun attributesToGson(data: Attributes): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun gsonToBrand(json: String?): Brand {
        return Gson().fromJson(json, Brand::class.java)
    }

    @TypeConverter
    fun brandToGson(data: Brand): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun gsonToPrice(json: String?): Price {
        return Gson().fromJson(json, Price::class.java)
    }

    @TypeConverter
    fun priceToGson(data: Price): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun gsonToReviewSummary(json: String?): ReviewSummary {
        return Gson().fromJson(json, ReviewSummary::class.java)
    }

    @TypeConverter
    fun reviewSummaryToGson(data: ReviewSummary): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun gsonToStockAvailability(json: String?): StockAvailability {
        return Gson().fromJson(json, StockAvailability::class.java)
    }

    @TypeConverter
    fun stockAvailabilityToGson(data: StockAvailability): String? {
        return Gson().toJson(data)
    }
}