package com.example.stage

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    val prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }
    fun putString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }
}

