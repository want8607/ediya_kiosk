package com.example.stage

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context:Context) {
    val prefs: SharedPreferences = context.getSharedPreferences("my_pref",0)

    fun getString(key: String, defValue: String): String
    {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String)
    {
        prefs.edit().putString(key, str).apply()
    }
}