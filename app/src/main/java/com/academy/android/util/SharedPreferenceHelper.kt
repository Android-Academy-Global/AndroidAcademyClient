package com.academy.android.util

import android.content.Context
import androidx.preference.PreferenceManager
import com.academy.android.model.Profile
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    private val gson = Gson()

    fun saveProfData(profile: Profile) {
        val gsonProfile = gson.toJson(profile)
        prefs.edit().putString(PROFILE, gsonProfile).apply()
    }

    fun getProfData(): Profile {
        val profData = prefs.getString(PROFILE, null)
        return if (profData != null){
            gson.fromJson(profData, Profile::class.java)
        } else {
            Profile()
        }
    }

    companion object {
        const val PROFILE = "user_profile"
    }
}