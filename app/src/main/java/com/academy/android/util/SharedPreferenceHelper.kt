package com.academy.android.util

import android.content.Context
import androidx.preference.PreferenceManager
import com.academy.android.domain.models.Profile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveProfData(profile: Profile) {
        val serializedProfile = Json.encodeToString(Profile.serializer(), profile)
        prefs.edit().putString(PROFILE, serializedProfile).apply()
    }

    fun getProfData(): Profile {
        val profData = prefs.getString(PROFILE, null)
        return if (profData != null){
            Json.decodeFromString(Profile.serializer(), profData)
        } else {
            Profile()
        }
    }

    companion object {
        const val PROFILE = "user_profile"
    }
}