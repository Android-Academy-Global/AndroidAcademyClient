package com.academy.android.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.academy.android.domain.models.Profile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PrefsStorage @Inject constructor(
    @ApplicationContext private val appContext: Context
) {
    private val Context.dataStore by preferencesDataStore("settings")

    suspend fun writeString(key: Preferences.Key<String>, value: String) {
        appContext.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }

    fun readString(key: Preferences.Key<String>, default: String): Flow<String> =
        appContext.dataStore.data
            .map { prefs ->
                prefs[key] ?: default
            }

    suspend fun saveProfData(profile: Profile) {
        val serializedProfile = Json.encodeToString(Profile.serializer(), profile)
        appContext.dataStore.edit { prefs ->
            prefs[PROFILE_KEY] = serializedProfile
        }
    }

    suspend fun getProfData(): Profile {
        val profData = appContext.dataStore.data
            .map { prefs ->
                prefs[PROFILE_KEY]
            }.first()
        return if (profData != null){
            Json.decodeFromString(Profile.serializer(), profData)
        } else {
            Profile()
        }
    }

    companion object {
        private val PROFILE_KEY = stringPreferencesKey("user_profile")
    }
}