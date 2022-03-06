package com.example.stage.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.preference.PreferenceFragmentCompat
import com.example.stage.MainActivity
import com.example.stage.R

class SettingFragment : PreferenceFragmentCompat() {
    lateinit var mainActivity : MainActivity

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting)
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
}