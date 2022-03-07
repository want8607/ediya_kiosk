package com.example.stage.mainfragments

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

class SettingFragment : PreferenceFragmentCompat(),Preference.OnPreferenceChangeListener{

    lateinit var mainActivity : MainActivity
    //리스너 설정
    var preferenceListener = Preference.OnPreferenceChangeListener { preference, any ->
        var stringValue = any.toString()
        if (preference is ListPreference) {
            val listPreference = preference
            val index = listPreference.findIndexOfValue(stringValue)
            preference.setSummary(
                if (index>=0){
                    listPreference.entries[index]
                }else{
                    null
                }
            )
        }
        true
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting,rootKey)
        bindPreferenceSummary(findPreference<ListPreference>("app_mode")!!)
        bindPreferenceSummary(findPreference<ListPreference>("app_language")!!)
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
            mainActivity.removeFragment(this,"setting")
        }
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        var prefs = PreferenceManager.getDefaultSharedPreferences(mainActivity)
        var d = prefs.getString("app_mode","")
        if (d == "기본 모드"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }else if (d=="다크 모드"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else if (d=="시스템 기본값"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        Log.d("sis",d!!)
        return true
    }

    fun bindPreferenceSummary(preference: Preference){
        preference.onPreferenceChangeListener = preferenceListener
        preferenceListener.onPreferenceChange(preference,PreferenceManager
            .getDefaultSharedPreferences(preference.context)
            .getString(preference.key, ""))
    }
}