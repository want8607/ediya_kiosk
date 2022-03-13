package com.example.stage.mainfragments

import android.content.Context
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
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = super.onCreateView(inflater, container, savedInstanceState)
        mainActivity = activity as MainActivity
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        writeSummary()
        //뒤로가기
        var settingBackBtn = view.findViewById<ImageButton>(R.id.setting_back_button)
        settingBackBtn.setOnClickListener {
            mainActivity.removeFragment(this, "setting")
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        mainActivity.settingFlag = "true"
        val pref = PreferenceManager.getDefaultSharedPreferences(mainActivity)
        val edit = pref.edit()
        //다크모드 설정
        if(AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO){
            when(sharedPreferences.getString(key,"")){
                "기본 모드" ->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)}
                "Light Mode" ->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)}
            }
        }else if(AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES){
            when(sharedPreferences.getString(key,"")){
                "Dark Mode" ->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)}
                "다크 모드" ->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)}
            }
        }else if(AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM){
            when(sharedPreferences.getString(key,"")){
                "시스템 기본값"->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)}
                "System Default"->{AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)}
            }
        }
        when(sharedPreferences.getString(key,"")){
            //언어 설정
            "영어" ->{
                if(pref.getString("app_mode","") == "기본 모드"){
                    edit.putString("app_mode","Light Mode").apply()
                }else if(pref.getString("app_mode","") == "다크 모드"){
                    edit.putString("app_mode","Dark Mode").apply()
                }else if(pref.getString("app_mode","") == "시스템 기본값"){
                    edit.putString("app_mode","System Default").apply()
                }
                edit.putString(key,"English").apply()
                mainActivity.recreate()
                writeSummary()

            }
            "Korean" ->{
                if(pref.getString("app_mode","") == "Light Mode"){
                    edit.putString("app_mode","기본 모드").apply()
                }else if(pref.getString("app_mode","") == "Dark Mode"){
                    edit.putString("app_mode","다크 모드").apply()
                }else if(pref.getString("app_mode","") == "System Default"){
                    edit.putString("app_mode","시스템 기본값").apply()
                }
                edit.putString(key,"한국어").apply()
                mainActivity.recreate()
                writeSummary()
            }
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

    fun writeSummary(){
        var appModePref = findPreference<ListPreference>("app_mode")
        var appLanguagePref = findPreference<ListPreference>("app_language")
        appModePref?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
        appLanguagePref?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
//
    }
}
