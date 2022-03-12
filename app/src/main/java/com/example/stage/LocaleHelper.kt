package com.example.stage

import android.content.Context
import android.content.res.Configuration
import java.util.*

class LocaleHelper{
    fun updateLocale(c: Context,lang:String): Context{
        var newContext = c
        var local = Locale(lang)
        Locale.setDefault(local)
        var resource = c.resources
        var config = Configuration(resource.configuration)
        config.setLocale(local)
        newContext = c.createConfigurationContext(config)

        return newContext
    }
}