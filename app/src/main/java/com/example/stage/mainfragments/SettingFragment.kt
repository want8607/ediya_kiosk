package com.example.stage.mainfragments

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.stage.MainActivity
import com.example.stage.R
import java.util.*

class SettingFragment : PreferenceFragmentCompat(),SharedPreferences.OnSharedPreferenceChangeListener {

    lateinit var mainActivity: MainActivity

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
        var appModePref = findPreference<ListPreference>("app_mode")
        var appLanguagePref = findPreference<ListPreference>("app_language")

        appModePref?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
        appLanguagePref?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = super.onCreateView(inflater, container, savedInstanceState)
        mainActivity = activity as MainActivity
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        var settingBackBtn = view.findViewById<ImageButton>(R.id.setting_back_button)
        settingBackBtn.setOnClickListener {
            mainActivity.removeFragment(this, "setting")
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {

        when(sharedPreferences.getString(key,"")){
            //다크모드설정
            "기본 모드" ->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Log.d("app_mode","기본 모드")}
            "다크 모드" ->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Log.d("app_mode","다크 모드")}
            "시스템 기본값"->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                Log.d("app_mode","시스템 기본값")}
            "Light Mode" ->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Log.d("app_mode","기본 모드")}
            "Dark Mode" ->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Log.d("app_mode","다크 모드")}
            "System Default"->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                Log.d("app_mode","시스템 기본값")}
            //언어설정
            "한국어" ->{setLocale("ko")}
            "영어" ->{setLocale("en")}
            "Korean"->{setLocale("ko")}
            "English" ->{setLocale("en")}
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    fun setLocale(lang:String){
        var locale = Locale(lang)
        Locale.setDefault(locale)
        var configuration : Configuration = mainActivity.resources.configuration
        configuration.setLocale(locale)
        mainActivity.createConfigurationContext(configuration)
    }

}
