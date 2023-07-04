package com.example.coinvault_app

import android.content.Context
import android.content.SharedPreferences

class AuthManager(context: Context) {
    companion object {
        private const val PREF_NAME = "auth_prefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return sharedPreferences.contains(KEY_USERNAME) && sharedPreferences.contains(KEY_PASSWORD)
    }

    fun login(username: String, password: String): Boolean {
        // Validate the entered username and password against stored credentials
        val storedUsername = sharedPreferences.getString(KEY_USERNAME, null)
        val storedPassword = sharedPreferences.getString(KEY_PASSWORD, null)

        if (username == storedUsername && password == storedPassword) {
            return true
        }

        return false
    }

    fun logout() {
        // Clear the stored credentials on logout
        sharedPreferences.edit().clear().apply()
    }

    fun saveCredentials(username: String, password: String) {
        // Save the entered username and password as credentials
        sharedPreferences.edit()
            .putString(KEY_USERNAME, username)
            .putString(KEY_PASSWORD, password)
            .apply()
    }
}
