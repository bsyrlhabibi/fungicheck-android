package com.bibi.fungicheck.ui

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

    fun saveAccessToken(token: String) {
        sharedPreferences.edit().putString("access_token", token).apply()
    }

    fun saveUserName(name: String) {
        sharedPreferences.edit().putString("name", name).apply()
    }

    fun saveUserEmail(email: String) {
        sharedPreferences.edit().putString("email", email).apply()
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("name", null)
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString("email", null)
    }

    fun clearSharedPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}
