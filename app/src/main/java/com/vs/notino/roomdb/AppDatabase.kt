package com.vs.notino.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vs.notino.models.Product
import com.vs.notino.roomdb.daos.ProductDAO

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDAO(): ProductDAO

    companion object {
        const val NOTINO_DB_NAME = "notino_db"
    }
}