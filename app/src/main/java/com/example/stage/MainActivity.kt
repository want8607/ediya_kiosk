package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)
    }

    fun setDataAtFragment(fragment: Fragment, title: Int, isChecked: Boolean){
        var bundle = Bundle()
        bundle.putInt("title",title)
        bundle.putBoolean("isChecked",isChecked)
        fragment.arguments = bundle
    }

    fun removeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,fragment).addToBackStack(null).commit()
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,fragment).commit()
    }

}
