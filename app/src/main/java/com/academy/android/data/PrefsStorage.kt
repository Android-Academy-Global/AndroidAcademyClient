package com.academy.android.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.academy.android.domain.OperationResult
import com.academy.android.domain.models.UserProfile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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

    suspend fun saveProfile(profile: UserProfile) {
        val serializedProfile = Json.encodeToString(UserProfile.serializer(), profile)
        appContext.dataStore.edit { prefs ->
            prefs[PROFILE_KEY] = serializedProfile
        }
    }

    fun loadProfile(): Flow<OperationResult<UserProfile, Throwable?>> =
        appContext.dataStore
            .data
            .map { prefs ->
                val profile = prefs[PROFILE_KEY]
                OperationResult.Success(
                    data = if (profile != null) {
                        Json.decodeFromString(UserProfile.serializer(), profile)
                    } else {
                        UserProfile.UNKNOWN
                    }
                )
            }

    companion object {
        private val PROFILE_KEY = stringPreferencesKey("user_profile")
    }
}