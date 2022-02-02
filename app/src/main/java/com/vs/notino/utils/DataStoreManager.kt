package com.vs.notino.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreManager(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[TOKEN] = token
        }
    }

    fun getToken() = context.dataStore.data.map {
        it[TOKEN]
    }

    companion object {
        val TOKEN = stringPreferencesKey("TOKEN")
        const val USER_DATASTORE = "USER_DATASTORE"
    }
}


