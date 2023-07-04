package com.example.coinvault_app

import android.content.Context
import android.content.SharedPreferences

class UserPreferencesManager(context: Context) {
    companion object {
        private const val PREF_NAME = "user_prefs"
        private const val KEY_SHOW_TUTORIAL = "show_tutorial"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun shouldShowTutorial(): Boolean {
        return sharedPreferences.getBoolean(KEY_SHOW_TUTORIAL, true)
    }

    fun setShowTutorial(showTutorial: Boolean) {
        sharedPreferences.edit()
            .putBoolean(KEY_SHOW_TUTORIAL, showTutorial)
            .apply()
    }
}
